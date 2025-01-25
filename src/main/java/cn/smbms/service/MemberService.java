package cn.smbms.service;

import cn.smbms.pojo.Member;
import cn.smbms.tools.Page;

import java.util.Map;

/**
 * @author xmy
 * @date 2021/3/28 19:45
 */
public interface MemberService {

    Page<Member> findByPage(Map<String, Object> map);

    Integer addMember(Member member);

    Member findMemberId(int id);

    Integer deleteMember(int id);

    Integer updateMember(Member member);

}
