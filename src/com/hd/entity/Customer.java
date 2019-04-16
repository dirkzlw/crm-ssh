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
	private String cust_image;  //客户的资质图片
	/**
	 * 客户和字典表是多对一的关系:在多的一方，存储一的对象
	 */
	private BaseDict baseDictSource;
	private BaseDict baseDictIndustry;
	private BaseDict baseDictLevel;
	/**
	 * 一个客户有多个联系人
	 */
	private Set<LinkMan> linkMans = new HashSet<>();
	
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_user_id="
				+ ", cust_create_id="  + ", cust_linkman=" + ", cust_phone=" + cust_phone
				+ ", cust_mobile=" + cust_mobile + "]";
	}
}
