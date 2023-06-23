package io09.excel;

public enum MoneyFormat {

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
