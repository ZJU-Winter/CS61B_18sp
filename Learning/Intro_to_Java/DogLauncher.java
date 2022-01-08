public class DogLauncher {
    public static void main(String[] args) {
        Dog small = new Dog(10);
        Dog medium = new Dog(50);
        Dog big = new Dog(100);
        Dog test = Dog.maxDog(small, big);
        test.makeVoice();
    }

    public static class Dog {
        public int weightInpounds;

        public Dog(int w) {
            this.weightInpounds = w;
        }

        public void makeVoice() {
            if (weightInpounds < 10) {
                System.out.println("yep!");
            } else if (weightInpounds < 30) {
                System.out.println("bark.");
            } else {
                System.out.println("woooof.");
            }
        }

        public static Dog maxDog(Dog d1, Dog d2) {
            if (d1.weightInpounds > d2.weightInpounds) {
                return d1;
            } else
                return d2;
        }

        public Dog maxDog(Dog d) {
            if (this.weightInpounds > d.weightInpounds)
                return this;
            else
                return d;
        }
    }
}
