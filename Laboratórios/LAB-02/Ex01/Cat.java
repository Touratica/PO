public class Cat {
    private String _name;
    private int _age;
    private double _weight;

    /*===============================================
    Construtors
    ===============================================*/
    public Cat(String name, int age, double weight) {
        _name = name;
        _age = age;
        _weight = weight;
    }

    public String getName() {
        return _name;
    }
    public void setName(String name) {
        _name = name;
    }

    public int getAge() {
        return _age;
    }
    public void setAge(int age) {
        _age = age;
    }

    public double getWeight() {
        return _weight;
    }
    public void setWeight(double weight) {
        _weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cat) {
            Cat cat = (Cat) o;
            return _name.equals(cat._name) && _age == cat._age && _weight == cat._weight;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return _name + " is a " + _age + "yo cat with " + _weight + "kg";
    }
}
