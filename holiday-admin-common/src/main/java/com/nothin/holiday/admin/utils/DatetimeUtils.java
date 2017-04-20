/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: DatetimeUtils.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DatetimeUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(DatetimeUtils.class);

	private static long ONE_DAY_IN_MILISECONDS = 24 * 60 * 60 * 1000;

	public static Timestamp afterOneWeek(Timestamp datetime) {

		if (datetime == null) {
			throw new IllegalArgumentException("Argument datetime is null!!");
		}

		Calendar c = Calendar.getInstance();
		c.setTime(datetime);
		c.add(Calendar.WEEK_OF_YEAR, 1);
		return new Timestamp(c.getTime().getTime());
	}

	public static long daysBetween(Timestamp datetime1, Timestamp datetime2) {

		Timestamp currentTimestamp = currentTimestamp();

		datetime1 = (datetime1 == null) ? currentTimestamp : datetime1;
		datetime2 = (datetime2 == null) ? currentTimestamp : datetime2;

		long delta = Math.abs(datetime2.getTime() - datetime1.getTime());
		return (long) Math.ceil(delta * 1.0 / ONE_DAY_IN_MILISECONDS);
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp parseTimestamp(String timestamp) {

		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATETIME_PATTERN);
		try {
			return new Timestamp(sdf.parse(timestamp).getTime());
		} catch (Exception ex) {
			logger.error("\"" + timestamp + "\" is invalid,"
					+ " it should be in pattern " + " \""
					+ Constants.DATETIME_PATTERN + "\"", ex);
		}
		return null;
	}

	public static String formatDate(Date timestamp) {
		return timestamp == null ? "" : new SimpleDateFormat(
				Constants.DATETIME_PATTERN).format(timestamp);
	}

	public static String formatTimestamp(Timestamp timestamp) {
		return formatDate(timestamp);
	}

	public static String formatTimestamp(Timestamp timestamp, String parttern) {
		return formatTimestamp(timestamp, parttern);
	}

	public static String formatDate(Date date, String parttern) {
		parttern = (StringUtils.isEmpty(parttern)) ? Constants.DATETIME_PATTERN
				: parttern;
		SimpleDateFormat sdf = new SimpleDateFormat(parttern);
		try {
			return sdf.format(date);
		} catch (Exception ex) {
			logger.error("\"" + date + "\" is invalid,"
					+ " it should be in pattern " + " \"" + parttern + "\"", ex);
		}
		return null;
	}
}
