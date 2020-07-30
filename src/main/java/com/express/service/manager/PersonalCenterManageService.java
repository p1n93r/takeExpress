package com.express.service.manager;

import com.baidu.aip.face.AipFace;
import com.express.common.TakeExpressResult;
import com.express.domain.Manager;
import com.express.utils.Validator.ModifyBaseInfoValidator;
import com.express.utils.Validator.ModifyPasswordValidator;
import com.express.utils.Validator.ModifyPhoneValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 9:51
 */
public interface PersonalCenterManageService {
    TakeExpressResult modifyPassword(ModifyPasswordValidator modifyPasswordValidator, HttpServletRequest request);
    TakeExpressResult modifyPhone(ModifyPhoneValidator modifyPhoneValidator, HttpServletRequest request);
    TakeExpressResult modifyManagerBaseInfo(ModifyBaseInfoValidator modifyBaseInfoValidator, HttpServletRequest request);
    Map<String,String> facesetAddUser(AipFace client, Manager manager, String img);
}
