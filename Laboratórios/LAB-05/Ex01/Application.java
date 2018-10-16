public class Application {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Predator d1 = new Dog("Piloto");
		Predator d2 = new Dog("Átila");
 
		Predator c1 = new Cat("Tareco");
		Predator c2 = new Cat("Pantufa");
		Predator c3 = new Cat("Kitty");
 
		Prey[] prey = new Prey[70];
 
		int ix;
		for (ix = 0; ix < 30; ix++) {
			prey[ix] = new Bird();
		}
 
		for (; ix < 70; ix++) {
			prey[ix] = new Mouse();
		}
 
	    // snapshot: present everything
		System.out.println("BEFORE");
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
 
		// run, chase, eat, sleep, etc.
 
		d1.run();
 
		//d2.attackCat(c1);            // not possible for predators
		((Dog)d2).attackCat((Cat)c1);  // but ok for cats and dogs (bad style)
 
		c2.eat(prey[2]);
		c3.eat(prey[9]);
		c3.eat(prey[0]);
		d2.eat(prey[1]);
		prey[3].run();
 
	    // snapshot: present everything
		System.out.println("AFTER");
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
 
	}
}