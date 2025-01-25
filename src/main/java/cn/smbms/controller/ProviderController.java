package cn.smbms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.ProviderService;
import cn.smbms.tools.Page;

@Controller
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	/**
	 * 进入供货商页面显示数据
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "providerlist.html", method = RequestMethod.GET)
	public String provider(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", 1);
		map.put("size", 5);
		Page<Provider> page = providerService.findProviderByPage(map);
		model.addAttribute("page", page);
		return "providerlist";
	}
	
	/**
	 * 查询供货商数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "providerlist.html", method = RequestMethod.POST)
	public String providerShow(
			Model model,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "queryProName", required = false) String queryProName,
			@RequestParam(value = "queryProCode", required = false) String queryProCode
			) {
		if ("".equals(pageIndex) || null == pageIndex) {
			pageIndex = "1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (queryProName != null && !"".equals(queryProName)) {
			map.put("proName", queryProName);
		}
		if (queryProCode != null && !"".equals(queryProCode)) {
			map.put("proCode", queryProCode);
		}
		map.put("index", pageIndex);
		map.put("size", 5);
		Page<Provider> page = providerService.findProviderByPage(map);
		model.addAttribute("page", page);
		model.addAttribute("queryProName", map.get("proName"));
		model.addAttribute("queryProCode", map.get("proCode"));
		return "providerlist";
	}
	
	/**
	 * 跳转到添加页面
	 *
	 * @return
	 */
	@RequestMapping(value="provideradd.html",method = RequestMethod.GET)
	public String providerAdd(){
		return "provideradd";
	}
	
	/**
	 * 跳转到详细信息界面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "providerview.html", method = RequestMethod.GET)
	public String providerViwe(Model model
			,@RequestParam(value = "proid", required = false) String proid) {
		Provider provider = providerService.findProviderByid(Integer.parseInt(proid));
		model.addAttribute("provider",provider);
		return "providerview";
	}
	
	/**
	 * 跳转到更新页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "providermodify.html", method = RequestMethod.GET)
	public String providerModify(Model model
			,@RequestParam(value = "proid", required = false)String proid) {
		Provider provider = providerService.findProviderByid(Integer.parseInt(proid));
		model.addAttribute("provider",provider);
		return "providermodify";
	}
	
	/**
	 * 通过ID删除对象
	 *
	 * @return
	 */
	@RequestMapping(value = "providerdel.json",method=RequestMethod.POST)
	@ResponseBody
	public Object providerdel(@RequestParam(value = "proid", required = false)String proid) {
		JSONObject json = new JSONObject();
		int count = providerService.findBillByProviderid(Integer.parseInt(proid));
		if(json.get("delResult") != null){
			json.remove("delResult");
		}
		if(count < 1){
			Integer result = providerService.delectProvider(Integer.parseInt(proid));
			if(result == 1){
				json.put("delResult", "true");
			}else{
				json.put("delResult", "false");
			}
		}else{
			json.put("delResult", count);
		}
		return json;
	}
	
	/**
	 * 更新供货商信息
	 *
	 * @param provider
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "providermodify.html", method = RequestMethod.POST)
	public String providerModify(Provider provider, HttpSession session) {
		if(session.getAttribute("USER_CODE")!=null){
			User u = (User)session.getAttribute("USER_CODE");
			provider.setModifyBy(u.getId());
		}
		provider.setModifyDate(new Date());
		Integer result = providerService.updateProvider(provider);
		if(result > 0){
			return "redirect:providerlist.html";
		}else{
			return "providermodify";
		}
	}

	/**
	 * 添加供货商信息
	 *
	 * @param provider
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "providerAdd.html", method = RequestMethod.POST)
	public String providerAdd(Provider provider, HttpSession session) {
		if(session.getAttribute("USER_CODE")!=null){
			User u = (User)session.getAttribute("USER_CODE");
			provider.setCreatedBy(u.getId());
		}
		provider.setCreationDate(new Date());
		Integer result = providerService.addProvider(provider);
		if(result > 0){
			return "redirect:providerlist.html";
		}else{
			return "provideradd";
		}
	}
	
}
