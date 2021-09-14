package com.patterns.chap38.chainOfResponsibility.pattern;

/*
 * A chain of components who all get a chance to  process a command or query,optionally having default processing implementation
 * and an ability to terminate the processing chain. 
 * */
public class Demo {
	public static void main(String args[]) {
		Creature goblin = new Creature("Goblin", 2, 2);
		System.out.println(goblin);
		
		CreatureModifier root = new CreatureModifier(goblin);
		
		System.out.println("Calling Cancel enhancements");
		root.add(new NoBonusesModifier(goblin));
		
		System.out.println("Doubling Goblin's attack ");
		root.add(new DoubleAttackModifier(goblin));
		
		System.out.println("Increasing Goblin's defence ");
		root.add(new IncreaseDefenceModifier(goblin));
		root.handle();
		System.out.println(goblin);
	}
}

class Creature{
	public String name;
	public int attack, defence;
	
	public Creature(String name, int attack, int defence) {
		super();
		this.name = name;
		this.attack = attack;
		this.defence = defence;
	}
	
	@Override
	public String toString() {
		return "Creature [name=" + name + ", attack=" + attack + ", defence=" + defence + "]";
	}
}

class CreatureModifier{
	protected Creature creature;
	protected CreatureModifier next;
	
	public CreatureModifier(Creature creature) {
		super();
		this.creature = creature;
	}
	
	public void add(CreatureModifier cm) {
		if(next != null) {
			next.add(cm);
		}
		else {
			next = cm;
		}
	}
	
	public void handle() {
		if(next != null) next.handle();
	}
}

class DoubleAttackModifier extends CreatureModifier{

	public DoubleAttackModifier(Creature creature) {
		super(creature);
	}

	@Override
	public void handle() {
		System.out.println("Doubling "+creature.name+"'s attack");
		creature.attack *= 2;
		super.handle();
	}	
}

class IncreaseDefenceModifier extends CreatureModifier{

	public IncreaseDefenceModifier(Creature creature) {
		super(creature);
	}

	@Override
	public void handle() {
		System.out.println("Increasing the "+creature.name+"'s defence");
		creature.defence +=3;
		super.handle();
	}
	
}

class NoBonusesModifier extends CreatureModifier{

	public NoBonusesModifier(Creature creature) {
		super(creature);
	}

	@Override
	public void handle() {
		System.out.println("Cancelling the Bonuses from here on ");
	}
	
}


