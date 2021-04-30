package Sequencer;

public abstract class AbsSequencer implements SequencerIF {
    protected SequencerIF sequencer;
    String sample;
    String sequenced;

    public void setSample(String sample){
        this.sample = sample;
    }

}
