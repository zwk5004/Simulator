package machines;

import com.zwk5004.Rack;
import com.zwk5004.Sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Sanger extends AbsMachine {

    public Sanger(String type){
        super(type);
    }

    @Override
    public void output() {
        // Output to FASTQ file.  Basically just a text file with a different extension.  Top row is below, most of it is random data
        // Below is a valid example
        /*
         * @SRR014849.1 EIXKN4201CFU84 length=93
         * GGGGGGGGGGGGGGGCTTTTTTTTTTTTTGTTTGGAAACCGAAGGaAGAGAT
         */
        try {
            String fileName = "sanger_" + System.currentTimeMillis() + ".fastq";
            File fout = new File(location + fileName);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            int totalSamples = racks.stream().mapToInt(rack -> rack.getSamples().size()).sum();

            bw.write("@SRR014849.1 " + getMachineName() + " length=" + totalSamples);
            bw.newLine();
            for (Rack rack : racks) {
                for (Sample sample : rack.getSamples()) {
                    bw.write(sample.getSequence());
                    bw.newLine();
                }
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMachineName() {
        return "Sanger";
    }

    @Override
    public String[] getSupportedTypes() {
        return new String[]{"DNA", "mRNA"};
    }

    @Override
    public String[] getSupportedRackSize() {
        return new String[]{"32", "64"};
    }

    @Override
    public int getMaxSamples() {
        return 4000;
    }
}
