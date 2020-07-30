package com.express.web.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class BaseUrlTag extends SimpleTagSupport {
    /**
     *为了得到basePath，需要一个HttpServletRequest对象
     */
    HttpServletRequest request;

    /**
     * 打印网站的basePath
     */
    @Override
    public void doTag() throws JspException, IOException {
        String path = request.getContextPath();
        String basePath = "<base href='"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/'/>";
        getJspContext().getOut().write(basePath);
    }
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
