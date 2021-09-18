package ninja.techved.com_utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fi; 
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static int get_row_count(String xlfile, String xlsheet) throws IOException {
		
		fi = new FileInputStream (xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		wb.close();
		fi.close();
		int rowncount = ws.getLastRowNum();
		return rowncount;
	}
	
	public static int get_cell_Count(String xlfile, String xlsheet, int rownum)throws IOException {
		fi= new FileInputStream(xlfile);
		wb= new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
		
	}
	
	public static String get_cell_data(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try {

			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
			
		catch (Exception e)
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;

		}
	
	
	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)throws IOException{

		fi = new FileInputStream(xlfile);
		wb= new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fi.close();
		}
	

}
