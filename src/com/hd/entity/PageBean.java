package com.hd.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageBean<T> {
	private Integer currentPage;  
	private Integer pageSize;  //ÿҳ��ʾ��¼��
	private Integer totalCount;  //�ܼ�¼��
	private Integer totalPage;  //��ҳ��
	private List<T> list;  //T��ʾ���ͣ�֮ǰ��Ҫ����
	
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", list=" + list + "]";
	}
	
	
}
