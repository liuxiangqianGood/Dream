package com.hope.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_Role_Menu")
public class T_Role_Menu {
@Id
@GeneratedValue
@Column(name="rmid")
	private int  rmid;
@Column
	private int rid;
@Column
	private int mid;
public int getRmid() {
	return rmid;
}
public void setRmid(int rmid) {
	this.rmid = rmid;
}
public int getRid() {
	return rid;
}
public void setRid(int rid) {
	this.rid = rid;
}
public int getMid() {
	return mid;
}
public void setMid(int mid) {
	this.mid = mid;
}
	

}
