package com.capstone.accountManagementSystem.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// table 
@Entity
@Table(name = "accountTransactions")
public class AccountTransactions {

	@Id // for PK
	private int transactionId;
	@Column
	private long transactionReferenceNo;
	@Column
	private int accountNo;
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateTime;
	@Column
	private String type;
	@Column
	private String subtype;
	@Column
	private long currentBalance;
	
}


