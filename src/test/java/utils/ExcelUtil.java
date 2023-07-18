package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {
	private final static String filePath = "testOutput/ProgramOutput.xlsx";

	public static void writeToExcel(String sheetName, String cellValue, int row, int col) throws IOException {
		File file = new File(filePath);
		XSSFWorkbook workbook;

		if (file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		} else {
			workbook = new XSSFWorkbook();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			sheet = workbook.createSheet(sheetName);
		}

		Row rowData = sheet.getRow(row);

		if (rowData == null) {
			rowData = sheet.createRow(row);
		}

		Cell cell = rowData.createCell(col);
		cell.setCellValue(cellValue);

		sheet.autoSizeColumn(col);

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		workbook.close();
	}
}
