package unistar.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Interface for Writer to write data into MS Excel file.
 * <p>
 * W:WORKBOOK, S:SHEET, R:ROW, C:CELL, F:FORMAT, L:STYLE
 */
public interface ExcelWriter {

    /**
     * Max rows per sheet.
     */
    int MAX_ROWNUM = 1048575;

    /**
     * Get workbook for this writer.
     *
     * @return The workbook.
     */
    Workbook getWorkbook();

    /**
     * Create and return a new sheet.
     *
     * @return New sheet created into the workbook.
     */
    Sheet createSheet() throws Exception;

    /**
     * Create and return a new sheet.
     *
     * @param sheetName sheet name.
     * @return New sheet created into the workbook.
     */
    Sheet createSheet(String sheetName) throws Exception;

    /**
     * Move to next sheet. <br/>
     * Create the sheet automatically if next sheet does not exist.
     *
     * @return the current sheet after moved.
     */
    Sheet nextSheet() throws Exception;

    /**
     * Move current sheet to specified sheet.
     *
     * @param index      Index of sheet to move to.
     * @param autoCreate Create new sheet if the sheet does not exist.
     * @return The sheet moved to, or null if sheet does not exist.
     */
    Sheet toSheet(int index, boolean autoCreate) throws Exception;

    /**
     * Set sheet name of current sheet.
     *
     * @param name Name for the sheet.
     */
    void setSheetName(String name);

    /**
     * Get current sheet.
     *
     * @return Current sheet.
     */
    Sheet getSheet();

    /**
     * Returns index of current sheet.
     *
     * @return Index of current sheet or -1 if there are no sheet pointed.
     */
    int getSheetIndex();

    /**
     * Create a new row as last row.
     *
     * @return The new row.
     */
    Row createRow();

    /**
     * Get current row.
     *
     * @return Current row.
     */
    Row getRow();

    /**
     * Returns the row at the index. If the row has not been created, it will be created automatically.
     *
     * @param index Index of row.
     * @return The row at the index.
     */
    Row getRow(int index);

    /**
     * Move to next row. <br/>
     * Create the row automatically if next row does not exist.
     *
     * @return The current row after moved.
     */
    Row nextRow() throws ExcelOperationException;

    /**
     * Move to a cell of current row.
     *
     * @param index Index of cell.
     * @return The current cell after moved.
     */
    Cell toCell(int index);

    /**
     * Get current cell.
     *
     * @return Current cell.
     */
    Cell getCell();

    /**
     * Get cell on the given index of current row. The cell will be created if the cell is not exist.
     *
     * @param index Index of cell.
     * @return The cell at the index of current row.
     */
    Cell getCell(int index);

    /**
     * Get cell on the given indexes. The cell will be created if the cell is not exist.
     *
     * @param row Index of row.
     * @param col Index of cell.
     * @return The cell at the index.
     */
    Cell getCell(int row, int col);

    /**
     * Whether if sheet will be created automatically if the row number reaches {@link #MAX_ROWNUM} in
     * current sheet.
     */
    boolean isAutoTurn();

    /**
     * Set auto-create and move to next sheet automatically if the row number reaches
     * {@link #MAX_ROWNUM} in current sheet.
     *
     * @param autoTurn Set true for auto-create.
     */
    void setAutoTurn(boolean autoTurn);

    /**
     * Set value for current cell with specified value.
     *
     * @param value Value to set.
     */
    void setValue(Object value) throws Exception;

    /**
     * Set value for current cell with specified value and style.
     *
     * @param value Value to set.
     * @param style Style for the cell.
     */
    void setValue(Object value, CellStyle style) throws Exception;

    /**
     * Set value to cell with specified value and style.
     *
     * @param row   Index of row for the cell.
     * @param col   Index of column for the cell.
     * @param value Value to set.
     * @param style Style for the cell.
     */
    void setValue(int row, int col, Object value, CellStyle style) throws Exception;

    /**
     * Set values to current row. The cells will be created automatically.<br/>
     * (The cursor won't be updated.)
     *
     * @param values Values to set.
     */
    void setValues(Object... values) throws Exception;

    /**
     * Set values to current row. The cells will be created automatically.<br/>
     * (The cursor won't be updated.)
     *
     * @param style  Style for the value cells.
     * @param values Values to set.
     */
    void setValues(CellStyle style, Object... values) throws Exception;

    /**
     * Set row height of sheet.
     *
     * @param chars How many characters could be shown for the column.
     */
    void setRowHeight(int chars) throws Exception;

    /**
     * Set column width of sheet.
     *
     * @param index Index of column(zero-based).
     * @param chars How many characters could be shown for the column.
     */
    void setColumnWidth(int index, int chars);

    /**
     * Set column width of columns in current sheet.
     *
     * @param chars How many characters could be shown for columns.
     */
    void setColumnWidth(int... chars);

    /**
     * Set style to current cell.
     *
     * @param style The style for cell.
     */
    void setCellStyle(CellStyle style);

    /**
     * Set default style of columns in current sheet.
     *
     * @param styles Styles for each column.
     */
    void setDefaultColumnStyles(CellStyle... styles);

}

