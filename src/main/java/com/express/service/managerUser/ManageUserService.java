package com.express.service.managerUser;
import com.express.common.DataTableResult;
import com.express.common.TakeExpressResult;
import com.express.utils.Validator.UserJurisdictionApplicationValidator;
import javax.servlet.http.HttpServletRequest;


/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/18 11:26
 */
public interface ManageUserService {
    //制裁用户，
    /*
    * 系统业务的实现
    * */

    TakeExpressResult sanctiosUser(Integer user_id, String status, String content, HttpServletRequest request);
    TakeExpressResult UserJurisdictionApplication(UserJurisdictionApplicationValidator ujav, HttpServletRequest request);
    TakeExpressResult auditUserJurisdictionApplicaton(Integer userid, String status,String content,Boolean check,HttpServletRequest request);

    /*
    * 管理员审核信息前将数据层数据返回到前端
    * @param page  当前页面显示的页码
    * @param rows  每页显示的记录数
    * */

    //显示注册用户分页

    DataTableResult registerUser(int page, int rows, String search);
    //显示制裁用户人员分页

    DataTableResult ScantionUser(int page, int rows,String search);
    //显示用户权限申请分页

    DataTableResult UserJurisdictionApplicationShow(int page, int rows, String search);
    //删除不合法的权限申请

    Integer deleteApplicationUser(String id);
}
