package com.pi.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Role;
import com.pi.dev.repository.RoleRepository;
import com.pi.dev.serviceInterface.IRoleService;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class RoleServiceImpl implements IRoleService {

	
	@Autowired
	RoleRepository roleRepository;
	
	
	
	@Override
	public List<Role> findAll() {
		return  roleRepository.findAll();
	}
	
	
	@Override
	public Role addRole(Role role) {
		
	return roleRepository.save(role);
	}
	
		
		
	@Override
	public Role updateRole(Role role, Integer roleId) {
		
			role.setId(roleId);
			return roleRepository.save(role);
		
	}
	
	
	@Override
	public void deleteRoleById(Integer roleID) {
		roleRepository.deleteById(roleID);
		
	}
	
	
}
