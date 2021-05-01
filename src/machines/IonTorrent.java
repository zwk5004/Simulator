package machines;

import com.zwk5004.Rack;
import com.zwk5004.Sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class IonTorrent extends AbsMachine  {

    public IonTorrent(String type){
        super(type);
    }
    @Override
    public void output() {
        // Output to FASTQ file.  Basically just a text file with a different extension.  Top row is below, most of it is random data
        // Below is the example of the top line and then a valid example of a sample
        // @<Run_ID>:<Chip_Row_Coordinate>:<Chip_Column_Coordinate>
        /*
         * @SIM:1:1
         * TCGCACTCAACGCCCTGCATATGACAAGACAGAATC
         */
        try {
            String fileName = "iontorrent_" + System.currentTimeMillis() + ".fastq";
            File fout = new File(location + fileName);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write("@" + getMachineName() + ":1:1");
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
        return "Ion Torrent";
    }
}
