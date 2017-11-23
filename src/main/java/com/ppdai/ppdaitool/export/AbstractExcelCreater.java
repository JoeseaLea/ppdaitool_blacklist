package com.ppdai.ppdaitool.export;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;


public abstract class AbstractExcelCreater{
	protected int startRowIndex = 0;	//开始行索引
	protected int startColIndex = 0;	//开始列索引
	protected boolean landscape = false; //页面是否横排
	
	//序列字段
	protected boolean showSequenceColumn = true;
	protected int sequenceColumnWidth = 30;
	protected String sequenceColumnLabel = "序号";

	//高度
	protected float titleHeight = 25;
	protected float headerHeight = 15;
	protected float detailHeight = 15;
	
	//样式
	protected HSSFCellStyle titleStyle;
	protected HSSFCellStyle headerStyle;
	protected HSSFCellStyle detailStyle;

	public void execute() throws Exception {
		try{
			init();
			//generateTitle();
			generateHeader();
			generateDetail();
			export();
		}finally{
			destroy();
		}
	}

	/**
	 * 初始化
	 */
	protected abstract void init();

	/**
	 * 生成标题行
	 */
	protected abstract void generateTitle();

	/**
	 * 生成列头行
	 */
	protected abstract void generateHeader();

	/**
	 * 生成数据行
	 */
	protected abstract void generateDetail();

	/**
	 * 输出结果
	 */
	protected abstract void export() throws Exception;

	/**
	 * 释放资源
	 */
	protected abstract void destroy();

	/**
	 * 开始输出的行的索引
	 */
	public int getStartRowIndex() {
		return startRowIndex;
	}

	public void setStartRowIndex(int startRowIndex) {
		this.startRowIndex = startRowIndex;
	}

	/**
	 * 开始输出的列的索引
	 */
	public int getStartColIndex() {
		return startColIndex;
	}

	public void setStartColIndex(int startColIndex) {
		this.startColIndex = startColIndex;
	}

	/**
	 * 页面是否横排
	 */
	public boolean isLandscape() {
		return landscape;
	}

	public void setLandscape(boolean landscape) {
		this.landscape = landscape;
	}

	/**
	 * 是否显示序号列
	 */
	public boolean isShowSequenceColumn() {
		return showSequenceColumn;
	}

	public void setShowSequenceColumn(boolean showSequenceColumn) {
		this.showSequenceColumn = showSequenceColumn;
	}

	/**
	 * 序号列的宽度
	 */
	public int getSequenceColumnWidth() {
		return sequenceColumnWidth;
	}

	public void setSequenceColumnWidth(int sequenceColumnWidth) {
		this.sequenceColumnWidth = sequenceColumnWidth;
	}

	/**
	 * 序号列的列头标签
	 */
	public String getSequenceColumnLabel() {
		return sequenceColumnLabel;
	}

	public void setSequenceColumnLabel(String sequenceColumnLabel) {
		this.sequenceColumnLabel = sequenceColumnLabel;
	}

	/**
	 * 标题行的高度
	 */
	public float getTitleHeight() {
		return titleHeight;
	}

	public void setTitleHeight(float titleHeight) {
		this.titleHeight = titleHeight;
	}
	
	/**
	 * 列头行的高度
	 */
	public float getHeaderHeight() {
		return headerHeight;
	}

	public void setHeaderHeight(float headerHeight) {
		this.headerHeight = headerHeight;
	}

	/**
	 * 数据行的高度
	 */
	public float getDetailHeight() {
		return detailHeight;
	}

	public void setDetailHeight(float detailHeight) {
		this.detailHeight = detailHeight;
	}

	/**
	 * 标题的样式
	 */
	public HSSFCellStyle getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(HSSFCellStyle titleStyle) {
		this.titleStyle = titleStyle;
	}

	/**
	 * 列表头的样式
	 */
	public HSSFCellStyle getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(HSSFCellStyle headerStyle) {
		this.headerStyle = headerStyle;
	}

	/**
	 * 数据行的样式
	 */
	public HSSFCellStyle getDetailStyle() {
		return detailStyle;
	}

	public void setDetailStyle(HSSFCellStyle detailStyle) {
		this.detailStyle = detailStyle;
	}

	/**
	 * 序号列数
	 * 
	 * @return 当包含序号列时返回1，否则返回0 
	 */
	public int getSeqCount() {
		if(showSequenceColumn) {
			return 1;
		}
		return 0;
	}

}