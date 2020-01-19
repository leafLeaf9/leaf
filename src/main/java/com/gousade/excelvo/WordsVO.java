package com.gousade.excelvo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class WordsVO {
	
	@Excel(name = "词名")
	private String wordname;
	
	@Excel(name = "情感值")
    private String value;
	
	@Excel(name = "词性")
    private String wordtype;
	
	@Excel(name = "公式类型")
    private String formtype;

	public String getWordname() {
		return wordname;
	}

	public void setWordname(String wordname) {
		this.wordname = wordname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getWordtype() {
		return wordtype;
	}

	public void setWordtype(String wordtype) {
		this.wordtype = wordtype;
	}

	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}

}
