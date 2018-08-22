package com.hope.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Controller;

@Entity
@Table(name="orderinfo")
public class Orderinfo {
@Id
@GeneratedValue
@Column(name="orderid")
	private String orderid;
@Column
	private String shop;
@Column
	private String adress;
@Column
	private String tel;
public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public String getShop() {
	return shop;
}
public void setShop(String shop) {
	this.shop = shop;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}


}
