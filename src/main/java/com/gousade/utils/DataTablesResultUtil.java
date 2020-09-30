package com.gousade.utils;

import com.github.pagehelper.PageInfo;

public class DataTablesResultUtil {

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static DataTablesPageUtil packageResult(DataTablesPageUtil dataTable, PageInfo pageInfo) {
		// 封装数据给DataTables
		dataTable.setData(pageInfo.getList());
		dataTable.setRecordsTotal((int) pageInfo.getTotal());
		dataTable.setRecordsFiltered(dataTable.getRecordsTotal());
		return dataTable;
	}
}
