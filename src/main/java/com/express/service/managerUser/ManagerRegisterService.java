package com.express.service.managerUser;

import com.express.common.TakeExpressResult;

import javax.servlet.http.HttpServletRequest;

public interface ManagerRegisterService {
    TakeExpressResult checkRegister(String registerid, String status, String content, HttpServletRequest request);
}
