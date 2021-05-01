package com.zwk5004;

import machines.*;

public class MachineFactory {
    public static AbsMachine loadMachine(String name, String type) {
        switch (name) {
            case "HiSeq2000":
                return new HiSeq2000(type);
            case "HiSeq3000":
                return new HiSeq3000(type);
            case "IonTorrent":
                return new IonTorrent(type);
            case "PacBio":
                return new PacBio(type);
            case "Sanger":
                return new Sanger(type);
        }
        throw new RuntimeException("Machine of " + name + " not supported.");
    }
}
