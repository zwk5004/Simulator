package Sequencer;

public class mRNASequencer extends AbsSequencer {
    public mRNASequencer() {
        super.sequencer = new RNASequencer();
    }

    @Override
    public String sequence() {
        // A becomes U, U becomes A, C becomes G, G becomes C
        String mRNA = super.sequencer.sequence();
        mRNA = mRNA.replaceAll("A", "U");
        mRNA = mRNA.replaceAll("U", "A");
        mRNA = mRNA.replaceAll("C", "G");
        mRNA = mRNA.replaceAll("G", "C");
        return mRNA;
    }
}
