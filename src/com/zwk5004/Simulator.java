package com.zwk5004;

import machines.AbsMachine;

import java.util.Observable;

public class Simulator extends Observable {
    private AbsMachine machine;

    public Simulator(String machine, String type){
        this.machine =  new MachineFactory().loadMachine(machine, type);
    }

    public void addRack(Rack rack){
        this.machine.loadRacks(rack);
    }

    public void run(){
        this.machine.start();
    }

    public void cancel() {
        this.machine.stop();
        notifyObservers();
    }
}
