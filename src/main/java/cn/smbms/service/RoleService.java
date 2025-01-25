package cn.smbms.service;

import java.util.List;

import cn.smbms.pojo.Role;

public interface RoleService {
	/**
	 * 获取角色列表
	 * @return
	 */
	List<Role> findByRoleAll();
}
