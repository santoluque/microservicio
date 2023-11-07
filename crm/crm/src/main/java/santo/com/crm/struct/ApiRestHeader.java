package santo.com.crm.struct;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author santo
 */
@JsonPropertyOrder({"code", "message", "data"})
public class ApiRestHeader {

    @Schema(description = "Status code execution", example = "200")
    private Integer code;
    @Schema(description = "Message status execution", example = "Success")
    private String message;
    @Schema(description = "Data json object", example = "[{...}]")
    private Object data;

    public ApiRestHeader() {
    }

    public ApiRestHeader(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
