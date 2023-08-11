package io.elementor.infra;

import io.elementor.infra.config.SLA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcSLAStats {
    private SLA sla;
    private List<Integer> samples;
    private int expected;


    public ConcSLAStats() {
        samples = new ArrayList<>();
    }

    public ConcSLAStats(SLA sla, int expected) {
        samples = new ArrayList<>();
        this.sla = sla;
        this.expected = expected;
    }

    public void addSample(int sample) {
        samples.add(sample);
    }

    public int getExpected() {
        return expected;
    }

    public void setExpected(int expected) {
        this.expected = expected;
    }

    public SLA getSla() {
        return sla;
    }

    public void setSla(SLA sla) {
        this.sla = sla;
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (int sameple : samples) {
            if (sameple < min) {
                min = sameple;
            }
        }
        return min;
    }

    public int getMax() {
        int max = Integer.MIN_VALUE;
        for (int sameple : samples) {
            if (sameple > max) {
                max = sameple;
            }
        }
        return max;
    }

    public double getAverage() {
        int total = 0;
        for (int sameple : samples) {
            total += sameple;
        }
        return total / samples.size();
    }

    public int getMedian() {
        Collections.sort(samples);
        return samples.get(samples.size() / 2);
    }

    public int getCount() {
        return samples.size();
    }
}
