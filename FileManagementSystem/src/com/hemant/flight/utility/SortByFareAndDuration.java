package com.hemant.flight.utility;

import java.util.Comparator;

import com.hemant.flight.model.FlightDetail;

public class SortByFareAndDuration implements Comparator<FlightDetail> {

	@Override
	public int compare(FlightDetail f1, FlightDetail f2) {
		double diff = f1.getFare() - f2.getFare();
		if (diff == 0) {
			diff = f1.getFlightDuration().compareTo(f2.getFlightDuration());
		}
		return (int) diff;
	}
}
