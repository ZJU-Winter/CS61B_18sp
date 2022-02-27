package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double factor;
    AcceleratingSawToothGenerator(int period, double factor) {
        this.period = period;
        this.factor = factor;
        state = 0;
    }

    @Override
    public double next() {
        state += 1;
        if (state == period) {
            state = 0;
            period = (int)(period * factor);
        }
        return normalize();
    }

    private double normalize() {
        return ((double)state / period) * 2 - 1;
    }
}
