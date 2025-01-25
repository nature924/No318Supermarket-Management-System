package cn.smbms.mapper;

import java.util.List;

import cn.smbms.pojo.Role;

public interface RoleMapper {

	/**
	 * 获取角色列表
	 * @return
	 */
	List<Role> findByRoleAll();
}
