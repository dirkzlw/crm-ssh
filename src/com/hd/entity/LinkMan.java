package com.hd.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * ��ϵ�˵�ʵ��
 */
@Getter @Setter
public class LinkMan {
	
	private Long lkm_id;
	private String lkm_name;
	private String lkm_gender;
	private String lkm_phone;
	private String lkm_mobile;
	private String lkm_email;
	private String lkm_qq;
	private String lkm_position;
	private String lkm_memo;
	
	//����ϵ��һ�����ÿͻ�����
	private Customer customer;

	@Override
	public String toString() {
		return "LinkMan [lkm_id=" + lkm_id + ", lkm_name=" + lkm_name + ", lkm_gender=" + lkm_gender + ", lkm_phone="
				+ lkm_phone + ", lkm_mobile=" + lkm_mobile + ", lkm_email=" + lkm_email + ", lkm_qq=" + lkm_qq
				+ ", lkm_position=" + lkm_position + ", lkm_memo=" + lkm_memo + ", customer=" + customer + "]";
	} 
	
	
}
