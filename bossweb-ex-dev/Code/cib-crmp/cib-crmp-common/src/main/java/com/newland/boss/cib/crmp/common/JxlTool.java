package com.newland.boss.cib.crmp.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class JxlTool {

	private JxlTool() {

	}
	private static final Logger LOGGER = LogManager.getLogger(JxlTool.class);
	private static int sheetSize = 60000;
	private static WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);// 设置文本格式
	private static NumberFormat nf = new NumberFormat("#0.00"); // 设置数字格式
	private static WritableCellFormat wcfN = new WritableCellFormat(nf); // 设置表单格式

	/**
	 * 导出Excel
	 * 
	 * @param path
	 *            路径
	 * @param xlsFull
	 *            文件名
	 * @param headList
	 *            列名
	 * @param bodyList
	 *            数据
	 * @param numberFlag
	 *            是否数字格式标志
	 * @throws IOException
	 * @throws WriteException
	 */
	public static void exportExcel(String path, String[] headList, List<String[]> bodyList, boolean[] numberFlag)
			throws IOException, WriteException {

		WritableWorkbook wwb = null;

		try {

			int sheetNum = 1;// 当前sheet索引

			int recNum = 0; // 当前记录索引

			// 创建excel文件
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				boolean mkdirflag = file.getParentFile().mkdirs();
				if (!mkdirflag) {
					throw new IOException("文件目录创建失败");
				}
			}
			boolean flag = file.createNewFile();
			if (!flag) {
				throw new IOException("文件创建失败");
			}

			// 整理数据流，WritableWorkbook
			wwb = Workbook.createWorkbook(file);

			// 开始分SHEET，写数据
			while (true) {
				int rowNum = 0;
				String sheetName = "sheet" + sheetNum;
				// 建立SHEET
				WritableSheet ws = wwb.createSheet(sheetName, sheetNum);
				sheetNum++;
				
				// 写入表头信息
				for (int i = 0; i < headList.length; i++) {
					ws.addCell(new Label(i, rowNum, headList[i], format));
				}
				rowNum++;
				
				// 写入表数据行信息
				if (!bodyList.isEmpty()) {
					int sheetRecNum = 0;
					for (int i = recNum; i < bodyList.size(); i++, recNum++, rowNum++, sheetRecNum++) {
						// 单个SHEET数据行超过SHEET应达到数据限额，则停止写入
						if (sheetRecNum >= sheetSize)
							break;
						String[] row = bodyList.get(i);
						for (int j = 0; j < row.length; j++) {
							if (numberFlag[j]) {
								ws = addNumCell(rowNum, ws, row, j);
							} else {
								ws.addCell(new Label(j, rowNum, row[j], format));
							}
						}
					}
				}
				// 数据写完，退出
				if (recNum >= bodyList.size())
					break;
			}

			// PUSH当前内容到XLS文件中
			wwb.write();

		} catch (Exception e) {
			LOGGER.error("exportExcel err.", e);
		} finally {
			// 关闭所有数据流
			if(wwb!=null){
				wwb.close();
			}
		}
	}

	private static WritableSheet addNumCell(int rowNum, WritableSheet ws, String[] row, int j)
			throws WriteException {
		double doubleValue = Double.parseDouble(row[j]);
		Number labelNF = new Number(j, rowNum, doubleValue, wcfN); // 格式化数值
		ws.addCell(labelNF);
		return ws;
	}

	/**
	 * Excel文件导入数据
	 * 
	 * @param path文件路径
	 *            +文件名
	 * @param sheetCode
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static List<String[]> importExcel(String path, int sheetCode) throws BiffException, IOException {
		List<String[]> dataList = new ArrayList<String[]>();
		Workbook book = null;
		book = Workbook.getWorkbook(new File(path));
		// 获得工作表对象
		Sheet sheet = book.getSheet(sheetCode);
		int rows = sheet.getRows();
		int columns = sheet.getColumns();

		// 遍历每行每列的单元格
		for (int i = 0; i < rows; i++) {
			String[] data = new String[columns];
			for (int j = 0; j < columns; j++) {
				Cell cell = sheet.getCell(j, i);
				String result = cell.getContents();
				data[j] = result;
			}
			dataList.add(data);
		}
		book.close();
		return dataList;
	}
}
