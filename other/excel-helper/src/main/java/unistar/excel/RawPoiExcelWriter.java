package unistar.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class RawPoiExcelWriter extends PoiExcelWriter {

    private CellStyle headerStyle;

    private String[] headers;

    private int[] colChars;

    /**
     * Create instance.
     *
     * @param headers  Header values.
     * @param colChars Set same width in characters for each column.
     * @throws Exception
     */
    public RawPoiExcelWriter(String[] headers, int colChars) throws Exception {
        this(headers, new int[]{colChars});
    }

    /**
     * Create instance.
     *
     * @param headers  Header values.
     * @param colChars Set different width in characters for each column.
     * @throws Exception
     */
    public RawPoiExcelWriter(String[] headers, int... colChars) throws Exception {
        super(false);
        this.headers = headers;
        this.colChars = colChars;
        initialSheet(getSheet());
    }

    public final void initialSheet(Sheet sheet) throws ExcelOperationException {
        try {
            if (colChars != null && colChars.length == 1) {
                if (headers != null) {
                    for (int i = 0; i < headers.length; i++) {
                        this.setColumnWidth(i, colChars[0]);
                    }
                }
            } else if (colChars != null) {
                this.setColumnWidth(colChars);
            }

            setValues(headerStyle, (Object[]) headers);
        } catch (Exception e) {
            throw new ExcelOperationException(e.getMessage(), e);
        }
    }

    @Override
    protected void createDefaultStyles() {
        // create style for body
        Font font = this.createFont();
        font.setFontName("ARIAL");
        font.setFontHeightInPoints((short) 10);
        super.createDefaultStyles();
        setFormat(defaultStringStyle, font);
        setFormat(defaultMoneyStyle, font);
        setFormat(defaultNumberStyle, font);
        setFormat(defaultDateStyle, font);
        defaultMoneyStyle.setAlignment(HorizontalAlignment.RIGHT);

        // create style for header
        Font headerFont = this.createFont();
        headerFont.setFontName("新細明體");
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle = this.createStyle();
        setFormat(headerStyle, headerFont);
        headerStyle.setBorderBottom(BorderStyle.THICK);
    }

    private void setFormat(CellStyle style, Font font) {
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        setBorder4DirThin(style);

        if (font != null) {
            style.setFont(font);
        }
    }

    public CellStyle getHeaderStyle() {
        return headerStyle;
    }

    public void rowValueWithHeaderStyle(Object... values) {
        setValues(headerStyle, values);
    }
}
