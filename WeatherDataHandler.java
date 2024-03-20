package algo.weatherdata;

import algo.weatherdata.WeatherMeasurement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class WeatherDataHandler {
	private Map<LocalDate, List<WeatherMeasurement>> weatherData = new HashMap<>(); // 0(1)

	public void loadData(String filePath) throws IOException {
		List<String> fileData = Files.readAllLines(Paths.get(filePath)); //0 (1)
		for (String line : fileData) { // 0(n)
			String[] parts = line.split(";"); // 0(1)
			LocalDate date = LocalDate.parse(parts[0]); // 0(1)
			double temperature = Double.parseDouble(parts[2]); // 0(1)
			boolean confirmed = parts[3].equals("G");  // 0(1)
			WeatherMeasurement measurement = new WeatherMeasurement(date, temperature, confirmed); // 0(1)

			weatherData.computeIfAbsent(date, k -> new ArrayList<>()).add(measurement);// 0(1)

		}
	}

	public List<String> averageTemperatures(LocalDate dateFrom, LocalDate dateTo) {
		LinkedList<String> result = new LinkedList<>(); // 0(1)
		for (LocalDate date = dateFrom; !date.isAfter(dateTo); date = date.plusDays(1)) { // 0(n)
			List<WeatherMeasurement> measurements = weatherData.get(date); // 0(1)
			if (measurements != null && !measurements.isEmpty()) { // 0(1)
				double sum = measurements.stream().mapToDouble(WeatherMeasurement::getTemperature).sum();// 0(1)
				double average = sum / measurements.size();// 0(1)
				average = Math.round(average * 100.0) / 100.0;// 0(1)
				result.add(date + " average temperature: " + average + " degrees Celsius");// 0(1)
			}
		}
		return result;// 0(1)
	}

		public List<String> missingValues(LocalDate dateFrom, LocalDate dateTo) {
			LinkedList<String> missingValuesList = new LinkedList<>();// 0(1)
			for (LocalDate date = dateFrom; !date.isAfter(dateTo); date = date.plusDays(1)) {// 0(n)
				List<WeatherMeasurement> measurements = weatherData.get(date);// 0(1)
				int missing = measurements != null ? 24 - measurements.size() : 24;// 0(1)
				missingValuesList.add(date + " missing " + missing + " values");// 0(1)
			}
			missingValuesList.sort((a, b) -> {// 0(1)
				int missingA = Integer.parseInt(a.split(" ")[2]);// 0(1)
				int missingB = Integer.parseInt(b.split(" ")[2]);// 0(1)
				return Integer.compare(missingB, missingA);// 0(1)
			});
			return missingValuesList;// 0(1)
		}

	public List<String> approvedValues(LocalDate dateFrom, LocalDate dateTo) {
		int totalApprovedValuesCount = 0;// 0(1)
		int totalValuesCount = 0;// 0(1)
		for (LocalDate date = dateFrom; !date.isAfter(dateTo); date = date.plusDays(1)) {// 0(n)
			List<WeatherMeasurement> measurements = weatherData.get(date);// 0(1)
			if (measurements != null) {// 0(1)
				totalValuesCount += measurements.size();// 0(1)
				totalApprovedValuesCount += measurements.stream().filter(WeatherMeasurement::isConfirmed).count();// 0(1)
			}
		}
		List<String> result = new ArrayList<>();// 0(1)
		if (totalValuesCount > 0) {// 0(1)
			double percentage = (double) totalApprovedValuesCount / totalValuesCount * 100;// 0(1)
			percentage = Math.round(percentage * 100.0) / 100.0;// 0(1)
			result.add("Approved values between " + dateFrom + " and " + dateTo + ": " + percentage + " %");// 0(1)
		} else {
			result.add("No values available for the specified date range.");// 0(1)
		}
		return result;// 0(1)
	}
}
