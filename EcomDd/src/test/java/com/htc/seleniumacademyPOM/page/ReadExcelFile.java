package com.htc.seleniumacademyPOM.page;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcelFile {

	//XSSF //HSSF
	Workbook ecomStoreWorkbook;
	Sheet loginData;
	public ReadExcelFile(String excelfilePath) {
		try {
		File s = new File(excelfilePath);
		FileInputStream stream = new FileInputStream(s);
		ecomStoreWorkbook = new XSSFWorkbook(stream);
		System.out.println("excelFilepath"+excelfilePath);
		System.out.println("ecomStoreWorkbook "+ecomStoreWorkbook.getNumberOfSheets());
		}
		catch(Exception e) {
		System.out.println("error "+e.getMessage());
		}
		}

	
		public Object[][] getRecords(String sheetName)
		{
			loginData =ecomStoreWorkbook.getSheet(sheetName);
			System.out.println("read"+loginData.toString());
			//if sheet as heading start row =1 for the data 
			//row 0 consist of heading
			int totalNoofRows=loginData.getLastRowNum();
			Object[][] records=new Object[totalNoofRows][loginData.getRow(1).getLastCellNum()];
			for(int row=0;row<loginData.getLastRowNum();row++)
			{
				for(int col=0;col<loginData.getRow(row+1).getLastCellNum();col++)
				{
					records[row][col]=loginData.getRow(row+1).getCell(col).getStringCellValue();
				}
			}
			return records;
		}
}
