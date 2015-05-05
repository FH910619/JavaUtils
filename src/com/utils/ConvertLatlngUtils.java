package com.utils;
import java.math.BigDecimal;

public class ConvertLatlngUtils {

	// 经纬度度分秒转换为小数
	public static double convertToDecimal(double du, double fen, double miao) {
		if (du < 0)
			return -(Math.abs(du) + (Math.abs(fen) + (Math.abs(miao) / 60)) / 60);

		return Math.abs(du) + (Math.abs(fen) + (Math.abs(miao) / 60)) / 60;

	}

	// 以字符串形式输入经纬度的转换
	public static double convertToDecimalByString(String latlng) {

		double du = Double
				.parseDouble(latlng.substring(0, latlng.indexOf("°")));
		double fen = Double.parseDouble(latlng.substring(
				latlng.indexOf("°") + 1, latlng.indexOf("′")));
		double miao = Double.parseDouble(latlng.substring(
				latlng.indexOf("′") + 1, latlng.indexOf("″")));
		if (du < 0)
			return -(Math.abs(du) + (fen + (miao / 60)) / 60);
		return du + (fen + (miao / 60)) / 60;

	}

	// 将小数转换为度分秒
	public static String convertToSexagesimal(double num) {

		int du = (int) Math.floor(Math.abs(num)); // 获取整数部分
		double temp = getdPoint(Math.abs(num)) * 60;
		int fen = (int) Math.floor(temp); // 获取整数部分
		double miao = getdPoint(temp) * 60;
		if (num < 0)
			return "-" + du + "°" + fen + "′" + miao + "″";

		return du + "°" + fen + "′" + miao + "″";

	}

	// 获取小数部分
	public static double getdPoint(double num) {
		double d = num;
		int fInt = (int) d;
		BigDecimal b1 = new BigDecimal(Double.toString(d));
		BigDecimal b2 = new BigDecimal(Integer.toString(fInt));
		double dPoint = b1.subtract(b2).floatValue();
		return dPoint;
	}

	/**
	 * "31/1,28/1,14304/1000"; "120/1,16/1,2903/1000";
	 * 先按照“，”分割成‘度时分’,然后按照"/"分割成分子和分母，分别运算，然后得到值
	 * 
	 * @param exif得到的String值
	 * @return 度时double分数组
	 */

	public static Double stringtodouble(String doublestr) {
		Double[] latlon = new Double[3];
		String[] tmpstrs = doublestr.split(",");
		for (int i = 0; i < tmpstrs.length; i++) {
			// System.out.println(latstrs[i]);
			double firstdouble = Double.parseDouble(tmpstrs[i].split("/")[0]);
			double senconddouble = Double.parseDouble(tmpstrs[i].split("/")[1]);
			double tmp = firstdouble / senconddouble;
			System.out.println(tmp);
			latlon[i] = tmp;
		}
		return convertToDecimal(latlon[0], latlon[1], latlon[2]);
	}

	/**
	 * 
	 * @param num
	 *            传进来的double
	 * @return 按照exif形式的经纬度字符串字符串 31/1,28/1,14304/1000
	 */
	public static String doubletostring(double num) {
		int du = (int) Math.floor(Math.abs(num)); // 获取整数部分
		double temp = getdPoint(Math.abs(num)) * 60;
		int fen = (int) Math.floor(temp); // 获取整数部分
		int miao = (int) (getdPoint(temp) * 60 * 1000);
		if (num < 0)
			return "-" + du + "/1," + fen + "/1," + miao + "/1000";

		return du + "/1," + fen + "/1," + miao + "/1000";

	}
}