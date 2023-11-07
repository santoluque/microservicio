package santo.com.bill.exception;

import jakarta.persistence.RollbackException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import santo.com.bill.struct.ApiRestHeader;

/**
 *
 * @author santo
 */
@RestControllerAdvice
public class ApiCtrlExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiCtrlExceptionHandler.class);

    //HTTP code 207
    @ExceptionHandler(ApiException207.class)
    @ResponseStatus(value = HttpStatus.MULTI_STATUS)
    public ApiRestHeader handleException(ApiException207 ex, WebRequest request) {
        log.info("Exception 207 " + ex.toString());
        return new ApiRestHeader(
                HttpStatus.MULTI_STATUS.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    // <editor-fold defaultstate="collapsed" desc="BAD_REQUEST_400">
    //-- Uniq Key catch
    @ExceptionHandler({RollbackException.class, UnexpectedRollbackException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiRestHeader handleBadRequest(Exception ex, WebRequest request) {
        String message = null;
        log.error("EE " + ex.getCause().getMessage());
        if (ExceptionUtils.getStackTrace(ex).contains("uniqueplayer")) {
            message = "Player already registered.";
        }

        if (message != null) {
            log.error(message);
            return new ApiRestHeader(603,
                    message,
                    request.getDescription(false));
        }

        return new ApiRestHeader(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));

    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiRestHeader handleBadRequest(org.hibernate.exception.ConstraintViolationException ex, WebRequest request) {
        log.error("ConstraintViolationException", ex);
        return new ApiRestHeader(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiRestHeader handleBadRequest(DataIntegrityViolationException ex, WebRequest request) {
        log.error("DataIntegrityViolationException");
        String msg = null;

        if (ex.getMessage().contains("uniquepersonbyteam")) {
            msg = "Player already registered.";

        } else if (ExceptionUtils.getStackTrace(ex).contains("uniqueteambytourid")) {
            msg = "Team already registered in tournament.";
        }

        if (msg != null) {
            log.error(msg);
            return new ApiRestHeader(603,
                    msg,
                    request.getDescription(false));
        }

        return new ApiRestHeader(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    // </editor-fold>
    //400 BAD REQUEST
    @ExceptionHandler(ApiException400.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiRestHeader handleException(ApiException400 ex, WebRequest request) {
        log.info(" Exception 400 " + ex.toString());
        return new ApiRestHeader(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    //404 DATA NOT FOUND
    @ExceptionHandler(ApiException404.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiRestHeader handleException(ApiException404 ex, WebRequest request) {
        log.info(" AppException 404 " + ex.toString());
        return new ApiRestHeader(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    //409 CONFLICT RESOURCE EXIST
    @ExceptionHandler(ApiException409.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ApiRestHeader handleException(ApiException409 ex, WebRequest request) {
        log.info(" AppException 409" + ex.toString());
        return new ApiRestHeader(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    //SERVER ERROR 500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiRestHeader globalExceptionHandler(Exception ex, WebRequest request) {

        log.info(" Message " + ex.getMessage());
        log.info(" Exception " + ex.toString());

        return new ApiRestHeader(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * ************************************
     * VALIDATION ** ************************************
     */
    /**
     * VALIDATION 400 BAD REQUEST
     *
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiRestHeader handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("info:", request.getDescription(false));

        return new ApiRestHeader(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors);
    }

}
