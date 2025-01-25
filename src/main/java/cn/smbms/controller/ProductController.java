package cn.smbms.controller;


import cn.smbms.pojo.Product;
import cn.smbms.service.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 商品管理列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "productlist.html", method = RequestMethod.GET)
    public String productList(Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", 1);
        map.put("size", 5);
        Page<Product> page = productService.findByPage(map);
        model.addAttribute("page", page);
        return "productlist";
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
    @RequestMapping(value = "productlist.html", method = RequestMethod.POST)
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
        Page<Product> page = productService.findByPage(map);
        model.addAttribute("page", page);
        model.addAttribute("queryProName", map.get("productname"));
        model.addAttribute("queryProCode", map.get("productcode"));
        return "productlist";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value="productadd.html",method = RequestMethod.GET)
    public String provideradd(){
        return "productadd";
    }

    /**
     * 添加商品
     *
     * @param model
     * @param product
     * @param session
     * @return
     */
    @RequestMapping(value = "productAdd.html", method = RequestMethod.POST)
    public String providerAdd(Model model, Product product, HttpSession session) {
        Integer result = productService.addProduct(product);
        if(result > 0){
            return "redirect:productlist.html";
        }else{
            return "productadd";
        }
    }

    /**
     * 删除商品
     *
     * @param model
     * @param proid
     * @return
     */
    @RequestMapping(value = "productdel.json",method=RequestMethod.POST)
    @ResponseBody
    public Object providerdel(Model model
            ,@RequestParam(value = "proid", required = false)String proid) {
        JSONObject json = new JSONObject();
        Product count = productService.findProductId(Integer.parseInt(proid));
        if(json.get("delResult") != null){
            json.remove("delResult");
        }
        Integer result = productService.deleteProduct(Integer.parseInt(proid));
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
    @RequestMapping(value = "productmodify.html", method = RequestMethod.GET)
    public String productModify(Model model,@RequestParam(value = "proid", required = false)String proid) {
        Product product = productService.findProductId(Integer.parseInt(proid));
        model.addAttribute("product",product);
        return "productmodify";
    }

    /**
     * 更新商品
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "productmodify.html", method = RequestMethod.POST)
    public String providermodify(Product product) {
        Integer result = productService.updateProduct(product);
        if(result > 0){
            return "redirect:productlist.html";
        }else{
            return "productmodify";
        }
    }

}
