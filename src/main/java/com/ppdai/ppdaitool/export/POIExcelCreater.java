package com.ppdai.ppdaitool.export;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import com.ppdai.ppdaitool.utils.StringUtil;
import com.ppdai.ppdaitool.vo.ColumnMetaDataVo;

public class POIExcelCreater extends AbstractExcelCreater {
	private int BASE_COLUMN_WIDTH = 50; //列宽基数
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	
	//构造函数参数
	private String title;
	private List<ColumnMetaDataVo> columnList;
	private List<Map<String, String>> dataList;
	private OutputStream out;
	
	public POIExcelCreater(String title, List<ColumnMetaDataVo> columnList, List<Map<String, String>> dataList, OutputStream out){
		this.title = title;
		this.columnList = columnList;
		this.dataList = dataList;
		this.out = out;
		
		workbook = new HSSFWorkbook();
	}
	
	protected void init(){
		sheet = workbook.createSheet();
		
		//页面设置
		HSSFPrintSetup setup = sheet.getPrintSetup();
		setup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
		setup.setLandscape(landscape); //页方向，true表示横向
		setup.setFooterMargin(0); //页脚
		setup.setHeaderMargin(0); //页眉
		
		//页面距
		sheet.setMargin(HSSFSheet.TopMargin, 0.8);
		sheet.setMargin(HSSFSheet.BottomMargin, 0.8);
		sheet.setMargin(HSSFSheet.LeftMargin, 0.4);
		sheet.setMargin(HSSFSheet.RightMargin, 0.4);
	}

	/**
	 * 生成title行，该行横跨多列
	 */
	protected void generateTitle(){
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(startRowIndex, startRowIndex, startColIndex, startColIndex+columnList.size()-1+getSeqCount()));
		
		HSSFRow titleRow = sheet.createRow(startRowIndex);
		
		//title行高
		titleRow.setHeightInPoints(titleHeight);
		
		//title单元格
		HSSFCell cell = titleRow.createCell(startColIndex);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(title));
		
		if(titleStyle==null) {
			titleStyle = getCellStyle("宋体", (short)16, HSSFColor.BLACK.index, true, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.VERTICAL_CENTER, false, (short)-1);
		}
		cell.setCellStyle((HSSFCellStyle)titleStyle);
		
		++startRowIndex;
	}

	/**
	 * 生成列表头
	 */
	protected void generateHeader(){
		HSSFRow headerRow = sheet.createRow(startRowIndex);
		headerRow.setHeightInPoints(headerHeight);

		if(headerStyle==null) {
			headerStyle = getCellStyle("宋体", (short)10, HSSFColor.BLACK.index, true, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.VERTICAL_CENTER, true, HSSFColor.GREY_25_PERCENT.index);
		}
		
		//包含序号列
		if(showSequenceColumn){
			sheet.setColumnWidth(startColIndex, BASE_COLUMN_WIDTH * sequenceColumnWidth); //栏宽
			
			HSSFCell seqCell = headerRow.createCell(startColIndex);
			seqCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			seqCell.setCellValue(new HSSFRichTextString(sequenceColumnLabel));
			seqCell.setCellStyle((HSSFCellStyle)headerStyle);
		}
		
		for(int col=0; col<columnList.size(); col++){
			ColumnMetaDataVo md = columnList.get(col);
			sheet.setColumnWidth(startColIndex + getSeqCount() + col, BASE_COLUMN_WIDTH * md.getWidth()); //栏宽
			
			HSSFCell cell = headerRow.createCell(startColIndex + getSeqCount() + col);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(md.getLabel()));
			cell.setCellStyle((HSSFCellStyle)headerStyle);
		}
		
		++startRowIndex;
	}

	/**
	 * 生成数据行
	 */
	protected void generateDetail(){
		if(detailStyle==null) {
			detailStyle = getCellStyle("宋体", (short)10, HSSFColor.BLACK.index, false, (short)-1, HSSFCellStyle.VERTICAL_CENTER, true, (short)-1);
		}
			
		for(int row=0; row<dataList.size(); row++){
			Map<String, String> rowValue = dataList.get(row);
			HSSFRow detailRow = sheet.createRow(startRowIndex + row);
			//detailRow.setHeightInPoints(detailHeight); //行高。不需要这行，可以自动适应行高
			
			for(int col=0; col<columnList.size(); col++){
				ColumnMetaDataVo md = columnList.get(col);
				
				//输出序号
				if(showSequenceColumn && col==0){
					HSSFCell cell = detailRow.createCell(startColIndex);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(new HSSFRichTextString(String.valueOf(row+1)));
					cell.setCellStyle((HSSFCellStyle)detailStyle);
				}
				
				HSSFCell cell = detailRow.createCell(startColIndex + getSeqCount() + col);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(StringUtil.isNotEmpty(rowValue.get(md.getName()))){
					cell.setCellValue(new HSSFRichTextString(rowValue.get(md.getName())));
				}else{
					cell.setCellValue(new HSSFRichTextString(" "));
				}
				cell.setCellStyle((HSSFCellStyle)detailStyle);
			}
		}
	}

	protected void export() throws Exception{
		workbook.write(out);
		out.flush();
	}

	protected void destroy(){
		IOUtils.closeQuietly(out);
		sheet = null;
		workbook = null;
	}
	
	/**
	 * 获取单元格样式对象
	 * @param fontName 字体名称，比如 宋体
	 * @param fontSize 字体大小，比如 10
	 * @param fontBold 是否粗体，比如 true
	 * @param align 水平对齐方式
	 * @param valign 垂直对齐方式
	 * @param showBorder 是否显示单元格边框
	 * @param backgroundColor 背景色
	 */
	public HSSFCellStyle getCellStyle(String fontName, short fontSize, short fontColor, boolean fontBold, short align, short valign, boolean showBorder, short backgroundColor){
		HSSFFont font = workbook.createFont();
		if(fontName!=null) font.setFontName(fontName); //字体名称
		if(fontSize!=-1) font.setFontHeightInPoints(fontSize); //字体大小
		if(fontBold) font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //粗体
		if(fontColor!=-1) font.setColor(fontColor); //字体颜色
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true); //自动换行
		style.setFont(font);
		
		//对齐方式
		if(align!=-1) style.setAlignment(align);
		if(valign!=-1) style.setVerticalAlignment(valign);
		
		//边框
		if(showBorder){
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		}
		
		if(backgroundColor!=-1){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(backgroundColor);
		}
		
		return style;
	}

}