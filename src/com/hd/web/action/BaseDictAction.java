package com.hd.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hd.entity.BaseDict;
import com.hd.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict>{

	private static final long serialVersionUID = 1L;
	
	private BaseDict baseDict = new BaseDict();
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	
	//ע��service
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	/**
	 * �����������Ʋ�ѯ�ֵ�:findByTypeCode
	 * @throws IOException 
	 */
	public String findByTypeCode() throws IOException{
		//����ҵ����ѯ
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		//��listת��json----jsonlib/fastjson(������/����ת��json)
		
		/**
		 * 	JSONConfig:תJSon�����ö���
		 * 	JSONArray:������/list����ת��JSon
		 * 	JSONObject:������/Map����ת��JSon
		 */
		
		//ʹ��jsonconfigȥ��json��������
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_enable","dict_memo"});
		
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		System.out.println(jsonArray.toString());
		
		//��json��ӡ��ҳ��
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
}
