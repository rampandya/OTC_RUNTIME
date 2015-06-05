package com.home.readmsdocs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.POIXMLProperties;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFromExcel {
	String fn;
	
	public ReadFromExcel (String fn) {
		this.fn = fn;
	}
	
	public ArrayList<String> getTickers () {
		Workbook  wb = null;
		ArrayList<String> tickers = new ArrayList<String> ();
		 
		try {
	        // Open the Excel file
	        FileInputStream fis = new FileInputStream(fn);
	        // Access the required test data sheet
	        try {
	        	wb = WorkbookFactory.create(fis);
	        }
	        catch (Exception e) {
	        	System.out.println("Encountered exception " + e);
	        }
			org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIter = sheet.rowIterator();
			int i = 0;
			while (rowIter.hasNext()) {
				System.out.println(sheet.getRow(i).getCell(0));
				tickers.add (sheet.getRow(i).getCell(0).toString());
				i++;
				rowIter.next();
			}
	        fis.close();
	    } catch (IOException e) {
	        System.out.println("Test data file not found");
	    }
		return tickers;
		
	}

	public static void main(String args[]) {
		String fn = "C:\\temp\\testdata.xlsx";
		
		ArrayList <String> tickers;
		ReadFromExcel rfe = new ReadFromExcel(fn);
		tickers = rfe.getTickers();
		Iterator <String> itr =  tickers.iterator();
		while (itr.hasNext()) {
			System.out.println("Ticker = " + itr.next());
		}
	}

}
