package com.hemant.flight.watchservice;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

import com.hemant.flight.data.DataBuilder;

public class WatchServiceFile extends Thread {
	public void directoryUpdate() {
		try (WatchService service = FileSystems.getDefault().newWatchService()) {
			Map<WatchKey, Path> keyMap = new HashMap<>();
			String directory = ".\\csvfiles";
			Path path = Paths.get(directory);
			keyMap.put(path.register(service, StandardWatchEventKinds.ENTRY_CREATE), path);
			WatchKey watchKey;

			do {
				watchKey = service.take();
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					Path eventpath = (Path) event.context();
					String fileName = eventpath.getFileName().toString();
					if (fileName.endsWith(".csv")) {
						DataBuilder dataBuilder = new DataBuilder(fileName);
						dataBuilder.run();
					}
				}
			} while (watchKey.reset());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		directoryUpdate();
	}
}
