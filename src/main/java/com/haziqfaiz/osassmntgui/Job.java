package com.haziqfaiz.osassmntgui;

public class Job implements Comparable<Job> {

    private int arrivalTime, burstTime, remainingBurstTime, priority, completionTime, waitTime, turnAroundTime;
    private String jobID;
    private Boolean jobStatus;

    Job(){
        this(null,0,0,0);
    }

    Job(String jobID,int arrivalTime, int burstTime){
        setJobID(jobID);
        setArrivalTime(arrivalTime);
        setBurstTime(burstTime);
        setRemainingBurstTime(burstTime);
        setWaitTime(0);
        setTurnAroundTime(0);
        setJobStatus(false);
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

    public void setProcessorTime(int completionTime){
        this.completionTime = completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
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
        return completionTime;
    }

    public int getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public int getCompletionTime(){
        return completionTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public String getJobID() {
        return jobID;
    }

    public Boolean getJobStatus() {
        return jobStatus;
    }

    @Override
    public int compareTo(Job o) {
        return this.arrivalTime - o.arrivalTime;
    }

    public String toString(){
        return this.jobID;
    }
}
