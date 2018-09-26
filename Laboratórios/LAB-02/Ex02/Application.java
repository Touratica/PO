public class Application {
	public static void main(String[] args) {
		Dog dog1 = new Dog("Piloto");
		Dog dog2 = new Dog("Átila");

		Cat cat1 = new Cat("Tareco");
		Cat cat2 = new Cat("Pantufa");
		Cat cat3 = new Cat("Kitty");

		Bird[] birds = new Bird[20];
		for (int i = 0; i < birds.length; i++) {
			birds[i] = new Bird();
		}

		Mouse[] mice = new Mouse[50];
		for (int i = 0; i < mice.length; i++) {
			mice[i] = new Mouse();
		}

		System.out.println("BEFORE");
		System.out.println(dog1);
		System.out.println(dog2);
		System.out.println(cat1);
		System.out.println(cat2);
		System.out.println(cat3);
		
		for (int i = 0; i < birds.length; i++) {
			System.out.println(birds[i]);
		}

		for (int i = 0; i < mice.length; i++) {
			System.out.println(mice[i]);
		}

		for (int i = 0; i < birds.length; i++) {
			birds[i].fly();
		}
 
		dog1.run();
		dog2.attackCat(cat1);
		cat2.eatBird(birds[2]);
		cat3.eatBird(birds[9]);
		cat3.eatMouse(mice[0]);
		dog2.eatMouse(mice[1]);
		mice[3].run();

		System.out.println();
		System.out.println("AFTER");
		System.out.println(dog1);
		System.out.println(dog2);
		System.out.println(cat1);
		System.out.println(cat2);
		System.out.println(cat3);
		
		for (int i = 0; i < birds.length; i++) {
			System.out.println(birds[i]);
		}

		for (int i = 0; i < mice.length; i++) {
			System.out.println(mice[i]);
		}

	}
}