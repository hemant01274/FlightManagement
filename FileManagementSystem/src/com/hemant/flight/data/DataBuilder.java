package com.hemant.flight.data;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hemant.flight.model.FlightDetail;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class DataBuilder implements Runnable {
	private String fileName;

	public DataBuilder(String fileName) {
		this.fileName = fileName;
	}

	public void readFileData(String name) {
		try {
			CSVReader reader = new CSVReaderBuilder(new FileReader(name)).withSkipLines(1).build();
			List<String[]> lines = reader.readAll();
			buildDataMap(lines);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void buildDataMap(List<String[]> lines) throws ParseException {
		String key;
		SimpleDateFormat stdfmt = new SimpleDateFormat("dd-mm-yyyy");
		for (String[] row : lines) {
			for (String cell : row) {
				FlightDetail flightData = new FlightDetail();
				String[] flightDetail = cell.split("\\|");
				flightData.setFlightNumber(flightDetail[0]);
				flightData.setDeparture(flightDetail[1]);
				flightData.setDestination(flightDetail[2]);

				Date date = stdfmt.parse(flightDetail[3]);
				flightData.setValidTill(date);
				String time = flightDetail[4].substring(0, 2) + ":" + flightDetail[4].substring(2);
				flightData.setFlightTime(time);
				flightData.setFlightDuration(flightDetail[5]);

				double fare = Double.parseDouble(flightDetail[6]);
				flightData.setFare(fare);
				flightData.setSeatAvailability(flightDetail[7]);
				flightData.setFlightClass(flightDetail[8]);
				key = flightDetail[1] + ":" + flightDetail[2] + ":" + flightDetail[8];
				if (UpdateData.flightDetailsMap.containsKey(key)) {
					List<FlightDetail> flightDetailList = UpdateData.flightDetailsMap.get(key);
					flightDetailList.add(flightData);
				} else {
					List<FlightDetail> flightDetailList = new ArrayList<>();
					flightDetailList.add(flightData);
					UpdateData.flightDetailsMap.put(key, flightDetailList);
				}
			}
			// System.out.println(Thread.currentThread().getName());
		}
	}

	@Override
	public void run() {
		readFileData(fileName);
	}

}
