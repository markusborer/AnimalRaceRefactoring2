package ch.adesso.animalRace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.adesso.animalRace.animal.Animal;

public class Race extends Thread {

	private Weather weather;
	private List<Animal> animals;
	private List<Integer> positions;
	private int place;
	private int index; 

	public Race(Weather weather) {
		this.weather = weather;
		animals = new ArrayList<>();
		positions = new ArrayList<>();
		place = 1;
	}
	
	public Weather getWeather() {
		return weather;
	}
	
	/**
	 * Add animal
	 * @param animal
	 */
	public void add(Animal animal) {
		animals.add(animal);
		positions.add(0);
	}

	/**
	 * Start
	 */
	@Override
	public void start() {
		System.out.println("Race started");
		System.out.println("It is " + weather);
		System.out.println("");
		boolean finished = false;
		Random random = new Random();
		while (!finished) {
			index = random.nextInt(animals.size());
			run();
			print(positions.get(index) >= 100);
			finished = true;
			for (Integer position : positions) {
				if (position < 100)
					finished = false;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		finished();
	}

	@Override
	public void run() {
		if (positions.get(index) < 100) {
			int step = animals.get(index).calculateStep(weather);
			positions.set(index, positions.get(index) + step);
		}
	}

	private void print(boolean arrived) {
		if (arrived) {
			Animal animal = animals.get(index);
			if (positions.get(index) < 1000) {
				System.out.println(animal.getAnimalType().toString() + " " + animal.getName() + " is arrived");
				animal.getFan().bravo();
				positions.set(index, 1000 + place);
				place++;
			}
		} else {
			Animal animal = animals.get(index);
			System.out.println(animal.getAnimalType().toString() + " " + animal.getName() + " is at " + positions.get(index));
			animal.getFan().applause(this);
		}
	}

	private void finished() {
		System.out.println("");
		System.out.println("Race finished");
		System.out.println("");
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).intValue() == 1001) {
				System.out.println("First:  " + animals.get(i).getAnimalType() + " " + animals.get(i).getName());
				animals.get(i).getFan().first();
			}
		}
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).intValue() == 1002) {
				System.out.println("Second: " + animals.get(i).getAnimalType() + " " + animals.get(i).getName());
				animals.get(i).getFan().second();
			}
		}
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).intValue() == 1003) {
				System.out.println("Third:  " + animals.get(i).getAnimalType() + " " + animals.get(i).getName());
				animals.get(i).getFan().third();
			}
		}
	}

}
