package com.express.common;

import lombok.Data;

import java.util.Properties;
import java.util.Set;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/11 19:53
 */
@Data
public class CollectionBean {
    public Set<String> sets;
    //properties集合

    public Properties props;
    public CollectionBean() {
        System.out.println("初始化。。。。。");
    }

}
