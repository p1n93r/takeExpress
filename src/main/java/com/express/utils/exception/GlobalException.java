package com.express.utils.exception;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 19:03
 */
public class GlobalException extends  RuntimeException {
    private static final long serialVersionUID = 1L;
    private CodeMsg cm;
    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
