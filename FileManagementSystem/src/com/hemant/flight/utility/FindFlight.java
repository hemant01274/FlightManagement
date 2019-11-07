package com.hemant.flight.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.hemant.flight.data.UpdateData;
import com.hemant.flight.model.FlightDetail;

public class FindFlight {
	public static List<FlightDetail> desiredFlightList = new ArrayList<>();

	public void find(String departureLocation, String arrivalLocation, String flightClass, String flightDate,
			String outputPreference) {
		Date flightDt = null;
		try {
			flightDt = new SimpleDateFormat("dd-mm-yyyy").parse(flightDate.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		flightClass = flightClass.equalsIgnoreCase("B") ? "EB" : "E";
		String key = departureLocation + ":" + arrivalLocation + ":" + flightClass;

		if (UpdateData.flightDetailsMap.containsKey(key)) {
			List<FlightDetail> tentativeFlightList = UpdateData.flightDetailsMap.get(key);
			makeDesiredFlightList(tentativeFlightList, flightDt);
			if (outputPreference.equals("1")) {
				Collections.sort(desiredFlightList, new SortByFare());
			} else {
				Collections.sort(desiredFlightList, new SortByFareAndDuration());
			}
		} else {
			System.out.println("No flight to or from the desired location");
		}
	}

	private void makeDesiredFlightList(List<FlightDetail> tentativeFlightList, Date flightDt) {
		Iterator<FlightDetail> it = tentativeFlightList.iterator();
		while (it.hasNext()) {
			FlightDetail fd = it.next();
			if (fd.getValidTill().compareTo(flightDt) >= 0 && fd.getSeatAvailability().equalsIgnoreCase("y")) {
				desiredFlightList.add(fd);
			}
		}
		if (desiredFlightList.size() < 1) {
			System.out.println("flight not available");
		}
	}
}
