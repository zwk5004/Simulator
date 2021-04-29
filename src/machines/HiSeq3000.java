package machines;

public class HiSeq3000 extends HiSeq {
    @Override
    public void output() {
        // TODO: Output to FASTQ file.  Basically just a text file with a different extension.  Top row is below, most of it is random data
        // Below is the example of the top line and then a valid example of two samples and no controls
        // @<instrument>:<run number>:<flowcell ID>:<lane>:<tile>:<x-pos>:<y-pos> <read>:<is filtered>:<control number>:<sample number>
        /*
         * @SIM:1:FCX:1:15:6329:1045 1:N:0:2
         * TCGCACTCAACGCCCTGCATATGACAAGACAGAATC
         * +
         * <>;##=><9=AAAAAAAAAA9#:<#<;<<<????#=
         */
    }
}
