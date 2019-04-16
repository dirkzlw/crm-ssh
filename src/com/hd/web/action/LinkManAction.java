package com.hd.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hd.entity.Customer;
import com.hd.entity.LinkMan;
import com.hd.entity.PageBean;
import com.hd.service.CustomerService;
import com.hd.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	//ģ������ʹ�õĶ���
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	//ע��LinkmanService
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	//ע��CustomerService
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//��ҳ������
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	/**
	 * ��ѯ��ϵ���б�
	 */
	public String findAll(){
		//��������������ѯ
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		//��������
		if(linkMan.getLkm_name() != null){
			//���ð����Ʋ�ѯ������
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())){
			//���ð��Ա��ѯ������
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		//����ҵ���
		PageBean pageBean = linkManService.findAll(detachedCriteria,currentPage,pageSize);
		//��pageBean����ֵջ
		ActionContext.getContext().getValueStack().push(pageBean);
		System.out.println(pageBean);
		
		return "findAll";
	}

	/**
	 * ��תҳ�溯��
	 */
	public String saveUI(){
		//��ѯ���пͻ�
		List<Customer> list = customerService.findAll();
		//��list���ϱ��浽ֵջ
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "saveUI";
	}
	
	/**
	 * ����ͻ��ķ���
	 */
	public String save(){
		//����ҵ��㱣����ϵ��
		linkManService.save(linkMan);
		System.out.println(linkMan);
		
		return "saveSuccess";
	}
	
	/**
	 * ��ת���༭ҳ��ķ�����edit
	 */
	public String edit(){
		//��ѯ���пͻ������л���
		List<Customer> list = customerService.findAll();
		//����id��ѯ��ϵ�˽��л���
		linkMan = linkManService.findById(linkMan.getLkm_id()); 
		//�����ϺͶ�����л���;ע�⣺����д����ǩ�д���name�����Զ�����
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		
		return "editSuccess";
	}
	
	/**
	 * �޸���ϵ����Ϣ������
	 */
	public String update(){
		//����ҵ���
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/**
	 * ɾ����ϵ��
	 */
	public String delete(){
		//����id�Ȳ������ϵ��
		linkMan = linkManService.findById(linkMan.getLkm_id());
		//ɾ������ϵ��
		linkManService.delete(linkMan);
		
		return "deleteSuccess";
	}
	
}
