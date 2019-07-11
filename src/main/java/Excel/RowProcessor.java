package Excel;

import XML.FindCompletedItemsResponse;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class RowProcessor {
    private Integer titleColumn;
    private Integer platformColumn;
    private Integer completenessColumn;
    private Integer regionColumn;
    private Integer notesColumn;

    private File file;
    private FileInputStream fis;
    private XSSFWorkbook xlsx;
    private int lastRow;

    @Getter
    private Iterator<Row> rowIterator;

    public RowProcessor(String filePath) throws IOException {
        file = new File(filePath);
        fis = new FileInputStream(file);
        xlsx = new XSSFWorkbook(fis);
        XSSFSheet sheet = xlsx.getSheetAt(0);
        rowIterator = sheet.rowIterator();
        findColumns(rowIterator.next());
    }

    public ProcessedRow processRow(Row row) {
        ProcessedRow processedRow = new ProcessedRow();
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (titleColumn != null && titleColumn == cell.getColumnIndex()) {
                processedRow.setTitle(cell.getStringCellValue());
            } else if (platformColumn != null && platformColumn == cell.getColumnIndex()) {
                processedRow.setPlatform(cell.getStringCellValue());
            } else if (completenessColumn != null && completenessColumn == cell.getColumnIndex()) {
                processedRow.setCompleteness(cell.getStringCellValue());
            } else if (regionColumn != null && regionColumn == cell.getColumnIndex()) {
                processedRow.setRegion(cell.getStringCellValue());
            } else if (notesColumn != null && notesColumn == cell.getColumnIndex()) {
                processedRow.setNotes(cell.getStringCellValue());
            }
        }
        return processedRow;
    }

    //save results to xlsx:  Value at <Date> (x nr found)
    public void saveRowResults(FindCompletedItemsResponse response, Row row) {
//        System.out.println(response.toString());
        Cell cell = row.createCell(lastRow);
        cell.setCellValue(response.toString());
    }

    public void saveAndCloseFile() throws IOException {
        fis.close();
        FileOutputStream fos = new FileOutputStream(file);
        xlsx.write(fos);
        fos.close();
    }

    private void findColumns(Row row) {
        Iterator<Cell> headerIterator = row.cellIterator();
        while (headerIterator.hasNext() && !allHeadersFound()) {
            Cell cell = headerIterator.next();
            switch (cell.getStringCellValue()) {
                case "Titel":
                case "Title":
                    titleColumn = cell.getColumnIndex();
                    break;
                case "Platform":
                    platformColumn = cell.getColumnIndex();
                    break;
                case "Completeness":
                case "Compleetheid":
                    completenessColumn = cell.getColumnIndex();
                    break;
                case "Regio":
                case "Region":
                    regionColumn = cell.getColumnIndex();
                    break;
                case "Notes":
                case "Note":
                case "Notities":
                case "Notitie":
                    notesColumn = cell.getColumnIndex();
                    break;
            }
        }
        lastRow = row.getLastCellNum();
        Cell cell = row.createCell(lastRow);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        cell.setCellValue("Value on " + dateFormat.format(new Date()));
    }

    private boolean allHeadersFound() {
        return titleColumn != null && platformColumn != null && completenessColumn != null && regionColumn != null && notesColumn != null;
    }
}