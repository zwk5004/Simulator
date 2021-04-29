package com.zwk5004;

import machines.*;

public class MachineFactory {
    public AbsMachine loadMachine(String name){
       try{
          switch (name){
              case "HiSeq":
                  return new HiSeq();
              case "HiSeq2000":
                  return new HiSeq2000();
              case "HiSeq3000":
                  return new HiSeq3000();
              case "IonTorrent":
                  return new IonTorrent();
              case "PacBio":
                  return new PacBio();
              case "Sanger":
                  return new Sanger();
          }

       }catch(Exception e){
           System.out.println(e.getMessage());
       }
       return new AbsMachine();
    }
}
