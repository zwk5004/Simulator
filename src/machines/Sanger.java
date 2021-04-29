package machines;

public class Sanger extends AbsMachine {

    @Override
    public void output() {
        // TODO: Output to FASTQ file.  Basically just a text file with a different extension.  Top row is below, most of it is random data
        // Below is a valid example
        /*
         * @SRR014849.1 EIXKN4201CFU84 length=93
         * GGGGGGGGGGGGGGGCTTTTTTTTTTTTTGTTTGGAAACCGAAGGaAGAGAT
         */
    }
}
