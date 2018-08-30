package ch.adesso.animalRace;

import java.util.Random;

import ch.adesso.animalRace.animal.Animal;
import ch.adesso.animalRace.animal.AnimalType;


public class Main {

	public static void main(String[] args) {
		int weather = new Random().nextInt(3);
		if (args.length > 0) {
			weather = Integer.parseInt(args[0]);
		}
		Race race = new Race(Weather.values()[weather]);
		if (args.length > 2) {
			AnimalType animalType = AnimalType.values()[Integer.parseInt(args[1])];
			String name = args[2];
			race.add(new Animal(animalType, name));
		} else {
			race.add(new Animal(AnimalType.ELEFANT, "Dumba"));
			race.add(new Animal(AnimalType.HEDGEHOG, "Greyhog"));
			race.add(new Animal(AnimalType.HEDGEHOG, "Blackhog"));
			race.add(new Animal(AnimalType.TIGER, "Simba"));
		}
		race.start();
	}

}
