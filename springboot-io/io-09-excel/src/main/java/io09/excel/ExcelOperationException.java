package io09.excel;

import java.io.Serial;


public class ExcelOperationException extends Exception {

    @Serial
    private static final long serialVersionUID = 7018312926599791274L;

    public ExcelOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
