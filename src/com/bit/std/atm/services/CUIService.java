package com.bit.std.atm.services;

import java.util.Scanner;

import com.bit.std.atm.iservices.IAuthenticationService;
import com.bit.std.atm.iservices.IBankingService;
import com.bit.std.atm.iservices.ICUIService;

public class CUIService implements ICUIService {
	private Integer choice = null;
	private Integer pin = null;
	private Integer amount = null;
	private Scanner sc = new Scanner(System.in);
	private IBankingService bankingService = new BankingService();
	private IAuthenticationService authenticationService = new AuthenticationService();

	@Override
	public void showMenu() {
		while (true) {
			System.out.println("1.Deposit\n2.Withdrawl\n3.Balance\n4.PIN Change\n5.Exit");
			userChoice();
		}
	}

	private void userChoice() {
		System.out.print("\nenter your choice: ");
		choice = sc.nextInt();
		performUserOperation();
	}

	private void performUserOperation() {
		switch (choice) {
		case 1:
			deposit();
			break;
		case 2:
			withdrawl();
			break;
		case 3:
			balance();
			break;
		case 4:
			pinChange();
			break;
		case 5:
			System.out.println("Terminating the banking services.");
			System.exit(0);
		default:
			System.out.println("Invalid choice");
			break;
		}
	}

	private void pinChange() {
		if (authenticateUser()) {
			readPin();
			if (authenticationService.resetPin(pin)) {
				System.out.println("PIN changed.");
			} else {
				System.out.println("PIN reset failed.");
			}
		}
	}

	private void withdrawl() {
		if (authenticateUser()) {
			readAmount();
			if (validateAmount()) {
				if (bankingService.balance() >= amount) {
					if (bankingService.withdrawl(amount)) {
						System.out.println("Amount deducted.");
					} else {
						System.out.println("Amount not deducted.");
					}
				} else {
					System.out.println("Insuffient funds.");
				}
			} else {
				System.out.println("Invalid amount entered.");
			}
		}
	}

	private void balance() {
		if (authenticateUser()) {
			System.out.println("Balance is: " + bankingService.balance());
		}
	}

	private void deposit() {
		readAmount();
		if (validateAmount()) {
			if (bankingService.deposit(amount)) {
				System.out.println("Amount deposited.");
			} else {
				System.out.println("Amount not deposited.");
			}
		} else {
			System.out.println("Invalid amount entered.");
		}
	}

	private void readAmount() {
		System.out.print("\nenter amount: ");
		amount = sc.nextInt();
	}

	private boolean validateAmount() {
		return (amount % 100) == 0 ? true : false;
	}

	private void readPin() {
		System.out.print("\nenter 4-digit PIN: ");
		pin = sc.nextInt();
	}

	private boolean authenticateUser() {
		boolean isAuthenticated = true;
		Integer attempt = 1;
		while (attempt <= 3) {
			readPin();
			isAuthenticated = authenticationService.isAuthenticated(pin);
			if (isAuthenticated) {
				System.out.println("User authenticated.");
				break;
			}
			attempt++;
		}
		if (attempt > 3) {
			System.out.println("User blocked.\nTerminating the banking services.");
			System.exit(0);
		}
		return isAuthenticated;
	}

}