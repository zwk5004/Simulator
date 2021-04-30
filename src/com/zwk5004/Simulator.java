package com.zwk5004;

import javafx.collections.ObservableArrayBase;
import machines.AbsMachine;

public class Simulator {
    private AbsMachine machine;

    public Simulator(String machine, String type){
        this.machine =  new MachineFactory().loadMachine(machine, type);
    }

    public void addRack(Rack rack){
        this.machine.loadRacks(rack);
    }

    public void run(){

    }
}
