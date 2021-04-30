package com.zwk5004;

import javafx.collections.ObservableArrayBase;
import machines.AbsMachine;

public class Simulator {
    private AbsMachine machine;

    public Simulator(String machine){
        this.machine =  new MachineFactory().loadMachine(machine);
    }

    public void addRack(Rack rack){
        this.machine.loadRacks(rack);
    }

    public void run(){

    }
}
