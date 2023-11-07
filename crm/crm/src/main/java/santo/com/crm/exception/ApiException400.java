package santo.com.crm.exception;

/**
 *
 * @author santo
 */
public class ApiException400 extends RuntimeException {

    private final Integer code;

    public ApiException400(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
