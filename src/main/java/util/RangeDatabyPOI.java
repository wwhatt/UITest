package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RangeDatabyPOI {

	/**
	 *
	 * @throws IOException
	 *
	 */
	public static Object[][] poiRangeData(String filePath) throws IOException {

		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String extensionName = filePath.substring(filePath.indexOf("."));
		if (extensionName.equals(".xls")){
			workbook= new HSSFWorkbook(inputStream);
		}
		else if (extensionName.equals(".xlsx")){
		workbook= new XSSFWorkbook(inputStream);
		}
		else{
			System.out.println("文件格式不正确");
		}
		Sheet sheet = workbook.getSheetAt(0);
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		List<Object[]> records =new ArrayList <Object[]>();

    	for(int i=1;i<rowCount+1;i++){
    		Row row = sheet.getRow(i);
    		String fields[] = new String[row.getLastCellNum()];
    		for(int j=0;j<row.getLastCellNum();j++){

//    			fields[j]=row.getCell(j).getStringCellValue();
				fields[j] = new DataFormatter().formatCellValue(row.getCell(j));
    		}
    		records.add(fields);

    	}
    	
    	Object[][] results=new Object[records.size()][];
    	
    	for(int i=0;i<records.size();i++){
    		results[i]=records.get(i);
    	}
    	
    	
    	return results;
    	//{{"fengluo","shuai"},{"muke","bang"}}
		
	}
	
	
	public static void main(String[] args) {


	}

}
