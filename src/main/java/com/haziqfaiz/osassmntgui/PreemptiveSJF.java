package com.haziqfaiz.osassmntgui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class PreemptiveSJF {

    private ArrayList<Job> jobList = new ArrayList<Job>();
    private ArrayList<GanttChart> gcArray = new ArrayList<GanttChart>();
    private ArrayList<Job> finishedJobList = new ArrayList<Job>();

    public ArrayList<Job> getJobList(){
        return this.jobList;
    }

    public ArrayList<GanttChart> getGcArray(){
        return this.gcArray;
    }

    public ArrayList<Job> getFinishedJobList(){return this.finishedJobList;}
    public void fillJobList(int[] arrivalTimeArray, int[] burstTimeArray){

        for(int i=0;i<arrivalTimeArray.length;i++){
            StringBuilder str = new StringBuilder("P");
            str.append(i);

            Job newJob = new Job(str.toString(),arrivalTimeArray[i],burstTimeArray[i]);
            jobList.add(newJob);
        }
    }

    public void solve(){
        int cpuTime=0,avgWT, avgTT;

        LinkedList<Job> waitQueue = new LinkedList<Job>();

        Collections.sort(jobList, new CompareArrivalThenBurst());

        System.out.println("Job list: "+jobList);

        while(!jobList.isEmpty() || !waitQueue.isEmpty()){

            while((!jobList.isEmpty()&&waitQueue.isEmpty())){

                //add the first job to waitqueue this means joblist is not empty
                checkForNewArrival(jobList, waitQueue, cpuTime);
                //System.out.println(cpuTime);
                if(waitQueue.isEmpty())
                    cpuTime++;
            }

            //System.out.println("Waitqueue before process: "+waitQueue);

            Job temp = waitQueue.poll();
            System.out.println("Waitqueue before process: "+waitQueue);
            System.out.println("Before: process "+temp.getJobID()+" "+temp.getRemainingBurstTime());
            cpuTime = process(temp, cpuTime, jobList, waitQueue, gcArray);
            System.out.println("After: process "+temp.getJobID()+" "+temp.getRemainingBurstTime());
            System.out.println("Waitqueue after process: "+waitQueue);


            System.out.println("cpuTime = "+cpuTime);
            //System.out.println(waitQueue);

            if(!temp.getJobStatus()){
                waitQueue.add(temp);
                Collections.sort(waitQueue,new CompareBurst());
            }
            //System.out.println("Waitqueue after process: "+waitQueue);
        }
        System.out.println(gcArray);
    }

    public void checkForNewArrival(ArrayList<Job> jobList, LinkedList<Job> waitQueue, int cpuTime){

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

    public int process(Job j, int cpuTime, ArrayList<Job> jobList, LinkedList<Job> waitQueue, ArrayList<GanttChart> gcArray){

        int cpuTimeCopy = cpuTime;

        while(j.getRemainingBurstTime()>0){
            //System.out.println("Current burst time: "+ j.getRemainingBurstTime());
            //System.out.println("Current cpu time: "+ cpuTime);
            j.setRemainingBurstTime(j.getRemainingBurstTime()-1);
            cpuTime++;
            checkForNewArrival(jobList, waitQueue, cpuTime);

            if(!waitQueue.isEmpty()){
                if(j.getRemainingBurstTime()>waitQueue.peek().getRemainingBurstTime()){
                    break;
                }
            }
        }
        GanttChart gcElement = new GanttChart(j.getJobID(),cpuTimeCopy,cpuTime);
        gcArray.add(gcElement);

        if(j.getRemainingBurstTime()==0){
            j.setJobStatus(true);
            j.setCompletionTime(cpuTime);
            j.setTurnAroundTime(j.getCompletionTime()-j.getArrivalTime());
            j.setWaitTime(j.getTurnAroundTime()-j.getBurstTime());
            finishedJobList.add(j);
        }

        return cpuTime;

    }
}
