package com.sbs.java.ssg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String getNowDatestr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		return format.format(time);
	}

}
