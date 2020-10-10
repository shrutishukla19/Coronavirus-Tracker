package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import io.shrula.coronavirustracker.CoronaVirusData;
import models.LocationStats;

class MappingTest {

	@ParameterizedTest(name = "id={0}")
	@CsvFileSource(resources = "testfile.csv")
	void assertDifference(String id, String fileName, String output) throws IOException {

		StringReader dataFromFile = null;
		try {
			File testFile = new File("src/main/resources/" + fileName);
			Scanner myReader = new Scanner(testFile);
			String data = new String();
			while (myReader.hasNextLine()) {
				data = data + "\n" + myReader.nextLine();
				data.trim();
			}

			dataFromFile = new StringReader(data);

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		ArrayList<String> expOutputList = new ArrayList<String>();
		Collections.addAll(expOutputList, output.split("/"));

		mapCSV(dataFromFile, expOutputList);

	}

	void mapCSV(StringReader input, ArrayList<String> expOutput) throws IOException {
		List<LocationStats> output = CoronaVirusData.csvMapping(input);

		for (int outputIndex = 0; outputIndex < output.size(); outputIndex++) {
			assertEquals("" + output.get(outputIndex).getDiffFromPrevDay(), expOutput.get(outputIndex));
		}
	}

}
