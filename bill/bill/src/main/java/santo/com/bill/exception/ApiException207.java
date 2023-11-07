package santo.com.bill.exception;

/**
 * Start codes for App error at 201.
 *
 * @date 2023.04.06
 * @author sluque
 * @version 1.0
 */
public class ApiException207 extends RuntimeException {

    private static final long serialVersionUID = -2786527129680405435L;

    private final Integer code;

    public ApiException207(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
