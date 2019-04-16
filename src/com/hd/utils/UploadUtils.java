package com.hd.utils;

import java.util.UUID;

/**
 * �ļ��ϴ��Ĺ�����
 */
public class UploadUtils {
	
	/**
	 * ��ȡ����ļ���
	 * @param fileName
	 * @return
	 */
	public static String getUuidFileName(String fileName){
		int idx = fileName.lastIndexOf(".");
		String extions = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-", "") + extions;  //����ַ���+��չ��
	}
	
	/**
	 * Ŀ¼����
	 * @param args
	 */
	public static String getPath(String uuidFileName){
		int code1 = uuidFileName.hashCode();  //�õ�����ֵ��ռ4���ֽڣ�32λ
		int d1 = code1 & 0xf;  //��Ϊһ��Ŀ¼��0xf��ʮ���Ƶ�15:1111H
		int code2 = code1 >> 4;
		int d2 = code2 & 0xf;  //��Ϊ����Ŀ¼
		return "/" + d1 + "/" + d2;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(getUuidFileName("aa.txt"));
	}
}
