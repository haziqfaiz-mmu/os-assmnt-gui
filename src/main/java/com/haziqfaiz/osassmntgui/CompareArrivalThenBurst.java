package com.haziqfaiz.osassmntgui;

import java.util.Comparator;

public class CompareArrivalThenBurst implements Comparator<Job>{

    public int compare(Job j1, Job j2){

        if(j1.getArrivalTime() != j2.getArrivalTime()){
            return j1.getArrivalTime() - j2.getArrivalTime();
        }

        else
            return j1.getBurstTime() - j2.getBurstTime();
    }
}
