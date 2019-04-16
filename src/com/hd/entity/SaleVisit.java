package com.hd.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 客户拜访记录的实体
 */

@Getter @Setter
public class SaleVisit {
	private String visit_id;
	private Date visit_time;
	private String visit_addr;
	private String visit_detail;
	private Date visit_nexttime;
	
	//拜访记录关联的客户的对象
	private Customer customer;
	//关联的用户的对象
	private User user;
	
}
