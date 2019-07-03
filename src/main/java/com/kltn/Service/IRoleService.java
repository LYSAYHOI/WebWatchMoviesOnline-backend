package com.kltn.Service;

import com.kltn.Model.Role;
import com.kltn.Model.RoleName;

public interface IRoleService {

    public Role addRole(RoleName roleName);
    public boolean checkHasRole(RoleName roleName);
    public Role getRole(RoleName roleName);
}
