package machines;

import Sequencer.DNASequencer;
import Sequencer.RNASequencer;
import Sequencer.SequencerIF;
import Sequencer.mRNASequencer;
import com.zwk5004.Rack;
import com.zwk5004.Sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public abstract class AbsMachine extends Thread implements Observer {
    private String type;
    private boolean running;
    protected String location;
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

    public void setLocation(String location) {
        if (!location.endsWith("/")) {
            location += "/";
        }
        this.location = location;
    }

    public void process() {
        // select the correct sequencer filter, and start processing samples on the racks
        List<Map<Sample, Rack>> sampleList = new LinkedList<>();

        for(Rack rack : racks){
            int i = 1;
            for(Sample sample : rack.getSamples()){
                // grab the output from the below statement as the sequenced output
                // sample.setSequence(sequencer.sequence());
                // rack.process();
                int samplePriority = calculateSamplePriority(i, rack);
                sample.setSamplePriority(samplePriority);
                Map<Sample, Rack> sampleToRack = new HashMap<>();
                sampleToRack.put(sample, rack);
                sampleList.add(sampleToRack);
                sampleList.sort((mapA, mapB) -> {
                    Sample a = new ArrayList<>(mapA.keySet()).get(0);
                    Sample b = new ArrayList<>(mapB.keySet()).get(0);
                    return a.getSamplePriority() - b.getSamplePriority();
                });
                i++;
            }
        }

        for (Map<Sample, Rack> sampleMap : sampleList) {
            Sample sample = new ArrayList<>(sampleMap.keySet()).get(0);
            Rack rack = sampleMap.get(sample);
            sample.setSequence(sequencer.sequence());
            rack.process();
        }
    }

    private int calculateSamplePriority(int sampleIdx, Rack rack) {
        return sampleIdx * rack.getPriority();
    }

    public void run() {
        process();
    }

    @Override
    public void update(Observable o, Object arg) {
        output();
    }

    // This needs to output the file for the samples since the "processing" is done by the filter
    public abstract void output();

    public abstract String getMachineName();
}
