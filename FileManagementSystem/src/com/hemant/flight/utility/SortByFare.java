package com.hemant.flight.utility;

import java.util.Comparator;

import com.hemant.flight.model.FlightDetail;

public class SortByFare implements Comparator<FlightDetail> {

	@Override
	public int compare(FlightDetail f1, FlightDetail f2) {
		return (int) (f1.getFare() - f2.getFare());
	}

}
