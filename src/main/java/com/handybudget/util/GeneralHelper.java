
package com.handybudget.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneralHelper {

	public static boolean isEmptyString(String value) {

		if (value == null || value.trim().isEmpty()) {
			return true;
		}
		return false;

	}

	public static String getRandomString() {

		return RandomStringUtils.randomAlphabetic(10);

	}

	public static Date getFirstDayOfMonth(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	public static Date getLastDayOfMonth(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

}
