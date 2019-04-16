package com.hd.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
	
	private Long cust_id;
	private String cust_name;
//	private Long cust_user_id;
//	private Long cust_create_id;
// 	private String cust_linkman;
	private String cust_phone;
	private String cust_mobile;
	private String cust_image;  //�ͻ�������ͼƬ
	/**
	 * �ͻ����ֵ���Ƕ��һ�Ĺ�ϵ:�ڶ��һ�����洢һ�Ķ���
	 */
	private BaseDict baseDictSource;
	private BaseDict baseDictIndustry;
	private BaseDict baseDictLevel;
	/**
	 * һ���ͻ��ж����ϵ��
	 */
	private Set<LinkMan> linkMans = new HashSet<>();
	
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_user_id="
				+ ", cust_create_id="  + ", cust_linkman=" + ", cust_phone=" + cust_phone
				+ ", cust_mobile=" + cust_mobile + "]";
	}
}
