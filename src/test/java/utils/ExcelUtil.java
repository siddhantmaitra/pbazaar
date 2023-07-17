package utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

	public static File file;
	public static XSSFWorkbook wb;
	public static FileInputStream inputStream;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static FileOutputStream outputStream;

	public static void selectSheetName(String sheetName) throws Exception {

		// Create an object of File class to open xlsx file
//	    file = new File("C:\\Users\\2266897\\eclipse-workspace\\SeleniumNG2_obRepo\\test-output\\ProgramOutput.xlsx");
		file = new File("testOutput/ProgramOutput02.xlsx");
		// Create an object of FileInputStream class to read excel file
		inputStream = new FileInputStream(file);

		// creating workbook instance that refers to .xls file
		wb = new XSSFWorkbook(inputStream);

		// creating a Sheet object using the sheet Name
		sheet = wb.getSheet(sheetName);
	}

	public static void createRow(int i) {
		row = sheet.createRow(i);
	}

	public static void setValue(String name, String price) {
		row.createCell(0).setCellValue(name);
		row.createCell(1).setCellValue(price);
	}

	public static void setExcelCell(String msg) {
		row.createCell(0).setCellValue(msg);
	}

	public static void writeExcel() throws IOException {
		// write the data in excel using output stream

		outputStream = new FileOutputStream(file);

		wb.write(outputStream);
		try {

			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        wb.close();
	}
}
