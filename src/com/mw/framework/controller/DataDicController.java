package com.mw.framework.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import javax.validation.Valid;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.ssm.Cache;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;
import com.mw.framework.bean.Message;
import com.mw.framework.dao.SysDataDicDao;
import com.mw.framework.domain.SysDataDic;
import com.mw.framework.support.DynamicDataSourceHolder;


@Controller
@RequestMapping("/core/dd/*")
public class DataDicController {
	
	private static final Logger logger = LoggerFactory.getLogger(DataDicController.class);
	
	@Autowired
	public SysDataDicDao dataDicDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Cache appCache;

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String toDataDic(ModelAndView modelAndView){
		return "/core/toDataDic";
	}
	
	
	/**
	 * 根据PID获取数据字典数据(优先缓存)
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/list/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Page<SysDataDic> getDataDicList(@PathVariable String pid,int page, int limit) {
		DynamicDataSourceHolder.setCustomerType("DB01");
		List<Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Order("id"));
		Sort sort = new Sort(orders);
		return dataDicDao.findByPid(pid,new PageRequest(page-1, limit, sort));
	}
	
	/**
	 * 根据PID获取数据字典数据(优先缓存)
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/list2/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<SysDataDic> getDataDicList2(@PathVariable String id) {
		SysDataDic dataDic = dataDicDao.get(id);
		//System.out.println(dataDic);
		/*if(dataDic!=null){
			dataDic.setDescEnUs("woding");
			dataDicDao.saveForSSM(dataDic);
		}*/
		
		System.out.println("DATA:"+dataDic);
		
		try {
			Object object = appCache.get("goodscenter:DataDicDao:"+id, SerializationType.JAVA);
			System.out.println("object:"+object);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (CacheException e) {
			e.printStackTrace();
		}
		/*DataDic dataDic2 = dataDicDao.get(6);
		System.out.println("第二次查询获取缓存服务器数据是否:"+dataDic2);*/
		
		/*MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil
	              .getAddresses("127.0.0.1:11211"));
	    MemcachedClient memcachedClient = null;
		
	    try {
	    	memcachedClient = builder.build();
			//循环查看缓存内的数据	
			Iterator<Map<String, String>> iterSlabs = memcachedClient.getStatsByItem("items").values().iterator();
			Set<String> set = new HashSet<String>();
			while(iterSlabs.hasNext()) {
			    Map<String, String> slab = iterSlabs.next();
			    for(String key : slab.keySet()) {
			        String index = key.split(":")[1];
			        set.add(index);
			        System.out.println(key+"|"+index);
			    }
			}

			//统计
			List<String> list = new LinkedList<String>();
			for(String v : set) {
			    String commond = "cachedump ".concat(v).concat(" 0");
			    Iterator<Map<String, String>> iterItems = memcachedClient.getStatsByItem(commond).values().iterator();
			    while(iterItems.hasNext()) {
			        Map<String, String> items = iterItems.next();
			        list.addAll(items.keySet());
			        System.out.println(items.keySet());
			    }
			}
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return null;
	}
	
/*	*//**
	 * 创建分页请求.
	 *//*
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}*/
 
	
	/**
	 * 根据ID获取数据字段详细信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SysDataDic getDataDic(@PathVariable String id){
		SysDataDic dataDic = dataDicDao.findOne(id);
		return dataDic;
	}
	
	/**
	 * 返回中文字符串的问题
	 * produces （返回json）:text/html;charset=utf-8
	 * produces （返回string）:text/html;charset=utf-8
	 * produces （返回string xml）:text/xml;charset=utf-8
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get2", method = RequestMethod.GET,produces = {"text/json;charset=UTF-8"})
	@ResponseBody
	public String getDataDicResultString(){
		StringBuffer sb = new StringBuffer();
		//获取部门
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("select * from sys_organ LIMIT 3");
		
		sb.append("{id:'root',text:'中文哈哈',children:[");
		dgi(sb, queryForList, "root","");
		sb.append("]}");
		System.out.println(sb);
		return sb.toString();
	}
	
	
	/**
	 * 递归Tree
	 * @param sb
	 * @param list
	 * @param id
	 */
	private void dgi(StringBuffer sb, List<Map<String, Object>> list, String id,String regx) {
		String menuId = "";
		//sb.append("{children:[");
		for (int i = list.size() - 1; i >= 0; i--) {
			Map<String, Object> map = list.get(i);
			/*if(i==0){
				sb.append("],id:"+menuId+",text:"+map.get("dept_name")+"}");
			}*/
			if (id.equals(map.get("pid").toString().trim())) {
				//sb.append(",leaf:false");
				menuId = (String) map.get("id");
				//sb.append("<item id='" + menuId + "' text='" + map.get("dept_name") + "'>");
				sb.append("{id:"+menuId+",text:"+map.get("dept_name")+",children:[");
				dgi(sb, list, menuId,",");
				sb.append("]}");
				sb.append(regx);
			}
			
			
		}
		//sb.append("}");
	}
	
	/**
	 * 保存数据字典(ajax提交)
	 * @param dataDic
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Message save(@Valid SysDataDic dataDic,BindingResult result){
		Message msg = null;
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getField()+"|"+fieldError.getDefaultMessage());
			}
			msg = new Message("DD-V-500",result.toString());
		}  
		
		if(dataDic!=null){
			try {
				logger.debug(dataDic.toString());
				dataDicDao.save(dataDic);
				msg = new Message("ok");
			} catch (Exception e) {
				msg = new Message("DD-S-500",e.getLocalizedMessage());
			}
		}else{
			msg = new Message("DD-S-000","dataDic is null");
		}
		logger.debug(msg.toString());
		return msg;
	}
	
	/**
	 * 保存数据字典(传统表单提交)
	 * @param dataDic
	 * @return
	 */
	@RequestMapping(value = "/save2", method = RequestMethod.GET)
	public String save2(String holder, @Valid SysDataDic dataDic,BindingResult result){
		Message msg = null;
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getField()+"|"+fieldError.getDefaultMessage());
			}
			msg = new Message("DD-V-500",result.toString());
			return "error";
		}  

		if(dataDic!=null){
			try {
				logger.debug(dataDic.toString());
				DynamicDataSourceHolder.setCustomerType(holder);
				dataDicDao.save(dataDic);
				msg = new Message("ok");
			} catch (Exception e) {
				msg = new Message("DD-S-500",e.getLocalizedMessage());
			}
		}else{
			msg = new Message("DD-S-000","dataDic is null");
		}
		logger.debug(msg.toString());
		//return msg;\
		return "success";
	}
	
	/**
	 * 根据ID删除数据字典
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Message delete(@PathVariable String id){
		Message msg = null;
		try {
			dataDicDao.delete(id);
			msg = new Message(null,"ok");
		} catch (Exception e) {
			msg = new Message("DD-D-500",e.getLocalizedMessage());
		}
		return msg;
	}
}
