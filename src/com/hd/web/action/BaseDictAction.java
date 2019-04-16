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
	
	//注入service
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	/**
	 * 根据类型名称查询字典:findByTypeCode
	 * @throws IOException 
	 */
	public String findByTypeCode() throws IOException{
		//调用业务层查询
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		//将list转成json----jsonlib/fastjson(将对象/集合转成json)
		
		/**
		 * 	JSONConfig:转JSon的配置对象
		 * 	JSONArray:将数组/list集合转成JSon
		 * 	JSONObject:将对象/Map集合转成JSon
		 */
		
		//使用jsonconfig去掉json部分属性
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_enable","dict_memo"});
		
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		System.out.println(jsonArray.toString());
		
		//将json打印到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
}
