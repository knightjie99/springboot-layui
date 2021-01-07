package com.wujie.springbootlayui.sys.common;

import com.icexls.IceExcel;
import com.icexls.IceExcelConfig;
import com.icexls.ParserType;
import org.apache.poi.hssf.usermodel.*;

public class ExcelUtil {

	public static String[][] getExcelStr(String readPath) {
		IceExcel iceXls = new IceExcel(readPath);
		IceExcelConfig.setParserType(iceXls, ParserType.POI);
		String[][] data = null;// 读取Excel
		try {
			data = iceXls.getData();
		} catch (Exception e) {
			return null;
		}
		return data;
	}
	
	public static void main(String[] args) {
//
//		String[] title = {"ID", "业主姓名"};
//
//		String[][] content = new String[2][2];
//		content[0][0]="1";
//		content[1][0]="2";
//		content[0][1]="3";
//		content[1][1]="4";
//		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook("页名", title, content, null);
//		File file=new File("d:\\upload\\");
//
//		OutputStream stream=null;
//		try {
//			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
//			stream = new FileOutputStream(new File(file, "机构客户信息表"+".xls"));
//			//document.write(stream);
//			wb.write(stream);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			if(stream != null);
//			try {
//				stream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		String filePath = "C:\\Users\\zhuy\\Desktop\\" + "sn导入表.xls";
		System.out.println("filePath:"+filePath);
		String [][] excelData = ExcelUtil.getExcelStr(filePath);

		System.out.println(excelData.length);
	}

	public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (wb == null)
			wb = new HSSFWorkbook();

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle); // 创建一个居中格式

		//声明列对象
		HSSFCell cell = null;

		//创建标题
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
//			cell.setCellStyle(style);
		}

		//创建内容
		for (int i = 0; i < values.length; i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < values[i].length; j++) {
				//将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
			}
		}
		return wb;
	}

}
