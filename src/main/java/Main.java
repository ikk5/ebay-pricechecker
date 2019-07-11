import XML.FindCompletedItemsResponse;
import keywords.SearchBuilder;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Properties properties = main.getProperties();
        //read config

        //open and read xls

        //build searches
        Map<String, String> urlParams = main.buildSearch();


        //open default connection
        try {
            Connection connection = new Connection(urlParams, properties);
            FindCompletedItemsResponse response = connection.getResponse();
            System.out.println(response.toString());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        //do and process searches

        //save results to xls

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

    private Map<String, String> buildSearch() {
        SearchBuilder builder = new SearchBuilder("Chibi Robo", "Gamecube", "CIB", "PAL", "");
        Map<String, String> urlParams = new LinkedHashMap<>();
        urlParams.put("keywords", builder.buildSearchString());
        urlParams.put("categoryId(0)", Integer.toString(builder.getCategoryId()));
        urlParams.put("itemFilter(0).name", "SoldItemsOnly");
        urlParams.put("itemFilter(0).value", "true");
        urlParams.putAll(builder.getLocatedInUrlParams());
        return urlParams;
    }
}
