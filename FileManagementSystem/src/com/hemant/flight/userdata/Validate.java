package com.hemant.flight.userdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validate {

	public boolean validateDepartureAndArrivalLocation(String Location) {
		boolean valid = false;
		if (Location.length() != 3) {
			System.out.println("Length should be 3, Please Re-enter the location");
		} else if (Location.matches(".*\\d.*")) {
			System.out.println("Enter valid location");
		} else {
			valid = true;
		}
		return valid;

	}

	public boolean validateFlightDate(String flightDate) {
		boolean valid = false;
		SimpleDateFormat stdfmt = new SimpleDateFormat("dd-mm-yyyy");
		stdfmt.setLenient(false);
		try {
			stdfmt.parse(flightDate);
			valid = true;
		} catch (ParseException e) {
			System.out.println("Invalid date or format, Please Re-enter the Date");
		}
		return valid;

	}

	public boolean validateFlightClass(String flightClass) {
		boolean valid = false;
		if (flightClass.length() != 1 || !flightClass.matches("E|B")) {
			System.out.println("Please enter E or B (E= Economy, B= Business)");
		} else {
			valid = true;
		}
		return valid;
	}

	public boolean validatePreference(String outputPreference) {
		boolean valid = false;
		if (outputPreference.length() != 1 || !outputPreference.matches("1|2")) {
			System.out.println("Please enter 1 or 2");
		} else {
			valid = true;
		}
		return valid;
	}

}
