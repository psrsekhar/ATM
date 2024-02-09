package com.bit.std.atm.services;

import com.bit.std.atm.iservices.IBankingService;

public class BankingService implements IBankingService {
	private static Integer balance = 200000;

	@Override
	public boolean deposit(Integer amount) {
		boolean isDeposited = false;
		balance += amount;
		isDeposited = true;
		return isDeposited;
	}

	@Override
	public boolean withdrawl(Integer amount) {
		boolean isDeducted = false;
		balance -= amount;
		isDeducted = true;
		return isDeducted;
	}

	@Override
	public Integer balance() {
		return balance;
	}
}