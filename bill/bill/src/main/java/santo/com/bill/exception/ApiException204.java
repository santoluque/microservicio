package santo.com.bill.exception;

/**
 * Start codes for App error at 204.
 *
 * @date 2023.04.06
 * @author sluque
 * @version 1.0
 */
public class ApiException204 extends RuntimeException {

    private static final long serialVersionUID = 529631755906125770L;

    private final Integer code;

    public ApiException204(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
