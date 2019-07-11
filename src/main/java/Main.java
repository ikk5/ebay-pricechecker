import Excel.ProcessedRow;
import Excel.RowProcessor;
import XML.FindCompletedItemsResponse;
import keywords.SearchBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Main main = new Main();

        try {

            //read config
            Properties properties = main.getProperties();
            Connection connection = new Connection(properties);

            //open and read xlsx
            RowProcessor rowProcessor = new RowProcessor(properties.getProperty("XLSX_FILE"));
            Iterator<Row> rowIterator = rowProcessor.getRowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (StringUtils.isBlank(row.getCell(0).getStringCellValue())) {
                    break;
                }
                ProcessedRow processedRow = rowProcessor.processRow(row);

                //build, execute and process searches
                Map<String, String> urlParams = main.buildSearch(processedRow);
                FindCompletedItemsResponse response = connection.getResponse(urlParams);
                rowProcessor.saveRowResults(response, row);
            }
            rowProcessor.saveAndCloseFile();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        System.out.println("Execution time: " + (System.nanoTime() - startTime) / 1000000 + "ms");
    }

    private Properties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (inputStream != null) {
            properties.load(inputStream);
            inputStream.close();
        }
        return properties;
    }

    private Map<String, String> buildSearch(ProcessedRow row) {
        SearchBuilder builder = new SearchBuilder(row.getTitle(), row.getPlatform(), row.getCompleteness(), row.getRegion(), row.getNotes());
        Map<String, String> urlParams = new LinkedHashMap<>();
        urlParams.put("keywords", builder.buildSearchString());
        urlParams.put("categoryId(0)", Integer.toString(builder.getCategoryId()));
        urlParams.put("itemFilter(0).name", "SoldItemsOnly");
        urlParams.put("itemFilter(0).value", "true");
        urlParams.putAll(builder.getLocatedInUrlParams());
        return urlParams;
    }
}
