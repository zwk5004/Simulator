package machines;

public class IonTorrent extends AbsMachine  {
    @Override
    public void output() {
        // TODO: Output to FASTQ file.  Basically just a text file with a different extension.  Top row is below, most of it is random data
        // Below is the example of the top line and then a valid example of a sample
        // @<Run_ID>:<Chip_Row_Coordinate>:<Chip_Column_Coordinate>
        /*
         * @SIM:1:1
         * TCGCACTCAACGCCCTGCATATGACAAGACAGAATC
         */
    }
}
