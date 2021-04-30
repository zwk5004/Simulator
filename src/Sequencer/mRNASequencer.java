package Sequencer;

public class mRNASequencer extends AbsSequencer {
    public mRNASequencer() {
        super.sequencer = new RNASequencer();
    }

    @Override
    public String sequence() {
        // TODO: Process the sequence for mRNA
        // A becomes U, U becomes A, C becomes G, G becomes C
        return super.sequencer.sequence();
    }

    @Override
    public void setSample(String sample) {
        super.sequencer.setSample(sample);
    }
}
