package cn.smbms.controller;

import cn.smbms.pojo.Member;
import cn.smbms.service.MemberService;
import cn.smbms.tools.Page;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 会员管理列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "memberlist.html", method = RequestMethod.GET)
    public String memberList(Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", 1);
        map.put("size", 5);
        Page<Member> page = memberService.findByPage(map);
        model.addAttribute("page", page);
        return "memberlist";
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
    @RequestMapping(value = "memberlist.html", method = RequestMethod.POST)
    public String memberShow(
            Model model,
            @RequestParam(value = "pageIndex", required = false) String pageIndex,
            @RequestParam(value = "queryProName", required = false) String queryProName,
            @RequestParam(value = "queryProCode", required = false) String queryProCode) {

        if ("".equals(pageIndex) || null == pageIndex) {
            pageIndex = "1";
        }
        Map<String, Object> map = new HashMap<>();
        if (queryProName != null && !"".equals(queryProName)) {
            map.put("membername", queryProName);
        }
        if (queryProCode != null && !"".equals(queryProCode)) {
            map.put("phone", queryProCode);
        }
        map.put("index", pageIndex);
        map.put("size", 5);
        Page<Member> page = memberService.findByPage(map);
        model.addAttribute("page", page);
        model.addAttribute("queryProName", map.get("membername"));
        model.addAttribute("queryProCode", map.get("phone"));
        return "memberlist";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value="memberadd.html",method = RequestMethod.GET)
    public String memberAdd(){
        return "memberadd";
    }

    /**
     * 添加会员
     *
     * @param member
     * @return
     */
    @RequestMapping(value = "memberAdd.html", method = RequestMethod.POST)
    public String providerAdd(Member member) {
        member.setCreatetime(new Date());
        Integer result = memberService.addMember(member);
        if(result > 0){
            return "redirect:memberlist.html";
        }else{
            return "memberadd";
        }
    }

    /**
     * 删除会员
     *
     * @param proid
     * @return
     */
    @RequestMapping(value = "memberdel.json",method=RequestMethod.POST)
    @ResponseBody
    public Object providerdel(@RequestParam(value = "proid", required = false)String proid) {
        JSONObject json = new JSONObject();
        if(json.get("delResult") != null){
            json.remove("delResult");
        }
        Integer result = memberService.deleteMember(Integer.parseInt(proid));
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
    @RequestMapping(value = "membermodify.html", method = RequestMethod.GET)
    public String memberModify(Model model,@RequestParam(value = "proid", required = false)String proid) {
        Member member = memberService.findMemberId(Integer.parseInt(proid));
        model.addAttribute("member",member);
        return "membermodify";
    }

    /**
     * 更新会员
     *
     * @param member
     * @return
     */
    @RequestMapping(value = "membermodify.html", method = RequestMethod.POST)
    public String providermodify(Member member) {
        Integer result = memberService.updateMember(member);
        if(result > 0){
            return "redirect:memberlist.html";
        }else{
            return "membermodify";
        }
    }

}
