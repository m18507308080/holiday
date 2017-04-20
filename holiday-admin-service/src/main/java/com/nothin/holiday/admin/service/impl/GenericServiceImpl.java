/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: GenericServiceImpl.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nothin.holiday.admin.dao.api.IGenericDAO;
import com.nothin.holiday.admin.dao.domain.BaseDomain;
import com.nothin.holiday.admin.service.api.IGenericService;
import com.nothin.holiday.admin.service.utils.GrapeServiceUtils;
import com.nothin.holiday.admin.view.BaseViewObject;

/**
 * 
 * @param <V> BaseViewObject子视图类型
 * @param <E> BaseEntity子实体类型
 * @param <T> GenericServiceImpl子类型
 * 
 * @author 李牧牧
 * @version 1.0
 *
 */
public abstract class GenericServiceImpl<V extends BaseViewObject<V>, 
                                 E extends BaseDomain,
                                 T extends GenericServiceImpl<V, E, T>>
    implements IGenericService<V, E> {

    private static final Logger logger = 
            LoggerFactory.getLogger( GenericServiceImpl.class );
    
    private static final Map<Class<?>, Logger> loggersMap =
            new ConcurrentHashMap<>();
    
    private IGenericDAO<E> genericDAO;
    
    private Class<V> viewObjectClass;
    private Class<E> entityClass;
    private Class<T> serviceImplClass;
    
    @SuppressWarnings("unchecked")
    public GenericServiceImpl( final IGenericDAO<E> genericDAO ) {
        
        this.genericDAO = genericDAO;
        
        Type[] types = ( ( ParameterizedType )getClass()
                .getGenericSuperclass() ).getActualTypeArguments();
        
        viewObjectClass = ( Class<V> )types[0];
        entityClass = ( Class<E> )types[1];
        serviceImplClass = ( Class<T> )types[2];
        
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public V getById( Long id ) {
        E entity = this.genericDAO.findById( id );
        return entity != null ? this.transToViewObject( entity ) : null;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public V getByCode( String code ) {
        E entity = this.genericDAO.findByCode( code );
        return entity != null ? this.transToViewObject( entity ) : null;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public boolean save( V viewObject ) {
        if( viewObject == null ) {
            throw new IllegalArgumentException( 
                    "Argument viewObject is empty!!" );
        }
        
        E entity = this.transToEntity( viewObject );
        return this.genericDAO.insert( entity ) == 1;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public void save( List<V> viewObjects ) {
        if( viewObjects == null || viewObjects.size() == 0 ) {
            throw new IllegalArgumentException( 
                    "Argument viewObjects is empty!!" );
        }
        
        List<E> entities = this.transToEntity( viewObjects );
        this.genericDAO.insert( entities );
        
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public boolean deleteById( Long id ) {
        return this.genericDAO.deleteById( id ) == 1;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public boolean deleteByCode( String code ) {
        return this.genericDAO.deleteByCode( code ) == 1;
    }
    
    protected Logger getLogger() {
        return getLogger( this.serviceImplClass );
    }
    
    private static Logger getLogger( Class<?> clazz ) {
        Logger logger = loggersMap.get( clazz );
        if( logger == null ) {
            logger = LoggerFactory.getLogger( clazz );
            loggersMap.put( clazz, logger );
        }
        return logger;
    }
    
    protected V newViewObject() {
        V obj = null;
        try {
            obj = this.viewObjectClass.newInstance();
        }catch( Exception ex ) {
            logger.error( "GenericServiceImpl.newViewObject exception" , ex );
        }
        return obj;
    }
    
    protected E newEntityObject() {
        E obj = null;
        try {
            obj = this.entityClass.newInstance();
        }catch( Exception ex ) {
            logger.error( "GenericServiceImpl.newViewObject exception" , ex );
        }
        return obj;
    }
    
    protected E transToEntity( V object ) {
        return transToEntity( object, ( Object[] )null );
    }
    
    protected V userCollectViewObject( E entity ) {
        return transToViewObject( entity, ( Object[] )null );
    }
    
    protected List<E> transToEntity( List<V> objects ) {
        return transToEntity( objects, ( Object[] )null );
    }
    
    protected List<E> transToEntity( List<V> objects, Object... options ) {
        List<E> entities = new ArrayList<>();
        if( objects != null ) {
            for( V obj : objects ) {
                entities.add( transToEntity( obj, options ) );
            }
        }
        return entities;
    }
    
    protected List<V> transToViewObject( List<E> entities ) {
        return transToViewObject( entities, ( Object[] )null );
    }
    
    protected List<V> transToViewObject( List<E> entities, Object... options ) {
        List<V> viewObjects = new ArrayList<>();
        if( entities != null ) {
            for( E entity : entities ) {
                viewObjects.add( transToViewObject( entity, options ) );
            }
        }
        return viewObjects;
    }
    
    protected E transToEntity( V viewObject, Object... options ) {
        
        if( viewObject == null ) {
            return null;
        }
        
        E entity = this.newEntityObject();
        
        try {
        	GrapeServiceUtils.copyProperties( entity, viewObject );
        }catch( Exception ex ) {
            logger.error( "GenericServiceImpl.transToEntity exception: ", ex );
        }
        
        return entity;
    }
    
    protected V transToViewObject( E entity, Object... options ) {
        if( entity == null ) {
            return null;
        }
        
        V viewObject = this.newViewObject();
        
        try {
        	GrapeServiceUtils.copyProperties( viewObject, entity );
        }catch( Exception ex ) {
            logger.error( 
                    "GenericServiceImpl.transToViewObject exception: ", ex );
        }
        
        return viewObject;
    }
    
}
