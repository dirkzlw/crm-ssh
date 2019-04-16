package com.hd.intercepter;

import org.apache.struts2.ServletActionContext;

import com.hd.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 */
public class PrivilegeIntercepter extends MethodFilterInterceptor{

	//拦截的方法
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//判断session中是否有登录的信息
		User exitUser = (User) ServletActionContext.getRequest().getSession().getAttribute("exitUser");
		if(exitUser == null){
			//存取错误信息
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("您还没有登录！没有权限访问");
			return "login";
		}
		
		//已经登录
		return invocation.invoke();
	}

}
