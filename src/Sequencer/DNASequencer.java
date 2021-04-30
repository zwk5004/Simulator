package Sequencer;

import java.util.Random;

public class DNASequencer extends AbsSequencer {
    private Character[] bases = {'A', 'C', 'G', 'T'};
    private Random random = new Random();
    @Override
    public String sequence() {
        // TODO: Generate X Number of characters for a string.
        // Characters should be random of A-C-G-T
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 25000; i++){
            builder.append(this.bases[random.nextInt(4)]);
        }
        return builder.toString();
    }

    @Override
    public void setSample(String sample) {
        super.sample = sample;
    }
}
