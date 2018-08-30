package ch.adesso.animalRace.fan;

import ch.adesso.animalRace.Race;
import ch.adesso.animalRace.Weather;
import ch.adesso.animalRace.animal.Animal;

public class Fan {

	private Animal animal;
	private String name;

	public Fan() {
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void applause(Race race) {
		if (race.getWeather() == Weather.SUNNY) {
			System.out.println(name + ": Hopp " + animal.getName());
		}
	}

	public void bravo() {
		System.out.println(name + ": Bravo " + animal.getName());
	}

	public void first() {
		System.out.println(name + ": Yeah Yeah Yeah " + animal.getName());
	}

	public void second() {
		System.out.println(name + ": Yeah Yeah " + animal.getName());
	}

	public void third() {
		System.out.println(name + ": Yeah " + animal.getName());
	}

}
