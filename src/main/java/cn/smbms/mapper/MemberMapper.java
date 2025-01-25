package cn.smbms.mapper;


import cn.smbms.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Integer countByQuery(Map<String, Object> map);

    List<Member> findByQuery(Map<String, Object> map);
}