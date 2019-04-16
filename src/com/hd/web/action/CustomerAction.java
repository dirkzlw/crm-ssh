package com.hd.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hd.entity.Customer;
import com.hd.entity.PageBean;
import com.hd.service.CustomerService;
import com.hd.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private static final long serialVersionUID = 1L;
	
	private Customer customer = new Customer();  //注意：必须得new对象
	@Override
	public Customer getModel() {
		return customer;
	}
	
	//注入service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//属性驱动,使用set方法的方式接收数据
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){  //如果为空
			currentPage = 1;  //设置当前页数为1
		}
		this.currentPage = currentPage;
	}
	//使用set方法接收每页显示的记录
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){  //如果为空
			pageSize = 3;  //设置当前页数为1
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * 文件上传提供三个属性：
	 * upload必须和jsp页面中文件标签的name保持一致
	 */
	private String uploadFileName;  //文件名称
	private File upload;  //上传的文件本身
	private String uploadContentType;  //文件类型
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 跳转保存客户页面
	 */
	public String saveUI(){
		return "saveUI";
	}
	
	/**
	 * 保存客户的方法
	 * @throws IOException 
	 */
	public String save() throws IOException {
		//上传文件：提供三个属性及set方法
		if(upload != null){  //不为空进行文件上传，为空直接保存客户
			//文件上传
			//设置上传的路径
			String path ="D:/crm_upload";
			//一个目录下存放相同的文件:随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//一个目录下存放过多的文件：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			//创建目录
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()){  //路径不存在便创建
				file.mkdirs();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//设置image属性值
			customer.setCust_image(url+"/"+uuidFileName);
		}
		//保存客户
		customerService.save(customer);
		
		return "saveSuccess";
	}
	
	/**
	 *	分页查询客户的方法findAll 
	 */
	public String findAll(){
		//接收参数:当前页数
		//使用DetachedCriteria对象(条件查询--自带分页)
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		
		//设置条件(web层)(注意：jsp页面需要回显数据)
		if(customer.getCust_name() != null){
			//按名称查询
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		if(customer.getBaseDictLevel() != null){
			//按级别查询
			if(customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		if(customer.getBaseDictSource() != null){
			//按来源查询
			if(customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		if(customer.getBaseDictIndustry() != null){
			//按行业查询
			if(customer.getBaseDictIndustry().getDict_id() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		
		//调用业务层查询
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currentPage,pageSize);
		//将pageBean放入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String findByID(){
		
		Customer customer = customerService.findByID(1L);
		System.out.println(customer);
		
		return NONE;
	}
	
	/**
	 * 删除客户的方法:delete
	 * @return
	 */
	public String delete(){
		//先查询，再删除。(做级联删除)
		customer = customerService.findByID(customer.getCust_id());
		//删除图片:
		if(customer.getCust_image() !=null){
			File file = new File(customer.getCust_image());
			if(file.exists()){
				file.delete();
			}
		}
		//删除客户
		customerService.delete(customer);
		
		return "deleteSuccess";
	}
	
	/**
	 * 编辑客户信息的方法:edit
	 */
	public String edit(){
		//根据id查询，跳转页面，回显数据
		customer = customerService.findByID(customer.getCust_id());
		/**
		 * 将customer传递到页面:
		 * 1.手动压栈:   回显数据：   <s:property value="cust_name"/>
		 * 2.因为模型驱动对象默认在栈顶:
		 * 		回显数据:<s:property value="model.cust_name"/>
		 */
		//1.手动压栈进行回显
//		ActionContext.getContext().getValueStack().push(customer);
		//2.第二种什么都不用写，自动在值栈
		
		return "editSuccess";
	}
	
	/**
	 * 修改客户方法:update
	 * @throws IOException 
	 */
	
	public String update() throws IOException{
		System.out.println("web层update");
		//文件是否再次上传
		if(upload !=null){
			//再次上传文件
			//删除原有文件
			String cust_image = customer.getCust_image();
			if(cust_image !=null || !"".equals(cust_image)){
				File file = new File(cust_image);
				file.delete();
			}
			//设置上传的路径
			String path ="D:/crm_upload";
			//一个目录下存放相同的文件:随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//一个目录下存放过多的文件：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			//创建目录
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()){  //路径不存在便创建
				file.mkdirs();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//设置image属性值
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.update(customer);
		
		return "updateSuccess";
	}
	
	public String findAllCustomer() throws IOException{
		List<Customer> list = customerService.findAll();
		//将list转为json
		JsonConfig config = new JsonConfig();
		//去掉不需要的属性
		config.setExcludes(new String[]{"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
		//转成json
		JSONArray jsonArray = JSONArray.fromObject(list,config);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
	
}
