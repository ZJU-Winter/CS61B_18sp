package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }

    @Override
    public double next() {
        state += 1;
        int weirdState = state & (state >> 3) & (state >> 8) % period;
        return normalize(weirdState);
    }

    private double normalize(int weirdState) {
        return ((double)weirdState / period) * 2 - 1;
    }
}
