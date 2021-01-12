package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import properties.Parameters;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWriteExcel {
    private static FileInputStream getInputStream() throws IOException {
        return new FileInputStream(Parameters.excelFileLocation);

    }

    private static FileOutputStream getOutputStream() throws IOException {
        return new FileOutputStream(Parameters.excelFileLocation);

    }

    private static Workbook getWorkbook(FileInputStream fis) throws IOException {
        return new XSSFWorkbook(fis);

    }

    private static Sheet getSheet(Workbook workbook, String inputOrOutput) {
        return workbook.getSheet(inputOrOutput);

    }

    private static void writeDataOutput(Workbook workbook){
        try {
            workbook.write(getOutputStream());
        } catch (IndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean cellIsEmpty(Cell c){
        return c == null || c.getCellType() == CellType.BLANK;
    }

    private static Row exactRow(Sheet sheet, String twitterURL){
        for(Row row: sheet) {
            Cell cName = row.getCell(1);
            if(!cellIsEmpty(cName)) {
                boolean containsSring = cName.getStringCellValue()
                        .contains(twitterURL);
                if (containsSring)
                    return row;
            }
        }
        return null;

    }



    public static void fillInArrayListsFromExcel(ArrayList<String> twitterUrls, ArrayList<String> twitterNames, ArrayList<String> twitterGames) throws IOException {
        FileInputStream fis = getInputStream();
        Workbook workbook = getWorkbook(fis);
        Sheet sheetI = getSheet(workbook, "input");

        for(Row row: sheetI) {
            Cell cellUsername = row.getCell(0);
            Cell cellURL = row.getCell(1);
            Cell cellGame = row.getCell(2);
            System.out.println(cellURL.getStringCellValue());
            Cell cellDate = row.getCell(3);
            if(cellIsEmpty(cellDate)) {
                twitterUrls.add(cellURL.getStringCellValue());
                twitterNames.add(cellUsername.getStringCellValue());
                twitterGames.add(cellGame.getStringCellValue());
            }
        }

    }

    public static String getDMText(String username, String game) throws IOException {
        FileInputStream fis = getInputStream();
        Workbook workbook = getWorkbook(fis);

        Sheet sheetI = getSheet(workbook, "dm");
        Row row = sheetI.getRow(0);
        Cell cell = row.getCell(0);
        String cellValue = cell.getStringCellValue();

        return SelUtils.personalizeMessage(cellValue, username, game);


    }

    public static void writeData(String dm, String url) throws IOException {
        FileInputStream fis = getInputStream();
        Workbook workbook = getWorkbook(fis);
        Sheet sheetI = getSheet(workbook, "input");
        Row row = exactRow(sheetI, url);
        int i = row.getRowNum();
        System.out.println("ROWNUM " + i);
        ;

        Cell cellA = row.createCell(4);
        cellA.setCellValue(dm);
        Cell cellB = row.createCell(3);
        cellB.setCellValue(SelUtils.getCurrentDate());

        writeDataOutput(workbook);


    }

}

