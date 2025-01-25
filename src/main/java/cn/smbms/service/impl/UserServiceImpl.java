/**
 *  UserServiceImpl  ()
 */
package cn.smbms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.mapper.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import cn.smbms.tools.Page;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public User findByName(String name) {
		return userMapper.findByName(name);
	}
	
	public Integer saveUser(User user) {
		return userMapper.saveUser(user);
	}
	
	public Page<User> findByPage(Map<String, Object> map) {
		Integer count = userMapper.countByQuery(map);
		Page<User> pa = new Page<User>();
		pa.setCount(count);
		pa.setIndex(Integer.parseInt(map.get("index").toString()));
		map.put("index", (pa.getIndex()-1)*pa.getSize());
		map.put("size", pa.getSize());
		List<User> list = userMapper.findByQuery(map);
		pa.setList(list);
		return pa;
	}
	
	public Integer delectUser(Integer id) {
		return userMapper.deleteUser(id);
	}
	
	public User findByid(Integer id) {
		return userMapper.findByid(id);
	}
	
	public Integer updateUser(User user) {
		return userMapper.updateUser(user);
	}
	
	public Integer updatePwd(User user) {
		return userMapper.updatePwd(user);
	}

}
