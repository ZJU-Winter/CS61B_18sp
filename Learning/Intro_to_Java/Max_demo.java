package Learning.Intro_to_Java;

public class Max_demo {
    /**
     * 
     * @param x
     * @param y
     * @return
     */

    public static int max(int x, int y) {
        if (x > y)
            return x;
        return y;
    }

    public static void main(String[] args) {
        int x;
        for (x = 0; x < 10; x++) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.print("The larger on is: " + max(-5, 5));
    }
}