package Inputs;

import java.io.File;
import java.util.Scanner;

public class Read {
    Scanner input;
    String sequence = "";

    public String getSequence(String type, int size){
        try{
            if(type.equals("DNA")){
                input = new Scanner(new File("DNA.txt"));
            }else if(type.equals("RNA")){
                input = new Scanner(new File("RNA.txt"));
            }else{
                // MISSING MRNA
            }

            for(int i = 0; i < size; i++){
                if(!input.next().equals('\n')){
                    sequence += input.next().charAt(0);
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return sequence;
    }
}
