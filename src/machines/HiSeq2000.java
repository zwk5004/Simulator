package machines;

import com.zwk5004.Rack;
import com.zwk5004.Sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class HiSeq2000 extends HiSeq {

    public HiSeq2000(String type){
        super(type);
    }
    @Override
    public void output() {
        // Output to FASTQ file.  Basically just a text file with a different extension.  Top row is below, most of it is random data
        // Below is the example of the top line and then a valid example of two samples and no controls.  Notice the lack of is filtered
        // @<instrument>:<run number>:<flowcell ID>:<lane>:<tile>:<x-pos>:<y-pos> <read>:<control number>:<sample number>
        /*
         * @SIM:1:FCX:1:15:6329:1045 1:0:2
         * TCGCACTCAACGCCCTGCATATGACAAGACAGAATC
         * +
         * <>;##=><9=AAAAAAAAAA9#:<#<;<<<????#=
         */
        try {
            String fileName = "hiseq2000_" + System.currentTimeMillis() + ".fastq";
            File fout = new File(location + fileName);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            int totalSamples = racks.stream().mapToInt(rack -> rack.getSamples().size()).sum();

            bw.write("@" + getMachineName() + ":1:1:1:1:1:1 N:0:" + totalSamples);
            bw.newLine();
            for (Rack rack : racks) {
                for (Sample sample : rack.getSamples()) {
                    bw.write(sample.getSequence());
                    bw.newLine();
                }
            }
            bw.write("+");
            bw.newLine();
            bw.write("<>;##=><9=AAAAAAAAAA9#:<#<;<<<????#=");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMachineName() {
        return "HiSeq 2000";
    }
}
