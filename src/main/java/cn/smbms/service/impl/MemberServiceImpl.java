package cn.smbms.service.impl;

import cn.smbms.mapper.MemberMapper;
import cn.smbms.pojo.Member;
import cn.smbms.pojo.Product;
import cn.smbms.service.MemberService;
import cn.smbms.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Page<Member> findByPage(Map<String, Object> map) {
        Integer count = memberMapper.countByQuery(map);
        Page<Member> pa = new Page<>();
        pa.setCount(count);
        pa.setIndex(Integer.parseInt(map.get("index").toString()));
        map.put("index", (pa.getIndex()-1)*pa.getSize());
        map.put("size", pa.getSize());
        List<Member> list = memberMapper.findByQuery(map);
        pa.setList(list);
        return pa;
    }

    @Override
    public Integer addMember(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public Member findMemberId(int id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer deleteMember(int id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateMember(Member member) {
        return memberMapper.updateByPrimaryKeySelective(member);
    }
}
