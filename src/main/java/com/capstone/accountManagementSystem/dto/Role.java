package com.capstone.accountManagementSystem.dto;

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
@Table(name = "role")
public class Role  {

	@Id // for PK
	private int roleId;
	@Column
	private String roleName;
	
}




