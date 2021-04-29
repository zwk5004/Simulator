package com.zwk5004;

public class Sample{
    private long id;
    private String sequence;

    public Sample(long id){
        this.id = id;
    }

    public void setSequence(String sequence){
        this.sequence = sequence;
    }

    public String getSequence(){
        return this.sequence;
    }
}
