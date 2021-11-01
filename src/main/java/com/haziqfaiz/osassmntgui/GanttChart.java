package com.haziqfaiz.osassmntgui;

public class GanttChart {
    private String processName;
    private Integer timeStart, timeEnd;

    GanttChart(){}

    GanttChart(String processName, Integer timeStart, Integer timeEnd){
        setProcessName(processName);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public void setProcessName(String processName){
        this.processName = processName;
    }

    public void setTimeStart(Integer timeStart){
        this.timeStart = timeStart;
    }

    public void setTimeEnd(Integer timeEnd){
        this.timeEnd = timeEnd;
    }

    public String getProcessName(){
        return this.processName;
    }

    public Integer getTimeStart(){
        return this.timeStart;
    }

    public Integer getTimeEnd(){
        return this.timeEnd;
    }

    public String toString(){
        return "| "+processName+ " "+timeStart+" "+timeEnd+ " |";
    }
}
