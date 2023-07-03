package com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.app.model.Authority;
import com.app.model.Patient;


public class PatientUserDetails implements UserDetails {
	
	Patient patient;
	
	
	

	public PatientUserDetails(Patient patient) {
		this.patient = patient;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities=new ArrayList<>();
		
		List<Authority> auths= patient.getAuthorities();

		
		
		
		for(Authority auth:auths) {
			SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(auth.getName());
			authorities.add(simpleGrantedAuthority);
		}
		
		
		return authorities;
		
	}

	@Override
	public String getPassword() {
		
		return patient.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return patient.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
