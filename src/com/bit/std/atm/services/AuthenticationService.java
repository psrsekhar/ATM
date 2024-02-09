package com.bit.std.atm.services;

import com.bit.std.atm.iservices.IAuthenticationService;

public class AuthenticationService implements IAuthenticationService {
	private static Integer passCode = 2143;

	@Override
	public boolean isAuthenticated(Integer pin) {
		return pin.equals(this.passCode);
	}

	@Override
	public boolean resetPin(Integer pin) {
		boolean isChanged = false;
		this.passCode = pin;
		isChanged = true;
		return isChanged;
	}
}