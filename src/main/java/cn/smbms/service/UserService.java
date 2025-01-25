/**
 *  UserService  ()
 */
package cn.smbms.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;
import cn.smbms.tools.Page;

public interface UserService {
	
	/**
	 * findByName  (通过名称获取用户对象)
	 * @param name
	 * @return
	 */
	User findByName(String name);
	
	/**
	 * saveUser  (添加用户信息)
	 * @param user
	 * @return
	 */
	Integer saveUser(User user);
	
	/**
	 * findByPage  (查询数据+分页)
	 * @param map
	 * @return
	 */
	Page<User> findByPage(Map<String, Object> map);
	
	/**
	 * delectUser  (删除用户信息)
	 * @param id
	 * @return
	 */
	Integer delectUser(Integer id);
	
	/**
	 * updateUser  (用户修改)
	 * @param user
	 * @return
	 */
	Integer updateUser(User user);
	
	/**
	 * findByid  (通过ID获取用户信息)
	 * @param id
	 * @return
	 */
	User findByid(Integer id);
	
	/**
	 * UpdatePwd  (修改密码)
	 * @return
	 */
	Integer updatePwd(User user);
	
}	
