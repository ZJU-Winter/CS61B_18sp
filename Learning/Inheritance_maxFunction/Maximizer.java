public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i += 1) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog[] dogs = { new Dog("1", 10), new Dog("2", 15), new Dog("Max", 20) };
        Dog maxDog = (Dog) Maximizer.max(dogs);
        maxDog.bark();
    }
}