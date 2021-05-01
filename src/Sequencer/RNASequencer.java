package Sequencer;

public class RNASequencer extends AbsSequencer {
    public RNASequencer() {
        super.sequencer = new DNASequencer();
    }

    @Override
    public String sequence() {
        String rna = super.sequencer.sequence();
        // Process the sequence for RNA
        // A becomes U, T becomes A, C becomes G, G becomes C
        // T becomes A or U becomes A?
        rna = rna.replaceAll("A", "U");
        rna = rna.replaceAll("T", "A");
        rna = rna.replaceAll("C", "G");
        rna = rna.replaceAll("G", "C");
        return rna;
    }
}