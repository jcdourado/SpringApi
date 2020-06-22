package br.com.learning.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Estado {
	
	@GeneratedValue
	@Id
	private Integer id;
	private String UF;
	
	public Integer getId() {
		return id;
	}
	public String getUF() {
		return UF;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUF(String uF) {
		UF = uF;
	}

}
