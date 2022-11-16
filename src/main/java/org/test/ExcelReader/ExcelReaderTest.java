package org.test.ExcelReader;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ExcelReaderTest {

    String filepath;
    String fullpath;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    FileInputStream fis = null; // actual reading of the data from the file
    FileOutputStream fout = null; // actual writing/adding of the data in the file
    int rowCount = 0;
    int columnCount = 0;
    ArrayList<String> loginValues = new ArrayList<String>();
    int i,j = 0;

    public ArrayList<String> getData(String filepath, String excelname, String sheetname, int rowNumber){

        fullpath = filepath + excelname;
        try {
            fis = new FileInputStream(fullpath); // this might give FileNotFoundException
            workbook = new XSSFWorkbook(fis); // this might give IOException
            sheet = workbook.getSheet(sheetname); // fetch the existing sheet
            rowCount = sheet.getLastRowNum(); // 0 - 4; 0 is the row header, 1 to 4 has the data

            columnCount = sheet.getRow(0).getLastCellNum(); // 1 to 3


            row = sheet.getRow(rowNumber); // TC01-> rowNumber = 1


            loginValues = new ArrayList<String>(); // flushing of the old values

            for(i = 0; i<columnCount; i++){ // loop for columns
                String var = row.getCell(i).getStringCellValue(); // actual fetching of data
                loginValues.add(var);
            }
            //workbook.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginValues;
    }
}
