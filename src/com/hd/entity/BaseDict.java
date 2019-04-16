package com.hd.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * ×ÖµäµÄÊµÌå
 * @author Dirk  Zhou
 *
 */
@Getter @Setter
public class BaseDict {
	private String dict_id;
	private String dict_type_code;
	private String dict_type_name;
	private String dict_item_name;
	private String dict_item_code;
	private Integer dict_sort;
	private String dict_enable;
	private String dict_memo;
}
