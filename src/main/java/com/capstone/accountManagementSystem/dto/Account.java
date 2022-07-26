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
@Table(name = "account")
public class Account {

	@Id // for PK
	private long accountNo;
	@Column
	private int customerId;
	@Column
	private long currentBalance;
	
}


