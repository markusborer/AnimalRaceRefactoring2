package ch.adesso.animalRace.animal;

import ch.adesso.animalRace.Weather;
import ch.adesso.animalRace.fan.Fan;

public class Animal {

	private AnimalType animalType;
	private String name;

	public Animal(AnimalType animalType, String name) {
		this.animalType = animalType;
		this.name = name;
	}

	public AnimalType getAnimalType() {
		return animalType;
	}

	public String getName() {
		return name;
	}

	public Fan getFan() {
		Fan fan = new Fan();
		fan.setAnimal(this);
		fan.setName("Fan of " + name);
		return fan;
	}

	public int calculateStep(Weather weather) {
		switch (getAnimalType()) {
		case ELEFANT:
			switch (weather) {
			case SUNNY:
				return 4;
			case RAINY:
				return 1;
			case STORMY:
				return 2;
			}
			break;
		case HEDGEHOG:
			switch (weather) {
			case SUNNY:
				return 2;
			case RAINY:
				return 4;
			case STORMY:
				return 4;
			}
			break;
		case TIGER:
			switch (weather) {
			case SUNNY:
				return 8;
			case RAINY:
				return 6;
			case STORMY:
				return 4;
			}
			break;
		}
		return 0;
	}

}
