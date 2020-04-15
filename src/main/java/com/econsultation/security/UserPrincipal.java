package com.econsultation.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.econsultation.jpa.UserRoleRespository;
import com.econsultation.model.User;
import com.econsultation.model.UserRole;

public class UserPrincipal implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
    
    @Autowired
    UserRoleRespository userRoleRepo;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of permissions (name)
        UserRole userRole =this.user.getUserRole();
        //long roleId = userRole.getRoleId();
		//UserRole userRole = userRoleRepo.findUserRoleByRoleId(roleId);
		String permissions = userRole.getAccess();
        List<String> permissionsList = new ArrayList<>();
        if(permissions.length() > 0){
        	permissionsList = Arrays.asList(permissions.split(","));
        }
        
        permissionsList.forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });
//        this.user.getPermissionList().forEach(p -> {
//            GrantedAuthority authority = new SimpleGrantedAuthority(p);
//            authorities.add(authority);
//        });

        // Extract list of roles (ROLE_name)
        String roleName = userRole.getRoleName();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);
            authorities.add(authority);

//        this.user.getRoleList().forEach(r -> {
//            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
//            authorities.add(authority);
//        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
