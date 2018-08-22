package com.hope.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_Role")
public class T_Role {
@Id
@GeneratedValue
@Column(name="rid")
	   private int rid; 
@Column
	   private String rname;
@Column
	   private String rdescribe; 
@Column
	   private String fid;
public int getRid() {
	return rid;
}
public void setRid(int rid) {
	this.rid = rid;
}
public String getRname() {
	return rname;
}
public void setRname(String rname) {
	this.rname = rname;
}
public String getRdescribe() {
	return rdescribe;
}
public void setRdescribe(String rdescribe) {
	this.rdescribe = rdescribe;
}
public String getFid() {
	return fid;
}
public void setFid(String fid) {
	this.fid = fid;
} 


}
