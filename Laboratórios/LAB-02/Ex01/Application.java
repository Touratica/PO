public class Application {
    public static void main(String[] args) {
        Cat cat = new Cat("Benny", 3, 8.56);
        System.out.println(cat.equals(new Cat("Benny", 3, 8.56)));
        System.out.println(cat.equals(new Cat("Celeste", 6, 6.72)));
        System.out.println(cat);
    }
}
