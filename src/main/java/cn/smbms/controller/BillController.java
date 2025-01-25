package cn.smbms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.BillService;
import cn.smbms.service.ProviderService;
import cn.smbms.tools.Page;

@Controller
public class BillController {

	@Autowired
	private BillService billService;

	@Autowired
	private ProviderService providerService;

	/**
	 * 销售管理页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "billlist.html", method = RequestMethod.GET)
	public String billlist(Model model) {
		List<Provider> list = providerService.showProviderInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", 1);
		map.put("size", 5);
		Page<Bill> page = billService.findBillByPage(map);
		model.addAttribute("providerList", list);
		model.addAttribute("page", page);
		return "billlist";
	}
	
	/**
	 * 进入销售管理页面显示数据
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "billlist.html", method = RequestMethod.POST)
	public String billlists(Model model,
			@RequestParam(value="pageIndex",required=false)String pageIndex,
			@RequestParam(value="queryProductName",required=false)String queryProductName,
			@RequestParam(value="queryProviderId",required=false)String queryProviderId,
			@RequestParam(value="queryIsPayment",required=false)String queryIsPayment
			) {
		List<Provider> list = providerService.showProviderInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		if(queryProductName != null && !"".equals(queryProductName)){
			map.put("productName", queryProductName);
		}
		if(queryProviderId != null && !"".equals(queryProviderId) && !"0".equals(queryProviderId)){
			map.put("providerId", queryProviderId);
		}
		if(queryIsPayment != null && !"".equals(queryIsPayment) && !"0".equals(queryIsPayment)){
			map.put("isPayment", queryIsPayment);
		}
		map.put("index", pageIndex);
		map.put("size", 5);
		Page<Bill> page = billService.findBillByPage(map);
		model.addAttribute("page", page);
		model.addAttribute("providerList", list);
		model.addAttribute("queryProductName", map.get("productName"));
		model.addAttribute("queryProviderId", map.get("providerId"));
		model.addAttribute("queryIsPayment", map.get("isPayment"));
		return "billlist";
	}
	
	/**
	 * 跳转到订单添加页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value="billadd.html",method = RequestMethod.GET)
	public String billadd(Model model){
		return "billadd";
	}
	
	/**
	 * 跳转到订单详细页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value="billview.html",method = RequestMethod.GET)
	public String billview(Model model,
			@RequestParam(value="billid",required = true)String billid){
		Bill bill = billService.findBillByid(Integer.parseInt(billid));
		model.addAttribute("bill", bill);
		return "billview";
	}
	
	/**
	 * 跳转到订单更新页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value="billmodify.html",method = RequestMethod.GET)
	public String billmodify(Model model,
			@RequestParam(value="billid",required = true)String billid){
		Bill bill = billService.findBillByid(Integer.parseInt(billid));
		model.addAttribute("bill", bill);
		return "billmodify";
	}
	
	/**
	 * 获取供应商列表
	 *
	 * @return
	 */
	@RequestMapping("providerlist.json")
	@ResponseBody
	public Object providerjson(){
		List<Provider> list = providerService.showProviderInfo();
		return JSON.toJSONString(list);
	}
	
	/**
	 * 通过ID删除对象
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "billdel.json",method=RequestMethod.POST)
	@ResponseBody
	public Object billdel(Model model
			,@RequestParam(value = "billid", required = false)String billid) {
		JSONObject json = new JSONObject();
		Integer result = billService.delectBill(Integer.parseInt(billid));
		if(result == 1){
			json.put("delResult", "true");
		}else{
			json.put("delResult", "false");
		}
		return json;
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteByIds",method=RequestMethod.GET)
	@ResponseBody
	public String deleteByIds(String[] ids) {
		try {
			System.out.println("ssssss" + ids);
			billService.deleteList(ids);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	/**
	 * 更新订单信息
	 *
	 * @param bill
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "billmodify.html", method = RequestMethod.POST)
	public String billmodify(Bill bill, HttpSession session) {
		if(session.getAttribute("USER_CODE")!=null){
			User u = (User)session.getAttribute("USER_CODE");
			bill.setModifyBy(u.getId());
		}
		bill.setModifyDate(new Date());
		Integer result = billService.updateBill(bill);
		if(result > 0){
			return "redirect:billlist.html";
		}else{
			return "billmodify";
		}
	}

	/**
	 * billAdd  添加订单信息
	 *
	 * @param bill
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "billAdd.html", method = RequestMethod.POST)
	public String providerAdd(Bill bill, HttpSession session) {
		if(session.getAttribute("USER_CODE")!=null){
			User u = (User)session.getAttribute("USER_CODE");
			bill.setCreatedBy(u.getId());
		}
		bill.setCreationDate(new Date());
		Integer result = billService.addBill(bill);
		if(result > 0){
			return "redirect:billlist.html";
		}else{
			return "billadd";
		}
	}
	

}
