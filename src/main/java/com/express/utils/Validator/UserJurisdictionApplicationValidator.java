package com.express.utils.Validator;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/18 16:41
 */
@Data
public class UserJurisdictionApplicationValidator {
    @NotNull(message = "申请用户不能为空")
    private String userid;
    @NotNull(message = "申请理由不能为空")
    private String applContent;
    @NotNull(message = "申请状态码不能为空")
    private String status;
    private MultipartFile uploadfile;
}
