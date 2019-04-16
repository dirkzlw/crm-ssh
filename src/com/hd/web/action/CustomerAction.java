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
	
	private Customer customer = new Customer();  //ע�⣺�����new����
	@Override
	public Customer getModel() {
		return customer;
	}
	
	//ע��service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//��������,ʹ��set�����ķ�ʽ��������
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){  //���Ϊ��
			currentPage = 1;  //���õ�ǰҳ��Ϊ1
		}
		this.currentPage = currentPage;
	}
	//ʹ��set��������ÿҳ��ʾ�ļ�¼
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){  //���Ϊ��
			pageSize = 3;  //���õ�ǰҳ��Ϊ1
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * �ļ��ϴ��ṩ�������ԣ�
	 * upload�����jspҳ�����ļ���ǩ��name����һ��
	 */
	private String uploadFileName;  //�ļ�����
	private File upload;  //�ϴ����ļ�����
	private String uploadContentType;  //�ļ�����
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
	 * ��ת����ͻ�ҳ��
	 */
	public String saveUI(){
		return "saveUI";
	}
	
	/**
	 * ����ͻ��ķ���
	 * @throws IOException 
	 */
	public String save() throws IOException {
		//�ϴ��ļ����ṩ�������Լ�set����
		if(upload != null){  //��Ϊ�ս����ļ��ϴ���Ϊ��ֱ�ӱ���ͻ�
			//�ļ��ϴ�
			//�����ϴ���·��
			String path ="D:/crm_upload";
			//һ��Ŀ¼�´����ͬ���ļ�:����ļ���
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//һ��Ŀ¼�´�Ź�����ļ���Ŀ¼����
			String realPath = UploadUtils.getPath(uuidFileName);
			//����Ŀ¼
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()){  //·�������ڱ㴴��
				file.mkdirs();
			}
			//�ļ��ϴ�
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//����image����ֵ
			customer.setCust_image(url+"/"+uuidFileName);
		}
		//����ͻ�
		customerService.save(customer);
		
		return "saveSuccess";
	}
	
	/**
	 *	��ҳ��ѯ�ͻ��ķ���findAll 
	 */
	public String findAll(){
		//���ղ���:��ǰҳ��
		//ʹ��DetachedCriteria����(������ѯ--�Դ���ҳ)
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		
		//��������(web��)(ע�⣺jspҳ����Ҫ��������)
		if(customer.getCust_name() != null){
			//�����Ʋ�ѯ
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		if(customer.getBaseDictLevel() != null){
			//�������ѯ
			if(customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		if(customer.getBaseDictSource() != null){
			//����Դ��ѯ
			if(customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		if(customer.getBaseDictIndustry() != null){
			//����ҵ��ѯ
			if(customer.getBaseDictIndustry().getDict_id() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		
		//����ҵ����ѯ
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currentPage,pageSize);
		//��pageBean����ֵջ
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String findByID(){
		
		Customer customer = customerService.findByID(1L);
		System.out.println(customer);
		
		return NONE;
	}
	
	/**
	 * ɾ���ͻ��ķ���:delete
	 * @return
	 */
	public String delete(){
		//�Ȳ�ѯ����ɾ����(������ɾ��)
		customer = customerService.findByID(customer.getCust_id());
		//ɾ��ͼƬ:
		if(customer.getCust_image() !=null){
			File file = new File(customer.getCust_image());
			if(file.exists()){
				file.delete();
			}
		}
		//ɾ���ͻ�
		customerService.delete(customer);
		
		return "deleteSuccess";
	}
	
	/**
	 * �༭�ͻ���Ϣ�ķ���:edit
	 */
	public String edit(){
		//����id��ѯ����תҳ�棬��������
		customer = customerService.findByID(customer.getCust_id());
		/**
		 * ��customer���ݵ�ҳ��:
		 * 1.�ֶ�ѹջ:   �������ݣ�   <s:property value="cust_name"/>
		 * 2.��Ϊģ����������Ĭ����ջ��:
		 * 		��������:<s:property value="model.cust_name"/>
		 */
		//1.�ֶ�ѹջ���л���
//		ActionContext.getContext().getValueStack().push(customer);
		//2.�ڶ���ʲô������д���Զ���ֵջ
		
		return "editSuccess";
	}
	
	/**
	 * �޸Ŀͻ�����:update
	 * @throws IOException 
	 */
	
	public String update() throws IOException{
		System.out.println("web��update");
		//�ļ��Ƿ��ٴ��ϴ�
		if(upload !=null){
			//�ٴ��ϴ��ļ�
			//ɾ��ԭ���ļ�
			String cust_image = customer.getCust_image();
			if(cust_image !=null || !"".equals(cust_image)){
				File file = new File(cust_image);
				file.delete();
			}
			//�����ϴ���·��
			String path ="D:/crm_upload";
			//һ��Ŀ¼�´����ͬ���ļ�:����ļ���
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//һ��Ŀ¼�´�Ź�����ļ���Ŀ¼����
			String realPath = UploadUtils.getPath(uuidFileName);
			//����Ŀ¼
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()){  //·�������ڱ㴴��
				file.mkdirs();
			}
			//�ļ��ϴ�
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//����image����ֵ
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.update(customer);
		
		return "updateSuccess";
	}
	
	public String findAllCustomer() throws IOException{
		List<Customer> list = customerService.findAll();
		//��listתΪjson
		JsonConfig config = new JsonConfig();
		//ȥ������Ҫ������
		config.setExcludes(new String[]{"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
		//ת��json
		JSONArray jsonArray = JSONArray.fromObject(list,config);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
	
}
