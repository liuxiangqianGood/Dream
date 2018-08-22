package com.hope.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_User_Role")
public class T_User_Role{
@Id
@GeneratedValue
@Column(name="urid")
       private int urid;
@Column
	   private int id; 
@Column
	   private int rid;
public int getUrid() {
	return urid;
}
public void setUrid(int urid) {
	this.urid = urid;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getRid() {
	return rid;
}
public void setRid(int rid) {
	this.rid = rid;
}


	   
}
