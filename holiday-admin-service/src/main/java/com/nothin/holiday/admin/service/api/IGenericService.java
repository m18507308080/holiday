/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: IGenericService.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.service.api;

import java.util.List;

import com.nothin.holiday.admin.dao.domain.BaseDomain;
import com.nothin.holiday.admin.view.BaseViewObject;

/**
 * 服务层基类接口
 * 
 * @param <V> BaseViewObject子视图类型
 * @param <E> BaseEntity子实体类型
 * 
 * @author 李牧牧
 * @version 1.0
 *
 */
public interface IGenericService<V extends BaseViewObject<V>, 
                         E extends BaseDomain> {

    public V getById( Long id );
    
    public V getByCode( String code );
    
    public boolean deleteById( Long id );
    
    public boolean deleteByCode( String code );
    
    public boolean save( V viewObject );
    
    public void save( List<V> viewObjects );
    
}
