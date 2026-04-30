package com.springinprarice.ch01.model;
//Source project:sip01, branch: 04 (Maven project)

import java.math.BigDecimal;
import java.util.Date;

public class Account {

	private String accountNo;
	private BigDecimal balance;
	private Data lastPaidOn;

	public Account(String accountNo, BigDecimal balance, Date lastPaidOn) {
		this.accountNo = accountNo;
		this.balance = balance;
		this.lastPaidOn = lastPaidOn;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public BigDecimal getBalance() {
		return balance;

	}

	public Data getLastPaidOn() {
		return lastPaidOn;
	}

}
