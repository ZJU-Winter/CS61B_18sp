public class DogLauncher {
    public static void main(String[] args) {
        Dog[] dogs = { new Dog("1", 10), new Dog("2", 15), new Dog("Max", 20) };
        Dog maxDog = (Dog) Maximizer.max(dogs);
        maxDog.bark();
    }
}
