package cn.smbms.controller;

import cn.smbms.pojo.Stock;
import cn.smbms.service.StockService;
import cn.smbms.tools.Page;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    public StockService getStockService() {
        return stockService;
    }

    /**
     * 列表展示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "stocklist.html", method = RequestMethod.GET)
    public String stockList(Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", 1);
        map.put("size", 5);
        Page<Stock> page = stockService.findByPage(map);
        model.addAttribute("page", page);
        return "stocklist";
    }

    /**
     * 分页查询
     *
     * @param model
     * @param pageIndex
     * @param queryProName
     * @param queryProCode
     * @return
     */
    @RequestMapping(value = "stocklist.html", method = RequestMethod.POST)
    public String productShow(
            Model model,
            @RequestParam(value = "pageIndex", required = false) String pageIndex,
            @RequestParam(value = "queryProName", required = false) String queryProName,
            @RequestParam(value = "queryProCode", required = false) String queryProCode) {

        if ("".equals(pageIndex) || null == pageIndex) {
            pageIndex = "1";
        }
        Map<String, Object> map = new HashMap<>();
        if (queryProName != null && !"".equals(queryProName)) {
            map.put("productname", queryProName);
        }
        if (queryProCode != null && !"".equals(queryProCode)) {
            map.put("productcode", queryProCode);
        }
        map.put("index", pageIndex);
        map.put("size", 5);
        Page<Stock> page = stockService.findByPage(map);
        model.addAttribute("page", page);
        model.addAttribute("queryProName", map.get("productname"));
        model.addAttribute("queryProCode", map.get("productcode"));
        return "stocklist";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value="stockadd.html",method = RequestMethod.GET)
    public String provideradd(){
        return "stockadd";
    }

    /**
     * 添加商品库存
     *
     * @param stock
     * @return
     */
    @RequestMapping(value = "stockAdd.html", method = RequestMethod.POST)
    public String providerAdd(Stock stock) {
        Integer result = stockService.addStock(stock);
        if(result > 0){
            return "redirect:stocklist.html";
        }else{
            return "stockadd";
        }
    }

    /**
     * 添加库存
     *
     * @param model
     * @param proid
     * @return
     */
    @RequestMapping(value = "stockdel.json",method=RequestMethod.POST)
    @ResponseBody
    public Object providerdel(Model model
            ,@RequestParam(value = "proid", required = false)String proid) {
        JSONObject json = new JSONObject();
        if(json.get("delResult") != null){
            json.remove("delResult");
        }
        Integer result = stockService.deleteStock(Integer.parseInt(proid));
        if(result == 1){
            json.put("delResult", "true");
        }else{
            json.put("delResult", "false");
        }
        return json;
    }

    /**
     * 跳转到更新页面
     *
     * @param model
     * @param proid
     * @return
     */
    @RequestMapping(value = "stockmodify.html", method = RequestMethod.GET)
    public String productModify(Model model,@RequestParam(value = "proid", required = false)String proid) {
        Stock stock = stockService.findStockId(Integer.parseInt(proid));
        model.addAttribute("stock",stock);
        return "stockmodify";
    }

    /**
     * 更新库存
     *
     * @param stock
     * @return
     */
    @RequestMapping(value = "stockmodify.html", method = RequestMethod.POST)
    public String providermodify(Stock stock) {
        Integer result = stockService.updateStock(stock);
        if(result > 0){
            return "redirect:stocklist.html";
        }else{
            return "stockmodify";
        }
    }

}
