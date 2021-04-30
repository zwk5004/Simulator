package com.zwk5004;

import java.util.List;

public class Rack {
    private List<Sample> samples;
    private int priority;
    private int progress = 0;

    public Rack(int priority){
        this.priority = priority;
    }

    public void addSample(Sample sample){
        this.samples.add(sample);
    }

    public void addSamples(List<Sample> samples){
        this.samples.addAll(samples);
    }

    public void process(){
        // TODO: Maybe have to do more than just incrementing the progress here
        progress++;
    }
}
