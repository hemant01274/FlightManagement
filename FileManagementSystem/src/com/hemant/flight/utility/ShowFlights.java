package com.hemant.flight.utility;

import java.util.Iterator;
import java.util.List;

import com.hemant.flight.model.FlightDetail;

public class ShowFlights {

	public static void displayFlights() {
		List<FlightDetail> list = FindFlight.desiredFlightList;
		Iterator<FlightDetail> itr = list.iterator();
		while (itr.hasNext()) {
			FlightDetail detail = itr.next();
			System.out.println(detail.getFlightNumber() + "\t" + detail.getFare() + "\t" + detail.getFlightDuration()
					+ "\t" + detail.getFlightTime());
		}

	}

}
