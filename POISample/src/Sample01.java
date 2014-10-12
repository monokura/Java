import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Sample01 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("sample");
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("abc");

		Sheet sheet2 = wb.cloneSheet(0);
		Cell cell2 = sheet2.getRow(0).getCell(0);
		cell2.setCellValue("hogehoge");

		OutputStream os = new FileOutputStream("C:\\Users\\monokura\\Desktop\\test.xlsx");
		wb.write(os);

		System.out.println("Finish!");
	}

}
