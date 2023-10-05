package Package2;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DDT {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fs= new FileInputStream("/Users/user/eclipse-workspace/TestNg/Pallavi/Excel-sheet.xlsx");
		Workbook wb=WorkbookFactory.create(fs);
		String pass=wb.getSheet("Login").getRow(1).getCell(0).getStringCellValue();
		System.out.print(pass);
		

	}

}
