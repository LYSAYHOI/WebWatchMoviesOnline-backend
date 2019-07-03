package com.kltn.Repository;

import com.kltn.Model.Role;
import com.kltn.Model.RoleName;

public interface IRoleRepository {

    public Role addRole(Role role);
    public boolean checkHasRole(Role role);
    public Role getRole(RoleName roleName);
}
