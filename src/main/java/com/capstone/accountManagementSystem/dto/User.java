package com.capstone.accountManagementSystem.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// table 
@Entity
@Table(name = "user")
public class User {

	@Id // for PK
	private int userId;
	@Column
	private String password;
	@Column
	private int roleId;
	public String getPassword() {
		return password;
	}
	
}


