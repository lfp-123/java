package com.newland.boss.cib.crmp.common;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.Test;

import com.newland.boss.cib.crmp.common.controller.DownloadController;

@PrepareForTest(value = { JxlTool.class })
public class JxlToolTest {

	@Test
	public void exportExcel() throws Exception {
		System.out.println(this.getClass().getResource("/").getPath());
		String path = this.getClass().getResource("/").getPath() + "downloadTemp/";
		String fileName = "test.xls";
		String[] columns = { "主叫号码", "被叫号码", "通话起始时间", "通话结束时间", "通话时长", "地区名" };
		boolean[] numberFlag = { false, false, false, false, true, false };
		List<String[]> dataList = new ArrayList<String[]>();
		String[] date = { "15678987898", "15353655365", "20180101 00:00:00", "20180101 00:01:00", "1", "591" };
		for (int i = 0; i < 10; i++) {
			dataList.add(date);
		}
		JxlTool.exportExcel(path + fileName, columns, dataList, numberFlag);
		List<String[]> dataListBack = JxlTool.importExcel(path + fileName, 0);
		File file = new File(path + fileName);
		File filePath = new File(path);
		if (file.exists()) {
			file.delete();
			filePath.delete();
		}
		path = System.getProperty("user.dir") + "/downloadTemp/";
		JxlTool.exportExcel(path + fileName, columns, dataList, numberFlag);
		new DownloadController().ratedCdrDownload(fileName);
		assertEquals((dataListBack.get(1))[0], (dataList.get(0))[0]);

	}
}
