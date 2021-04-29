package Sequencer;

public class mRNASequencer extends AbsSequencer {
    public mRNASequencer() {
        super.sequencer = new RNASequencer();
    }

    @Override
    public String sequence() {
        String sequence = super.sequencer.sequence();
        // TODO: Process the sequence for mRNA
        // A becomes U, U becomes A, C becomes G, G becomes C
        return "";
    }
}
