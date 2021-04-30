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
        // T becomes A or U becomes A?
        for(int i = 0; i < 25000; i++){
            switch (this.sample.charAt(i)){
                case 'A':
                    this.sequenced += 'U';
                    break;
                case 'U':
                    this.sequenced += 'A';
                    break;
                case 'C':
                    this.sequenced += 'G';
                    break;
                case 'G':
                    this.sequenced += 'C';
                    break;

            }
        }
        return this.sequenced;
    }

    @Override
    public void setSample(String sample) {
        this.sample = sample;
    }
}