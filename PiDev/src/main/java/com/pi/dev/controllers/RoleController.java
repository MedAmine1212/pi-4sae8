//package com.pi.dev.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pi.dev.models.Contributor;
//import com.pi.dev.models.Role;
//import com.pi.dev.models.User;
//import com.pi.dev.serviceInterface.IRoleService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@RestController
//@Api(tags = "Manage roles")
//@RequestMapping("/role")
//public class RoleController {
//
//	@Autowired
//	IRoleService roleService;
//
//
//    @PostMapping("/addRole")
//    @ApiOperation(value = "ajouter role")
//	@ResponseBody
//	public Role add(@RequestBody Role role) {
//	        return roleService.addRole(role);
//	    }
//
//
//
//    @ApiOperation(value = "Update role")
//	@PostMapping("updateRole/{roleID}")
//	@ResponseBody
//	Role updateRole(@RequestBody Role role,@PathVariable Integer roleID){
//    	return roleService.updateRole(role, roleID);
//	}
//
//
//    @GetMapping("/getRole")
//    @ResponseBody
//    public List<Role> findAll() {
//        return roleService.findAll();
//    }
//
//
//    @DeleteMapping("/delete/{roleID}")
//    public void deleteContributorById(@PathVariable Integer roleID) {
//    	roleService.deleteRoleById(roleID);
//
//    }
//}
