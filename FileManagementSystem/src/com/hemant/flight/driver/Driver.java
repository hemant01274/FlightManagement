package com.hemant.flight.driver;

import com.hemant.flight.data.UpdateData;
import com.hemant.flight.userdata.TakeUserData;
import com.hemant.flight.utility.FindFlight;
import com.hemant.flight.utility.ShowFlights;
import com.hemant.flight.watchservice.WatchServiceFile;

public class Driver {

	public static void main(String[] args) throws InterruptedException {

		UpdateData updateData = new UpdateData();
		updateData.start();
		
		WatchServiceFile watchService = new WatchServiceFile();
		watchService.start();
		
		TakeUserData userData = new TakeUserData();
		userData.userInput();

		updateData.join();

		FindFlight findFlight = new FindFlight();
		findFlight.find(userData.departureLocation, userData.arrivalLocation, userData.flightClass, userData.flightDate,
				userData.outputPreference);

		ShowFlights.displayFlights();


	}
}