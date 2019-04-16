package com.hd.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 */
public class UploadUtils {
	
	/**
	 * 获取随机文件名
	 * @param fileName
	 * @return
	 */
	public static String getUuidFileName(String fileName){
		int idx = fileName.lastIndexOf(".");
		String extions = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-", "") + extions;  //随机字符串+拓展名
	}
	
	/**
	 * 目录分离
	 * @param args
	 */
	public static String getPath(String uuidFileName){
		int code1 = uuidFileName.hashCode();  //得到整形值，占4个字节，32位
		int d1 = code1 & 0xf;  //作为一级目录，0xf是十进制的15:1111H
		int code2 = code1 >> 4;
		int d2 = code2 & 0xf;  //作为二级目录
		return "/" + d1 + "/" + d2;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(getUuidFileName("aa.txt"));
	}
}
