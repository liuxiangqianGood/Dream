package com.hope.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_Menu")
public class T_Menu {
@Id
@GeneratedValue
@Column(name="mid")
	   private int mid; 
@Column
	   private String mname;
@Column
	   private String fid;
@Column
	   private String urlaction;
public int getMid() {
	return mid;
}
public void setMid(int mid) {
	this.mid = mid;
}
public String getMname() {
	return mname;
}
public void setMname(String mname) {
	this.mname = mname;
}
public String getFid() {
	return fid;
}
public void setFid(String fid) {
	this.fid = fid;
}
public String getUrlaction() {
	return urlaction;
}
public void setUrlaction(String urlaction) {
	this.urlaction = urlaction;
}


}
