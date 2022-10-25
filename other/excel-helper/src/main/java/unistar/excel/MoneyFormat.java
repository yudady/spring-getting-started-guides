package unistar.excel;

public enum MoneyFormat {
    /**
     * 一般
     */
    NORMAL("#,##0"),
    /**
     * 負數用括號代表(金流)
     */
    BILLING("#,##0;(#,##0)");

    public final String value;

    MoneyFormat(String value) {
        this.value = value;
    }

}
