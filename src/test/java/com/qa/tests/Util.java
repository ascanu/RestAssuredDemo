package com.qa.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Util {
	
	public static String generateRandomChars(String candidateChars, int length) {
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
	                .length())));
	    }

	    return sb.toString();
	}

	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	String cellText=null;
	public String readExcel(String filePath,String fileName,String sheetName) throws IOException{

	    //Create an object of File class to open xlsx file

	    File file =    new File(filePath+"\\"+fileName);

	    //Create an object of FileInputStream class to read excel file

	    FileInputStream inputStream = new FileInputStream(file);

	    Workbook guru99Workbook = null;

	    //Find the file extension by splitting file name in substring  and getting only extension name

	    String fileExtensionName = fileName.substring(fileName.indexOf("."));

	    //Check condition if the file is xlsx file

	    if(fileExtensionName.equals(".xlsx")){

	    //If it is xlsx file then create object of XSSFWorkbook class

	    guru99Workbook = new XSSFWorkbook(inputStream);

	    }

	    //Check condition if the file is xls file

	    else if(fileExtensionName.equals(".xls")){

	        //If it is xls file then create object of HSSFWorkbook class

	        guru99Workbook = new HSSFWorkbook(inputStream);

	    }

	    //Read sheet inside the workbook by its name

	    Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);
	    int rowcount=guru99Sheet.getLastRowNum();
        System.out.println("Total Row" + rowcount);

      
	    //Find number of rows in excel file

	    int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();

	    //Create a loop over all the rows of excel file to read it
	    DataFormatter formatter = new DataFormatter(Locale.US);
	  

	    for (int i = 0; i < rowCount+1; i++) {

	        Row row = guru99Sheet.getRow(i);
	        

	        //Create a loop to print cell values in a row

	        for (int j = 0; j < row.getLastCellNum(); j++) {

	            //Print Excel data in console
	        	System.out.print(formatter.formatCellValue(row.getCell(j))+"|| ");     
	        	  cellText=formatter.formatCellValue(row.getCell(j));
	        }

	        System.out.println();
	       
	    }
		return cellText;
	  

	}	    

	    //Main function is calling readExcel function to read data from excel file

	    public static void main(String...strings) throws IOException{

	    //Create an object of ReadGuru99ExcelFile class

	    	Util objExcelFile = new Util();

	    //Prepare the path of excel file

	    String filePath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\testdata";
	    
	    System.out.println("file path is"+filePath); 
	    //Call read file method of the class to read data

	    objExcelFile.readExcel(filePath,"anupam.xlsx","Sheet1");

	    }

}
