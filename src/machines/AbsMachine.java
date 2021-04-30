package machines;

import Sequencer.DNASequencer;
import Sequencer.RNASequencer;
import Sequencer.SequencerIF;
import Sequencer.mRNASequencer;
import com.zwk5004.Rack;
import com.zwk5004.Sample;

import java.util.List;

public abstract class AbsMachine {
    private String type;
    private String name;
    protected List<Rack> racks;
    private int totalRacks;
    private int samplePerRack;
    private SequencerIF sequencer;

    public AbsMachine(String type){
        this.type = type;
        switch(this.type){
            case "DNA":
                this.sequencer = new DNASequencer();
                break;
            case"RNA":
                this.sequencer = new RNASequencer();
                break;
            case "mRNA":
                this.sequencer = new mRNASequencer();
                break;
        }
    }

    public void loadRacks(Rack racks){
        this.racks.add(racks);
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public void process() {
        // TODO: select the correct sequencer filter, and start processing samples on the racks
        for(Rack rack : racks){
            for(Sample sample : rack.getSamples()){
                sequencer.setSample(sample.getSequence());
                // The output will be here!!!!! = sequencer.sequence()
            }
        }
        // Call output from the implemented classes
        output();
    }

    // TODO: This needs to output the file for the samples since the "processing" is done by the filter
    public abstract void output();
}
