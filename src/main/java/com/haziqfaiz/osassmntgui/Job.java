package com.haziqfaiz.osassmntgui;

public class Job {

    private int arrivalTime, burstTime, remainingBurstTime, priority, processorTime;
    private String jobID;
    private Boolean jobStatus;

    Job(){
       this(null,0,0,0);
    }

    Job(String jobID,int arrivalTime, int burstTime){
        setJobID(jobID);
        setArrivalTime(arrivalTime);
        setBurstTime(burstTime);
    }

    Job(String jobID, int arrivalTime, int burstTime, int priority){
        this(jobID,arrivalTime,burstTime);
        setPriority(priority);
    }

    public void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime){
        this.burstTime = burstTime;
    }

    public void setRemainingBurstTime(int remainingBurstTime){
        this.remainingBurstTime = remainingBurstTime;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public void setProcessorTime(int processorTime){
        this.processorTime = processorTime;
    }

    public void setJobID(String jobID){
        this.jobID = jobID;
    }

    public void setJobStatus(Boolean jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getProcessorTime() {
        return processorTime;
    }

    public int getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public String getJobID() {
        return jobID;
    }

    public Boolean getJobStatus() {
        return jobStatus;
    }
}
