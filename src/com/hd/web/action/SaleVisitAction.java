package com.hd.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hd.entity.Customer;
import com.hd.entity.PageBean;
import com.hd.entity.SaleVisit;
import com.hd.entity.User;
import com.hd.service.CustomerService;
import com.hd.service.SaleVisitService;
import com.hd.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private static final long serialVersionUID = 1L;
	
	//ģ������ʹ�õĶ���,madan��������new��������
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	//��Action��ע��Service,�����ṩset
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	@Resource(name="customerService")
	private CustomerService customerService;
	@Resource(name="userService")
	private UserService userService;
	
	//���շ�ҳ����
	private Integer currentPage =1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	private Integer pageSize =3;
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	//����visit_end_time����
	private Date visit_end_time;
	//����������Ҫget���������action���ԣ�action������ջ
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	/**
	 * ��ѯ�ͻ��ݷü�¼�б�findAll
	 */
	public String findAll(){
		
		//��������������ѯ����
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		//��������
		if(saleVisit.getVisit_time() != null){
			criteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));  //ge��>=
		}
		if(visit_end_time != null){
			criteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		
		//����ҵ���
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(criteria,currentPage,pageSize);
		//����ֵջ
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findAll";
	}
	
	/**
	 * �ݷü�¼��ת�����ҳ��
	 */
	public String saveUI(){
		return "saveUI";
	}
	
	/**
	 * ����ͻ��ݷü�¼
	 */
	public String save(){
		//����ҵ��㱣��ݷü�¼
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	
	/**
	 * ɾ���ͻ��ݷü�¼
	 * @return
	 */
	public String delete(){

		//����id�õ��ݷü�¼
		SaleVisit saleVisit2 = saleVisitService.findById(saleVisit.getVisit_id());
		saleVisitService.delete(saleVisit2);
		
		return "deleteSuccess";
	}
	
	/**
	 * ��ʾ�޸�ҳ��
	 */
	public String edit(){

		//��ȡ��Ҫ���Ե�����
		List<Customer> customerList = customerService.findAll();
		System.out.println(customerList.size());
		List<User> userList = userService.findAll();
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		ActionContext.getContext().getValueStack().set("customerList", customerList);
		//�������ݵ�ֵջ
		ActionContext.getContext().getValueStack().set("userList", userList);
		ActionContext.getContext().getValueStack().push(saleVisit);
		
		return "editSuccess";
	}
	
	/**
	 * �޸İݷ���Ϣ������
	 */
	public String update(){
		//����ҵ���
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}
}

