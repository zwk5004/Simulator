package machines;

import com.zwk5004.Rack;
import com.zwk5004.Sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

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
         * >chr1 Jackalope;length=7
         * GATTACA
         * >chr2 Jackalope;length=7
         * TTACAGA
         */
        try {
            String fileName = "pacbio_" + System.currentTimeMillis() + ".fasta";
            File fout = new File(location + fileName);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            int i = 1;
            for (Rack rack : racks) {
                for (Sample sample : rack.getSamples()) {
                    bw.write(">chr" + i + " Sample;length=" + sample.getSequence().length());
                    bw.newLine();
                    bw.write(sample.getSequence());
                    bw.newLine();
                    i++;
                }
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMachineName() {
        return "PacBio";
    }

    @Override
    public String[] getSupportedTypes() {
        return new String[]{"DNA", "RNA"};
    }

    @Override
    public String[] getSupportedRackSize() {
        return new String[]{"32", "64", "100"};
    }

    @Override
    public int getMaxSamples() {
        return 5000;
    }
}
