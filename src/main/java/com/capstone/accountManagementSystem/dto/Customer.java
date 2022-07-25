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
@Table(name = "customer")
public class Customer {

	@Id // for PK
	private int customerId;
	@Column(unique=true, nullable=false)
	private String pancardNo;
	@Column(unique=true, nullable=false)
	private long aadharNo;
	@Column(nullable=false)
	private String customerName;
	@Column
	private String address;
	@Column
	private String email;
	@Column
	private LocalDate dob;
	@Column
	private String aadharDoc;
	@Column
	private String pancardDoc;
	public String getPancardNo() {
		return pancardNo;
	}
	
}
