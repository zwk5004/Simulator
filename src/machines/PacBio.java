package machines;

public class PacBio extends AbsMachine  {

    public PacBio(String type){
        super(type);
    }
    @Override
    public void output() {
        // TODO: Output to FASTA file. Different from FASTQ. Basically just a text file with a different extension.  Top row is below, most of it is random data
        // Below is a valid example of two samples and no controls
        // The first part must be unique, then the next is description, followed by chromosome number and then length
        /*
         * >chr1 Jackalope chromosome 1;length=7
         * GATTACA
         * >chr2 Jackalope chromosome 2;length=7
         * TTACAGA
         */
    }
}
