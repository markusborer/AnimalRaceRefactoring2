package ch.adesso.animalRace;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

import ch.adesso.animalRace.animal.AnimalType;

public class MainTest {

	private OutputStream outputStream;

	@Before
	public void setup() {
		outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		System.setOut(printStream);
	}

	private StringTokenizer getOutputLines() {
		String output = outputStream.toString();
		System.err.println(output);
		StringTokenizer lines = new StringTokenizer(output, "\n\r");
		return lines;
	}

	@Test
	public void sunny() {
		test(Weather.SUNNY, 276);
	}

	@Test
	public void rainy() {
		test(Weather.RAINY, 171);
	}

	@Test
	public void stormy() {
		test(Weather.STORMY, 129);
	}

	private void test(Weather weather, int countOfLines) {
		String[] args = new String[] { String.valueOf(weather.ordinal()) };
		Main.main(args);
		StringTokenizer lines = getOutputLines();
		assertEquals("Race started", lines.nextToken());
		assertEquals("It is " + weather, lines.nextToken());
		for (int i = 0; i < countOfLines; i++) {
			lines.nextToken();
		}
		assertEquals("Race finished", lines.nextToken());
		assertEquals("First:  ", lines.nextToken().substring(0, 8));
		String line = lines.nextToken();
		assertEquals("Fan of ", line.substring(0, 7));
		assertTrue(line.contains("Yeah Yeah Yeah"));
		assertEquals("Second: ", lines.nextToken().substring(0, 8));
		line = lines.nextToken();
		assertEquals("Fan of ", line.substring(0, 7));
		assertFalse(line.contains("Yeah Yeah Yeah"));
		assertTrue(line.contains("Yeah Yeah"));
		assertEquals("Third:  ", lines.nextToken().substring(0, 8));
		line = lines.nextToken();
		assertEquals("Fan of ", line.substring(0, 7));
		assertFalse(line.contains("Yeah Yeah"));
		assertTrue(line.contains("Yeah"));
	}

	@Test
	public void sunnyElefant() {
		test(Weather.SUNNY, AnimalType.ELEFANT, "Dumba", 4);
	}

	@Test
	public void sunnyHedgehog() {
		test(Weather.SUNNY, AnimalType.HEDGEHOG, "Greyhog", 2);
	}

	@Test
	public void sunnyTiger() {
		test(Weather.SUNNY, AnimalType.TIGER, "Simba", 8);
	}

	@Test
	public void rainyElefant() {
		test(Weather.RAINY, AnimalType.ELEFANT, "Dumba", 1);
	}

	@Test
	public void rainyHedgehog() {
		test(Weather.RAINY, AnimalType.HEDGEHOG, "Greyhog", 4);
	}

	@Test
	public void rainyTiger() {
		test(Weather.RAINY, AnimalType.TIGER, "Simba", 6);
	}

	@Test
	public void stormyElefant() {
		test(Weather.STORMY, AnimalType.ELEFANT, "Dumba", 2);
	}

	@Test
	public void stormyHedgehog() {
		test(Weather.STORMY, AnimalType.HEDGEHOG, "Greyhog", 4);
	}

	@Test
	public void stormyTiger() {
		test(Weather.STORMY, AnimalType.TIGER, "Simba", 4);
	}

	private void test(Weather weather, AnimalType animal, String name, int step) {
		String[] args = new String[] { String.valueOf(weather.ordinal()), String.valueOf(animal.ordinal()), name };
		Main.main(args);
		StringTokenizer lines = getOutputLines();
		assertEquals("Race started", lines.nextToken());
		assertEquals("It is " + weather, lines.nextToken());
		for (int position = step; position < 100; position += step) {
			assertEquals(animal + " " + name + " is at " + position, lines.nextToken());
			if (weather == Weather.SUNNY) {
				assertEquals("Fan of " + name + ": Hopp " + name, lines.nextToken());
			}
		}
		assertEquals(animal + " " + name + " is arrived", lines.nextToken());
		assertEquals("Fan of " + name + ": Bravo " + name, lines.nextToken());
		assertEquals("Race finished", lines.nextToken());
		assertEquals("First:  " + animal + " " + name + "", lines.nextToken());
		assertEquals("Fan of " + name + ": Yeah Yeah Yeah " + name, lines.nextToken());
	}

}
