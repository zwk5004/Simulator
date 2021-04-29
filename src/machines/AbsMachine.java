package machines;

import Sequencer.SequencerIF;
import com.zwk5004.Rack;
import java.util.List;

public abstract class AbsMachine {
    private String type;
    private String name;
    protected List<Rack> racks;
    private int totalRacks;
    private int samplePerRack;
    private SequencerIF sequencer;

    public void loadRacks(){

    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public void process() {
        // TODO: select the correct sequencer filter, and start processing samples on the racks

        // Call output from the implemented classes
        output();
    }

    // TODO: This needs to output the file for the samples since the "processing" is done by the filter
    public abstract void output();
}
