package com.hd.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户的实体类
 * @author Dirk  Zhou
 *
 */
@Getter @Setter
public class User {
	private Long user_id;
	private String user_code;
	private String user_name;
	private String user_password;
	private String user_state;
}
