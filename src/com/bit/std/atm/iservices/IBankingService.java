package com.bit.std.atm.iservices;

public interface IBankingService {
	public boolean deposit(Integer amount);
	public boolean withdrawl(Integer amount);
	public Integer balance();
}