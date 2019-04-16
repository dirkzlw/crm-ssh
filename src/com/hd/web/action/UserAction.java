package com.hd.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hd.entity.User;
import com.hd.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	//ģ������ʹ�õĶ���
	User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	//ע��Service
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/**
	 * �û�ע��ķ���:regist
	 */
	public String regist(){
		
		userService.regist(user);
		
		return LOGIN;
	}
	
	/**
	 * �û���¼�ķ���:login
	 */
	public String login(){
		//����ҵ����ѯ�ͻ�
		User exitUser = userService.login(user);
		if(exitUser == null){
			//��¼ʧ��
			//��Ӵ�����Ϣ
			/*this.addActionError("�û������������");*/
			return LOGIN;
			
		}else{
			//��¼�ɹ�,�û���Ϣ����session
			ActionContext.getContext().getSession().put("exitUser", exitUser);
			return SUCCESS;
		}
	}
	
	public String findAllUser() throws IOException{
		List<User> list = userService.findAll();
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
	
}
