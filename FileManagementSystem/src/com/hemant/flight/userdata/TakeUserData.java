package com.hemant.flight.userdata;

import java.util.Scanner;

public class TakeUserData {
	public String departureLocation;
	public String arrivalLocation;
	public String flightDate;
	public String flightClass;
	public String outputPreference;

	public void userInput() {
		Scanner input = new Scanner(System.in);
		boolean isvalid;

		Validate validate = new Validate();
		System.out.println("Enter the Departure Location:");
		do {
			departureLocation = input.next().toUpperCase();
			isvalid = validate.validateDepartureAndArrivalLocation(departureLocation.trim());
		} while (!isvalid);

		System.out.println("Enter the Arrival Location:");
		do {
			arrivalLocation = input.next().toUpperCase();
			isvalid = validate.validateDepartureAndArrivalLocation(arrivalLocation.trim());
		} while (!isvalid);

		System.out.println("Enter the Flight Date (dd-mm-yyyy):");
		do {
			flightDate = input.next();
			isvalid = validate.validateFlightDate(flightDate.trim());
		} while (!isvalid);

		System.out.println("Enter the Flight Class (E = Economy , B = Business):");
		do {
			flightClass = input.next().toUpperCase();
			isvalid = validate.validateFlightClass(flightClass.trim());
		} while (!isvalid);

		System.out.println("Enter the Output Preference (1 = by fare, 2 = by fare & duration of flight)");
		do {
			outputPreference = input.next();
			isvalid = validate.validatePreference(outputPreference.trim());
		} while (!isvalid);

		input.close();

	}

}
