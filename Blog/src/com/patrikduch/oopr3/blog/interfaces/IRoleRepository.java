package com.patrikduch.oopr3.blog.interfaces;

import com.patrikduch.oopr3.blog.model.Role;

public interface IRoleRepository {

    Role getRoleByName(String roleName);
    Role addRole(Role role);


}
