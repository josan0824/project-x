package com.yc.javabasic.test;
 
 
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * @author: ZhangHouYing
 * @date: 2021-09-23 21:15
 */
public class PinYinUtil {
	public static void main(String[] args) {
		System.out.println(getPinYin("中国红")); //--zhongguohong123
	}

	/**
	 * @param china (字符串 汉字)
	 * @return 汉字转拼音 其它字符不变
	 */
	public static String getPinyin(String china){
		HanyuPinyinOutputFormat formart = new HanyuPinyinOutputFormat();
		formart.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		formart.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		formart.setVCharType(HanyuPinyinVCharType.WITH_V);
		char[] arrays = china.trim().toCharArray();
		String result = "";
		try {
			for (int i=0;i<arrays.length;i++) {
				char ti = arrays[i];
				if(Character.toString(ti).matches("[\\u4e00-\\u9fa5]")){ //匹配是否是中文
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(ti,formart);
					result += temp[0];
				}else{
					result += ti;
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
 
		return result;
	}
 
	public static String getPinYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		//t3是全部的拼音，不带声调
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
 
		//format是全部的拼音并且带声调
		HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
 
 
 
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches(
						"[\\u4E00-\\u9FA5]+")) {
//					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], format);
					t4 += t2[0]+" ";
				} else {
					t4 += java.lang.Character.toString(t1[i]);
				}
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}
}