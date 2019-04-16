package com.hd.intercepter;

import org.apache.struts2.ServletActionContext;

import com.hd.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Ȩ��������
 */
public class PrivilegeIntercepter extends MethodFilterInterceptor{

	//���صķ���
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//�ж�session���Ƿ��е�¼����Ϣ
		User exitUser = (User) ServletActionContext.getRequest().getSession().getAttribute("exitUser");
		if(exitUser == null){
			//��ȡ������Ϣ
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("����û�е�¼��û��Ȩ�޷���");
			return "login";
		}
		
		//�Ѿ���¼
		return invocation.invoke();
	}

}
