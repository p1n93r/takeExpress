package com.express.utils.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 21:36
 */
public class ValidatorException {
    public static String getValidatorException(BindingResult result){
        String msg = "";
        if (result.getFieldErrorCount() > 0) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            if (fieldErrors.isEmpty() || fieldErrors==null){
                return null;
            }
            for (int i = 0;i<fieldErrors.size();i++) {
                msg = msg+fieldErrors.get(i).getDefaultMessage()+";";
            }
        }
        return msg;
    }
}
