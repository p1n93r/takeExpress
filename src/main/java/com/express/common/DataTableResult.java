package com.express.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.apache.commons.fileupload.util.LimitedInputStream;

import java.util.List;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/15 18:00
 */
@Data
public class DataTableResult {
    /*
    * Datatables发送的draw是多少那么服务器就返回多少
    * */

    private Integer draw;
    /*
    * 即没有过滤的记录数（数据库里总共记录数）
    * */
    private Integer recordsTotal;
    /*
    * 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
    * */
    private Integer recordsFiltered;
    /*
    * 表中中需要显示的数据。这是一个对象数组，
    * 也可以只是数组，区别在于 纯数组前台就不需要用 columns绑定数据，
    * 会自动按照顺序去显示 ，而对象数组则需要使用 columns绑定数据才能正常显示。
     * */

    private List<?> data;
    private String error;

    /*
    * 该参数用于非dataTable返回时必须初始化的参数
    * 需要进行普通分页时需要初始化  data、 pageInfo，其他信息前端可以通过pageInfo获取
    * */

    private PageInfo<?> pageInfo;
}
