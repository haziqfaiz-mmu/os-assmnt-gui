package com.haziqfaiz.osassmntgui;

import java.util.Comparator;

public class CompareBurst implements Comparator<Job> {

    public int compare(Job j1, Job j2){

        return j1.getBurstTime() - j2.getBurstTime();
    }
}