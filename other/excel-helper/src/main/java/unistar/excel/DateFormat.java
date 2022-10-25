package unistar.excel;

public enum DateFormat {
    /**
     * 只有日期
     */
    DATE("yyyy/MM/dd"),
    /**
     * 日期+時間
     */
    DATE_TIME("yyyy/MM/dd hh:mm:ss");
    public final String value;

    DateFormat(String value) {
        this.value = value;
    }

}
