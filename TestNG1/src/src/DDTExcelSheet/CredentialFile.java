package DDTExcelSheet;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.testng.annotations.Test;

import com.google.common.collect.Table.Cell;

public class CredentialFile {
	String username;
	String password;
	@Test
	public String test() throws EncryptedDocumentException, IOException
	{
	
	FileInputStream fs=new FileInputStream("E:\\Pallavi-WorkingDirectory\\TestNG1\\src\\Excelsheet-ddt\\Pallavi.xlsx");
	Workbook wb=WorkbookFactory.create(fs);
	String username=NumberToTextConverter.toText(wb.getSheet("Login").getRow(1).getCell(0).getNumericCellValue());
	return username;

}
	public String passtest() throws EncryptedDocumentException, IOException
	{
	
	FileInputStream fs=new FileInputStream("E:\\Pallavi-WorkingDirectory\\TestNG1\\src\\Excelsheet-ddt\\Pallavi.xlsx");
	Workbook wb=WorkbookFactory.create(fs);
	String password=wb.getSheet("Login").getRow(0).getCell(1).getStringCellValue();
	return password;

}

	
}
