package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Role;

public interface IRoleService {

	List<Role> findAll();

	Role addRole(Role role);

	Role updateRole(Role role, Integer roleID);

	void deleteRoleById(Long roleID);
}
