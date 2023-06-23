package io09.excel;

import org.apache.poi.ss.usermodel.*;


public interface ExcelWriter {

    int MAX_ROWNUM = 1048575;

    Workbook getWorkbook();


    Sheet createSheet() throws Exception;

    Sheet createSheet(String sheetName) throws Exception;

    Sheet nextSheet() throws Exception;

    Sheet toSheet(int index, boolean autoCreate) throws Exception;

    void setSheetName(String name);

    Sheet getSheet();

    int getSheetIndex();

    Row createRow();

    Row getRow();

    Row getRow(int index);

    Row nextRow() throws ExcelOperationException;

    Cell toCell(int index);

    Cell getCell();

    Cell getCell(int index);

    Cell getCell(int row, int col);

    boolean isAutoTurn();

    void setAutoTurn(boolean autoTurn);

    void setValue(Object value) throws Exception;

    void setValue(Object value, CellStyle style) throws Exception;

    void setValue(int row, int col, Object value, CellStyle style) throws Exception;

    void setValues(Object... values) throws Exception;

    void setValues(CellStyle style, Object... values) throws Exception;

    void setRowHeight(int chars) throws Exception;

    void setColumnWidth(int index, int chars);

    void setColumnWidth(int... chars);

    void setCellStyle(CellStyle style);

    void setDefaultColumnStyles(CellStyle... styles);

}

