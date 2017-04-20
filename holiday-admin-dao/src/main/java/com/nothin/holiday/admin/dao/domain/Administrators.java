/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: Administrators.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.dao.domain;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("administrators")
public class Administrators extends BaseDomain {

	private String account; 		// 登陆账户名
	private String password; 		// 登陆密码
	private Long accountLevel; 	// 账户等级
	private String nickName; 		// 呢称
	private String realName; 		// 真实姓名
	private String phoneNumber; 	// 手机号码
	private String emailAddress; 	// 邮箱地址
	private String idNumber; 		// 身份证号码
	private Timestamp brithday; 	// 生日
	private Long sex; 				// 性别

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(Long accountLevel) {
		this.accountLevel = accountLevel;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Timestamp getBrithday() {
		return brithday;
	}

	public void setBrithday(Timestamp brithday) {
		this.brithday = brithday;
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

}
