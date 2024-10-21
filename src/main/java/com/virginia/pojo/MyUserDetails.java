package com.virginia.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
// overwrite UserDetails

public class MyUserDetails implements UserDetails {
    private User user;

    private List<String> roleList;

    private List<String> permissionList;

    // merge roleList and permissionList and then return

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        roleList.forEach(role->list.add(new SimpleGrantedAuthority(role)));
        permissionList.forEach(permission->list.add(new SimpleGrantedAuthority(permission)));
        return list;
    }

    @Override
    public String getPassword() {
        return user.getLoginPwd();
    }

    @Override
    public String getUsername() {
        return user.getLoginAct();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountNoExpired() == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountNoLocked() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsNoExpired() == 1;
    }

    @Override
    public boolean isEnabled() {
        return user.getAccountEnabled() == 1;
    }
}
