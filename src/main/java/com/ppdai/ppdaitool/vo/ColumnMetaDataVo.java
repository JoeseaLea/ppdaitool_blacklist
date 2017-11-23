package com.ppdai.ppdaitool.vo;

public class ColumnMetaDataVo {
	private String name;
	private String label;
	private int width;

	public ColumnMetaDataVo(){

	}
	
	public ColumnMetaDataVo(String name, String label, int width){
		this.name = name;
		this.label = label;
		this.width = width;
	}

	/**
	 * 字段名
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 字段标签
	 */
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 字段宽度
	 */
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}