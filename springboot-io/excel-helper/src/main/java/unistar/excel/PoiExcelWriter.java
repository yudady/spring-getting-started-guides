package unistar.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiExcelWriter implements ExcelWriter {

    public static void setBorder4DirThin(CellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
    }

    protected CellStyle defaultStringStyle;

    protected CellStyle defaultMoneyStyle;

    protected CellStyle defaultNumberStyle;

    protected CellStyle defaultDateStyle;

    private final Workbook workbook;

    private Sheet sheet;

    private Row row;

    private Cell cell;

    private boolean autoTurn;

    /**
     * Create new instance.
     *
     * @param autoTurn auto create and go to next sheet if the row reaches {@link #MAX_ROWNUM}. Default is false.
     */
    public PoiExcelWriter(InputStream is, boolean autoTurn) throws IOException {
        this.autoTurn = autoTurn;

        if (is == null) workbook = new XSSFWorkbook();
        else workbook = new XSSFWorkbook(is);

        initDefaultStyle(workbook);
    }

    public PoiExcelWriter(boolean autoTurn) {
        this.autoTurn = autoTurn;

        workbook = new XSSFWorkbook();
        initDefaultStyle(workbook);

        sheet = createSheet("sheet1");
        sheetChanged();

        row = sheet.createRow(sheet.getPhysicalNumberOfRows());
        cell = sheet.getRow(row.getRowNum()).getCell(0);
        if (cell == null) cell = sheet.getRow(row.getRowNum()).createCell(0);
    }

    private void initDefaultStyle(Workbook workbook) {
        defaultStringStyle = workbook.createCellStyle();
        setBorder4DirThin(defaultStringStyle);

        short moneyFormat = workbook.createDataFormat().getFormat(MoneyFormat.NORMAL.value);
        defaultMoneyStyle = workbook.createCellStyle();
        defaultMoneyStyle.setDataFormat(moneyFormat);
        setBorder4DirThin(defaultMoneyStyle);
        defaultMoneyStyle.setAlignment(HorizontalAlignment.RIGHT);

        defaultNumberStyle = workbook.createCellStyle();
        setBorder4DirThin(defaultNumberStyle);

        short dateFormat = workbook.createDataFormat().getFormat(DateFormat.DATE.value);
        defaultDateStyle = workbook.createCellStyle();
        defaultDateStyle.setDataFormat(dateFormat);
        setBorder4DirThin(defaultDateStyle);
    }

    public void setIntValue(Cell cell, Number value) {
        if (value != null) cell.setCellValue(value.intValue());
    }

    public void setIntValue(Cell cell, Number value, CellStyle style) {
        setNumericCell(cell, style);
        setIntValue(cell, value);
    }

    public void setIntValue(Number value) {
        setIntValue(cell, value);
    }

    public void setIntValue(Number value, CellStyle style) {
        setIntValue(cell, value, style);
    }

    public void setLongValue(Cell cell, Number value) {
        if (value != null) cell.setCellValue(value.longValue());
    }

    public void setLongValue(Number value) {
        setLongValue(cell, value);
    }

    public void setDoubleValue(Cell cell, Number value) {
        if (value != null) cell.setCellValue(value.doubleValue());
    }

    public void setDoubleValue(Number value) {
        setDoubleValue(cell, value);
    }

    public void setDateValue(Cell cell, Date value) {
        if (value != null) cell.setCellValue(value);
    }

    public void setDateValue(Date value) {
        setDateValue(cell, value);
    }

    private void setNumericCell(Cell cell, CellStyle style) {
        cell.setCellStyle(style);
    }

    @Override
    public Workbook getWorkbook() {
        return workbook;
    }

    public DataFormat createDataFormat() {
        return workbook.createDataFormat();
    }

    public Font createFont() {
        return workbook.createFont();
    }

    public CellStyle createStyle() {
        return workbook.createCellStyle();
    }

    @Override
    public Sheet createSheet() {
        int sheetNum = workbook.getNumberOfSheets() + 1;
        return createSheet("sheet" + sheetNum);
    }

    @Override
    public final Sheet createSheet(String sheetName) {
        return workbook.createSheet(sheetName);
    }

    public void newSheet(String sheetName) {
        createSheet(sheetName);
    }

    public void goNextSheet() {
        nextSheet();
    }

    @Override
    public final Sheet nextSheet() {
        int index = workbook.getSheetIndex(sheet);

        if (index == workbook.getNumberOfSheets() - 1) { // create new sheet if current sheet is last
            sheet = createSheet();
            sheetChanged();
            return sheet;
        }
        return toSheet(index + 1, true);
    }

    @Override
    public final Sheet toSheet(int index, boolean autoCreate) {
        sheet = workbook.getSheetAt(index);
        if (sheet == null && autoCreate) sheet = createSheet();

        sheetChanged();
        return sheet;
    }

    private void sheetChanged() {
        row = null;
        cell = null;
    }

    @Override
    public void setSheetName(String name) {
        workbook.setSheetName(workbook.getSheetIndex(sheet), name);
    }

    @Override
    public Sheet getSheet() {
        return sheet;
    }

    @Override
    public int getSheetIndex() {
        return getWorkbook().getSheetIndex(sheet);
    }

    @Override
    public Row createRow() {
        return sheet.createRow(sheet.getPhysicalNumberOfRows());
    }

    @Override
    public Row getRow() {
        return row;
    }

    @Override
    public Row getRow(int index) {
        Row row = sheet.getRow(index);
        if (row != null) return row;

        return sheet.createRow(index);
    }

    public final void goNextRow() {
        nextRow();
    }

    @Override
    public final Row nextRow() {
        if (row == null) row = createRow();
        else if (autoTurn && row.getRowNum() == MAX_ROWNUM) {
            nextSheet();
            row = createRow();
        } else {
            row = getRow(row.getRowNum() + 1);
        }

        cell = toCell(0);
        return row;
    }

    @Override
    public Cell toCell(int index) {
        cell = getCell(index);
        return cell;
    }

    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public Cell getCell(int index) {
        return this.getCell(row.getRowNum(), index);
    }

    @Override
    public Cell getCell(int rowIndex, int colIndex) {
        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
        if (cell != null) return cell;

        return sheet.getRow(rowIndex).createCell(colIndex);
    }

    private CellStyle getFormat(Object value) {
        if (value instanceof Date) return defaultDateStyle;
        else if (value instanceof Number) return defaultNumberStyle;
        else return defaultStringStyle;
    }

    @Override
    public boolean isAutoTurn() {
        return autoTurn;
    }

    @Override
    public void setAutoTurn(boolean autoTurn) {
        this.autoTurn = autoTurn;
    }

    @Override
    public void setValue(Object value) {
        setValue(cell, value);
    }

    @Override
    public void setValue(Object value, CellStyle style) {
        setValue(cell, value, style);
    }

    @Override
    public void setValue(int row, int col, Object value, CellStyle style) {
        Cell cell = this.getCell(row, col);
        setValue(cell, value, style);
    }

    public void setValue(Cell cell, Object value) {
        CellStyle style = this.getFormat(value);
        setValue(cell, value, style);
    }

    public void setValue(Cell cell, Object value, CellStyle style) {
        Object valueTmp = value;
        if (valueTmp == null) valueTmp = "";

        cell.setCellStyle(style);
        if (valueTmp instanceof Integer) setIntValue(cell, (Integer) valueTmp);
        else if (valueTmp instanceof Long) setLongValue(cell, (Long) valueTmp);
        else if (valueTmp instanceof Double) setDoubleValue(cell, (Double) valueTmp);
        else if (valueTmp instanceof Date) setDateValue(cell, (Date) valueTmp);
        else setStringValue(cell, String.valueOf(valueTmp));
    }

    public void rowValues(Object... values) {
        setValues(values);
    }

    @Override
    public void setValues(Object... values) {
        for (int i = 0; i < values.length; i++) {
            Cell cell = this.getCell(row.getRowNum(), i);
            this.setValue(cell, values[i]);
        }
    }

    @Override
    public void setValues(CellStyle style, Object... values) {
        setValuesFromCol(0, style, values);
    }

    public void setValuesFromCol(int col, CellStyle style, Object... values) {
        if (values == null) return;

        for (int i = 0; i < values.length; i++) {
            setValue(getRow().getRowNum(), col + i, values[i], style);
        }
    }

    @Override
    public void setCellStyle(CellStyle style) {
        getCell().setCellStyle(style);
    }

    public void setStringValue(Cell cell, String value) {
        cell.setCellValue(value == null ? "" : value);
    }

    public void setStringValue(String value) {
        setStringValue(cell, value);
    }

    public byte[] getContent() throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            outputStream.flush();
            return outputStream.toByteArray();
        }
    }

    @Override
    public void setRowHeight(int chars) {
        getRow().setHeight((short) getWidth(chars));
    }

    @Override
    public void setColumnWidth(int index, int chars) {
        getSheet().setColumnWidth(index, getWidth(chars));
    }

    @Override
    public void setColumnWidth(int... visibleNums) {
        for (int i = 0; i < visibleNums.length; i++) {
            sheet.setColumnWidth(i, getWidth(visibleNums[i]));
        }
    }

    @Override
    public void setDefaultColumnStyles(CellStyle... styles) {
        Sheet sheet = getSheet();

        for (int i = 0; i < styles.length; i++) {
            sheet.setDefaultColumnStyle(i, styles[i]);
        }
    }

    protected void createDefaultStyles() {
        initDefaultStyle(workbook);
    }

    public void write(OutputStream os) throws IOException {
        // set merged cell style
        for (int i = 0; i < getWorkbook().getNumberOfSheets(); i++) {
            Sheet sheet = getWorkbook().getSheetAt(i);
            for (int j = 0; j < sheet.getNumMergedRegions(); j++) {
                CellRangeAddress range = sheet.getMergedRegion(j);
                Cell firstCell = this.getCell(range.getFirstRow(), range.getFirstColumn());
                CellStyle style = firstCell.getCellStyle();
                RegionUtil.setBorderBottom(style.getBorderBottom(), range, sheet);
                RegionUtil.setBorderLeft(style.getBorderLeft(), range, sheet);
                RegionUtil.setBorderTop(style.getBorderTop(), range, sheet);
                RegionUtil.setBorderRight(style.getBorderRight(), range, sheet);
                RegionUtil.setBottomBorderColor(style.getBottomBorderColor(), range, sheet);
                RegionUtil.setLeftBorderColor(style.getLeftBorderColor(), range, sheet);
                RegionUtil.setTopBorderColor(style.getTopBorderColor(), range, sheet);
                RegionUtil.setRightBorderColor(style.getRightBorderColor(), range, sheet);
            }
        }
        getWorkbook().write(os);
    }

    public void close() throws IOException {
        getWorkbook().close();
    }

    public int getWidth(int charNum) {
        return (charNum + 1) * 256;
    }

    public void setDateFormat(DateFormat format) {
        this.defaultDateStyle.setDataFormat(this.createDataFormat().getFormat(format.value));
    }

    public byte[] writeToByteArray() throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            write(outputStream);
            return outputStream.toByteArray();
        } finally {
            close();
        }
    }
}
