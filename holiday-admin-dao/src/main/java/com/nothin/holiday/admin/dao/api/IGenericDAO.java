/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: IGenericDAO.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.dao.api;

import java.util.List;
import com.nothin.holiday.admin.dao.domain.BaseDomain;

public interface IGenericDAO<E extends BaseDomain> {
    
    List<E> selectAll();
    
    E findById( Long id );
    
    E findByCode( String code );
    
    int insert( E entity );
    
    void insert( List<E> entities );
    
    int update( E entity );
    
    void update( List<E> entities );
    
    int deleteById( Long id );
    
    int deleteByCode( String code );
    
    int delete( E entity );
    
    void delete( List<E> entities );

}
