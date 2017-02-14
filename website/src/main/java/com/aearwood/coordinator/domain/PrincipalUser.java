package com.aearwood.coordinator.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalUser implements UserDetails{
	
	private static final long serialVersionUID = 6720154359374912073L;
	List<GrantedAuthority> authorities;
	User user;
	public PrincipalUser(User user) {
		this.user = user;
		authorities = AuthorityUtils.createAuthorityList(user.getRole().toString());
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPasswordHash();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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
