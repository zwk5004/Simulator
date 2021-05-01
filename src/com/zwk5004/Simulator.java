package com.zwk5004;

import machines.AbsMachine;

import javax.swing.JProgressBar;
import java.util.List;
import java.util.Observable;

public class Simulator extends Observable {
    private AbsMachine machine;

    public Simulator(String machine, String type){
        this.machine =  MachineFactory.loadMachine(machine, type);
        addObserver(this.machine);
    }

    public void addRack(Rack rack){
        this.machine.loadRacks(rack);
    }

    public void run(){
        this.machine.start();
    }

    public void cancel() {
        setChanged();
        notifyObservers();
        this.machine.stop();
    }

    public void alignProgressBars(List<JProgressBar> bars) {
        this.machine.setProgressBars(bars);
    }

    public AbsMachine getMachine() {
        return machine;
    }
}
