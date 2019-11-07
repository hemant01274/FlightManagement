package com.hemant.flight.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hemant.flight.model.FlightDetail;

public class UpdateData extends Thread{
	public static Map<String, List<FlightDetail>> flightDetailsMap = new HashMap<>();
	
	public void run() {
		updateFlightData();
	}
	public void updateFlightData() {
		File[] files = getFiles();
		String name;
		ExecutorService es = Executors.newFixedThreadPool(3);
		for (int i = 0; i < files.length; i++) {
			name = files[i].getAbsolutePath();
			if (name.endsWith(".csv")) {
				DataBuilder file = new DataBuilder(name);
				es.execute(file);
			}
		}
		es.shutdown();
	}

	private File[] getFiles() {
		String directory = ".\\csvfiles";
		File[] files = new File(directory).listFiles();
		return files;

	}

}
