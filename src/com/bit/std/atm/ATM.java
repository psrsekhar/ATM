package com.bit.std.atm;

import com.bit.std.atm.iservices.ICUIService;
import com.bit.std.atm.services.CUIService;

public class ATM {

	public static void main(String[] args) {
		System.out.println("Welcome to BIT Banking Services");
		ICUIService cuiService = new CUIService();
		cuiService.showMenu();
	}
}
