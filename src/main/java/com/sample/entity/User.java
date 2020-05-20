package com.sample.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.sample.form.SignupForm;

@Entity
@Table(name = "User")
public class User{
	
    @Id
    @Email
    @Column(name = "username")
	private String username;

    @Column(name = "password")
	private String password;

    @Column(name = "is_admin")
	private boolean isAdmin;

    @Column(name = "last_login")
	private Date lastLogin;

    @Column(name = "created_at")
	private Date createdAt;

    @Column(name = "updated_at")
	private Date updatedAt;

	public User() {

	}
	
	public User(SignupForm form) {
		this.username = form.getUsername();
		this.password = form.getPassword();
		this.isAdmin = false;
		this.lastLogin = new Date();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
    
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
	public boolean getIsAdmin() {
		return isAdmin;
	}
    
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin() {
		this.lastLogin = new Date();
	}

}
