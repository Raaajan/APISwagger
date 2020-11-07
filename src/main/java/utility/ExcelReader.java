package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public List<HashMap<String, String>> getListMap(String sheetname) throws IOException {
		
		FileInputStream fi = new FileInputStream("D:\\Automation\\Eclipse\\ExcelData\\SwggerAPI.xlsx");
		XSSFWorkbook w = new XSSFWorkbook(fi);
		XSSFSheet s = w.getSheet(sheetname);
		int lastrow = s.getLastRowNum();		
		int lc = s.getRow(0).getLastCellNum();
		
		List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
		
		for(int i=0;i<lastrow;i++) {
			int totalcell = s.getRow(i).getLastCellNum();
			HashMap<String, String> map = new HashMap<String, String>();
			for(int j=0;j<totalcell;j++) {
				map.put(s.getRow(0).getCell(j).toString(), s.getRow(i+1).getCell(j).toString());
			}
			listmap.add(map);
		}
		
		return listmap;
	
	}
	


}
