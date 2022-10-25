package unistar.excel;

import java.io.Serial;

/**
 * Exception for error occurred from excel operation.
 *
 * @author harvey.cheng(cerberusrei @ yahoo.com)
 */
public class ExcelOperationException extends Exception {

    @Serial
    private static final long serialVersionUID = 7018312926599791274L;

    public ExcelOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
