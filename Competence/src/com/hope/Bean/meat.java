package com.hope.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="meat")
public class meat {
@Id
@GeneratedValue
@Column(name="id")
	private String id;
@Column
	private String name;
@Column
	private String flavor;
@Column
	private String cnumber;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getFlavor() {
	return flavor;
}
public void setFlavor(String flavor) {
	this.flavor = flavor;
}
public String getCnumber() {
	return cnumber;
}
public void setCnumber(String cnumber) {
	this.cnumber = cnumber;
}


}
