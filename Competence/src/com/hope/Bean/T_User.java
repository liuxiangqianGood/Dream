package com.hope.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
public class T_User {
@Id
@GeneratedValue
@Column(name="id")
	  private String id; 
@Column
	  private String username; 
@Column
	  private String password ;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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


}
