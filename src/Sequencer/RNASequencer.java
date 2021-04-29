package Sequencer;

public class RNASequencer extends AbsSequencer {
    public RNASequencer() {
        super.sequencer = new DNASequencer();
    }

    @Override
    public String sequence() {
        String sequence = super.sequencer.sequence();
        // TODO: Process the sequence for RNA
        // A becomes U, T becomes A, C becomes G, G becomes C
        return "";
    }
}