package com.bit.std.atm.iservices;

public interface IAuthenticationService {
	public boolean isAuthenticated(Integer pin);
	public boolean resetPin(Integer pin);
}