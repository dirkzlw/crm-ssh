package com.hd.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * �ͻ��ݷü�¼��ʵ��
 */

@Getter @Setter
public class SaleVisit {
	private String visit_id;
	private Date visit_time;
	private String visit_addr;
	private String visit_detail;
	private Date visit_nexttime;
	
	//�ݷü�¼�����Ŀͻ��Ķ���
	private Customer customer;
	//�������û��Ķ���
	private User user;
	
}
