package com.patterns.chap45.iterator.pattern.arraybacked;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Demo {
	public static void main(String args[]) {
		Creature creature = new Creature();
		creature.setAgility(12);
		creature.setIntelligence(13);
		creature.setStrength(17);
		System.out.println("Creature has a max stat of " + creature.max() + ", total stats of " + creature.sum()
				+ " and an average stat of " + creature.average());
	}
}

class Creature implements Iterable<Integer> {

	private int[] stats = new int[3];
	private final int str = 0;

	public int getStrength() {
		return stats[0];
	}

	public void setStrength(int s) {
		stats[0] = s;
	}

	public int getAgility() {
		return stats[0];
	}

	public void setAgility(int s) {
		stats[1] = s;
	}

	public int getIntelligence() {
		return stats[0];
	}

	public void setIntelligence(int s) {
		stats[2] = s;
	}

	public int max() {
		return IntStream.of(stats).max().getAsInt();
	}

	public int sum() {
		return IntStream.of(stats).sum();
	}

	public double average() {
		return IntStream.of(stats).average().getAsDouble();
	}

	@Override
	public Iterator<Integer> iterator() {

		return IntStream.of(stats).iterator();
	}

	@Override
	public void forEach(Consumer<? super Integer> action) {

		for (int x : stats) {
			action.accept(x);
		}
	}

	@Override
	public Spliterator<Integer> spliterator() {

		return Arrays.spliterator(stats);
	}

}