package com.express.service.user;


import com.express.common.TakeExpressResult;
import com.express.utils.Validator.RegisterValidator;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface RegisterService {
    TakeExpressResult register(RegisterValidator registerValidator, HttpServletRequest request);
}
