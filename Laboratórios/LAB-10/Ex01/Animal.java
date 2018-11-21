class Animal implements Comparable<Animal> {
  private Double _weight;
  public Animal(double weight) { _weight = weight; }
  public Double getWeight()         { return _weight; }
  public int    compareTo(Animal a) { return _weight.compareTo(a.getWeight()); }
  public String toString()          { return "Peso " + _weight; }
}