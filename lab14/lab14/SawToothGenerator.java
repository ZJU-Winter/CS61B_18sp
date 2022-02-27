package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    @Override
    public double next() {
        state += 1;
        if (state == period)
            state = 0;
        return normalize();
    }

    private double normalize() {
        return ((double)state / period) * 2 - 1;
    }
}
