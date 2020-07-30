package com.express.utils.exception;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 19:01
 */
public class ExceptionResult<T> {
        private int code;
        private String msg;
        private T data;
        /**
         * 请求成功时调用
         * @param data
         * @return
         */
        public static <T> ExceptionResult<T> success(T data){
            return new ExceptionResult<T>(data);
        }

        public static <T> ExceptionResult<T> error(CodeMsg cm){
            return new ExceptionResult<T>(cm);
        }

        /**
         * 只传入数据默认成功，所以添加默认的code和msg
         * @param data
         */
        private ExceptionResult(T data) {
            this.code=0;
            this.msg="success";
            this.data=data;
        }

        private ExceptionResult(CodeMsg cm) {
            if(cm==null){
                return;
            }
            this.code=cm.getCode();
            this.msg=cm.getMsg();
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public T getData() {
            return data;
        }


}
