package com.haziqfaiz.osassmntgui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class NonPreemptiveSJF {

    public static void solve(){
        int cpuTime=0,avgWT, avgTT;
        ArrayList<Job> jobList = new ArrayList<Job>();
        ArrayList<GanttChart> gcArray = new ArrayList<GanttChart>();
        Job j1 = new Job("j1",0,6);
        Job j2 = new Job("j2",1,4);
        Job j3 = new Job("j3",5,6);
        /**Job j4 = new Job("j4",6,6);
         Job j5 = new Job("j5",7,6);
         Job j6 = new Job("j6",8,6);**/
        LinkedList<Job> waitQueue = new LinkedList<Job>();

        jobList.add(j1);
        jobList.add(j2);
        jobList.add(j3);
        /**jobList.add(j4);
         jobList.add(j5);
         jobList.add(j6);**/
        Collections.sort(jobList, new CompareArrivalthenBurst());

        System.out.println("Job list: "+jobList.toString());

        while(!jobList.isEmpty() || !waitQueue.isEmpty()){

            while((!jobList.isEmpty()&&waitQueue.isEmpty())){

                //add the first job to waitqueue this means joblist is not empty
                checkForNewArrival(jobList, waitQueue, cpuTime);
                //System.out.println(cpuTime);
                if(waitQueue.isEmpty())
                    cpuTime++;
            }

            System.out.println("Waitqueue before process: "+waitQueue);

            Job temp = waitQueue.poll();
            System.out.println("Before: process "+temp.getJobID()+" "+temp.getRemainingBurstTime());
            cpuTime = process(temp, cpuTime, jobList, waitQueue, gcArray);
            System.out.println("After: process "+temp.getJobID()+" "+temp.getRemainingBurstTime());

            System.out.println("cpuTime = "+cpuTime);
            //System.out.println(waitQueue);

            if(temp.getJobStatus()==false){
                waitQueue.add(temp);
                Collections.sort(waitQueue,new CompareBurst());
            }
            System.out.println("Waitqueue after process: "+waitQueue);
        }
        System.out.println(gcArray);
    }

    public static void checkForNewArrival(ArrayList<Job> jobList, LinkedList<Job> waitQueue, int cpuTime){

        boolean flag = true;
        int i=0;
        while(flag && i<jobList.size()){
            //System.out.println("i is "+i);
            Job temp = jobList.get(i);

            if(temp.getArrivalTime()>cpuTime){
                flag = false;
            }

            if(temp.getArrivalTime()==cpuTime){
                waitQueue.add(temp);
                jobList.remove(i);
                i--;
            }
            i++;
        }
        Collections.sort(waitQueue,new CompareBurst());;
    }

    public static int process(Job j, int cpuTime, ArrayList<Job> jobList, LinkedList<Job> waitQueue, ArrayList<GanttChart> gcArray){

        int cpuTimeCopy = cpuTime;

        while(j.getRemainingBurstTime()>0){
            //System.out.println("Current burst time: "+ j.getRemainingBurstTime());
            //System.out.println("Current cpu time: "+ cpuTime);
            j.setRemainingBurstTime(j.getRemainingBurstTime()-1);
            cpuTime++;
            checkForNewArrival(jobList, waitQueue, cpuTime);
        }

        GanttChart gcElement = new GanttChart(j.getJobID(),cpuTimeCopy,cpuTime);
        gcArray.add(gcElement);

        if(j.getRemainingBurstTime()==0){
            j.setJobStatus(true);
            j.setCompletionTime(cpuTime);
        }

        return cpuTime;

    }

    public static void main(String[] args){
        solve();
    }

}
