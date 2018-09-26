public class Dog {
    private static final int BASE_ENERGY = 1000;
    private String _name;
    private int _energy = BASE_ENERGY;

    public Dog(String name) {
        _name = name;
    }

    public int getEnergy() {
        return _energy;
    }

    public String getName() {
        return _name;
    }

    public boolean run() {
        if (_energy < 50) {
            return false;
        }
        _energy -= 50;
        return true;
    }

    public void sleep() {
        _energy = BASE_ENERGY;
    }

    public boolean isExausted() {
        return _energy == 0;
    }

    public boolean caughtMouse(Mouse mouse) {
        run();
        if (!mouse.run()) {
            return true;
        }

        if (0 == (int) (25 * Math.random())) {
            return true;
        }
        if (isExausted()) {
            sleep();
        }
        mouse.escaped();
        return false;
    }

    public void eatMouse(Mouse mouse) {
        if (caughtMouse(mouse)) {
            _energy += mouse.drain();
        }
    }

    public void atackCat(Cat cat) {
        _energy -= 100;
        cat.attacked(25);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Dog) {
            Dog dog = (Dog) o;
            return _name.equals(dog.getName()) && _energy == dog.getEnergy();
        }
        return false;
    }

    @Override
    public String toString() {
        return _name + " (dog) (" + _energy + ")";
    }
}
