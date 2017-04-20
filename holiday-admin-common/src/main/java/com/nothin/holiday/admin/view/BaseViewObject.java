/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: BaseViewObject.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.view;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 视图类基类
 * 
 * @author 李牧牧
 * @version 1.0
 *
 */
public class BaseViewObject<V extends BaseViewObject<V>> 
        implements Serializable {

    private static final long serialVersionUID = 8950660914544659827L;

    private Class<V> viewObjectClass;
    
    private Long id;					// 表关键字
    
	private String RowUuid; 			// 表的行记录唯一标示
	private Long Deleted; 				// 是否逻辑删除,0表示未删除
	
	private Long createId; 			// 创建人ID
	private Timestamp createTime; 		// 创建时间
	private Long updateId; 			// 修改人ID
	private Timestamp updateTime;		// 修改时间
	
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	private String attribute11;
	private String attribute12;
	private String attribute13;
	private String attribute14;
	private String attribute15;
	private String attribute16;
	private String attribute17;
	private String attribute18;
	private String attribute19;
	private String attribute20;
    
    public Class<V> getViewObjectClass() {
		return viewObjectClass;
	}

	public void setViewObjectClass(Class<V> viewObjectClass) {
		this.viewObjectClass = viewObjectClass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRowUuid() {
		return RowUuid;
	}

	public void setRowUuid(String rowUuid) {
		RowUuid = rowUuid;
	}

	public Long getDeleted() {
		return Deleted;
	}

	public void setDeleted(Long deleted) {
		Deleted = deleted;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	public String getAttribute15() {
		return attribute15;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	public String getAttribute16() {
		return attribute16;
	}

	public void setAttribute16(String attribute16) {
		this.attribute16 = attribute16;
	}

	public String getAttribute17() {
		return attribute17;
	}

	public void setAttribute17(String attribute17) {
		this.attribute17 = attribute17;
	}

	public String getAttribute18() {
		return attribute18;
	}

	public void setAttribute18(String attribute18) {
		this.attribute18 = attribute18;
	}

	public String getAttribute19() {
		return attribute19;
	}

	public void setAttribute19(String attribute19) {
		this.attribute19 = attribute19;
	}

	public String getAttribute20() {
		return attribute20;
	}

	public void setAttribute20(String attribute20) {
		this.attribute20 = attribute20;
	}

	@SuppressWarnings("unchecked")
    public BaseViewObject() {
        Type type = getClass().getGenericSuperclass();
        if( type != null && type instanceof ParameterizedType ) {
            viewObjectClass = ( Class<V> )( ( ParameterizedType )type )
                    .getActualTypeArguments()[0];
        }else {
            throw new IllegalArgumentException( 
                    "Type argument <V> is empty!!" );
        }
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString( this, 
                ToStringStyle.MULTI_LINE_STYLE );
    }
    
    @SuppressWarnings("unchecked")
    @Override
    final public boolean equals( Object object ) {
        boolean b = false;
        if( object == null ) {
            b = false;
        }else if( object == this ) {
            b = true;
        }else if( object.getClass() == this.viewObjectClass ) {
            b = equals( ( V )object );
        }
        return b;
    }
    
    public boolean equals( V object ) {
        return super.equals( object );
    }
    
    
}
