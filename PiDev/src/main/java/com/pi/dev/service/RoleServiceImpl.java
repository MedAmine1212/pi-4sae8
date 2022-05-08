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
	
	
	
	//afficher la liste des users
	@Override
	public List<Role> findAll() {
		return  roleRepository.findAll();
	}
	
	
	//ajouter un user
	@Override
	public Role addRole(Role role) {
		
	return roleRepository.save(role);
	}
	
	@Override
	public Role updateRole(Role role, Long roleID) {
		
			role.setRoleId(roleID);
			return roleRepository.save(role);
		
	}
	
	//effacer un user
	@Override
	public void deleteRoleById(Long roleID) {
		roleRepository.deleteById(roleID);
		
	}
	
	
}
