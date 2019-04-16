package com.hd.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageBean<T> {
	private Integer currentPage;  
	private Integer pageSize;  //每页显示记录数
	private Integer totalCount;  //总记录数
	private Integer totalPage;  //总页数
	private List<T> list;  //T表示泛型，之前先要声明
	
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", list=" + list + "]";
	}
	
	
}
