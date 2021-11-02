package com.haziqfaiz.osassmntgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    private ComboBox<String> mainBox;

    @FXML
    private ComboBox<String> priorityBox;

    @FXML
    private TextField arrivalTextField;

    @FXML
    private TextField burstTextField;

    @FXML
    private TextField priorityTextField;

    @FXML
    private Button solveButtonMain, solveButtonPriority;

    private int[] arrivalTimeArray, burstTimeArray, priorityArray;

    private String[] processIDArray;

    private Job[] jobsArray;

    private Queue<Job> jobsQueue = new LinkedList<Job>();

    private ArrayList<GanttChart> gcArray = new ArrayList<GanttChart>();

    public void setMainBox(){
        mainBox.setOnAction((event) -> {
            int selectedIndex = mainBox.getSelectionModel().getSelectedIndex();
            if (mainBox.getValue().equals("Preemptive Priority")||mainBox.getValue().equals("Non-Preemptive Priority")){
                try{
                    System.out.println("Priority chosen");
                    Parent root = FXMLLoader.load(getClass().getResource("PriorityInput.fxml"));

                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setTitle("Scheduling Algorithm Solver");
                    stage.setScene(scene);
                    stage.show();
                }catch (IOException exception){}

            }
        });
    }

    public void setPriorityBox() throws IOException {

        priorityBox.setOnAction((event) ->{
            int selectedIndex = priorityBox.getSelectionModel().getSelectedIndex();
            System.out.println(priorityBox.getValue());
            if (priorityBox.getValue().equals("Round Robin") || priorityBox.getValue().equals("Non-Preemptive SJF") || priorityBox.getValue().equals("Preemptive SJF")){

                try{
                    System.out.println("Non Priority chosen");
                    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setTitle("Scheduling Algorithm Solver");
                    stage.setScene(scene);
                    stage.show();
                }catch (IOException exception){}
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void arrivalTextFieldAction(){
        String input = arrivalTextField.getText();
        String[] inputArr = input.split("\\s+");
        System.out.println(Arrays.toString(inputArr));
        arrivalTimeArray = new int[inputArr.length];

        //convert from String to Integer;
        for(int i=0;i<inputArr.length;i++){
            arrivalTimeArray[i] = Integer.parseInt(inputArr[i]);
        }
        System.out.print("Converted to int: ");
        System.out.println(Arrays.toString(arrivalTimeArray));
    }

    public void burstTextFieldAction(){
        String input = burstTextField.getText();
        String[] inputArr = input.split("\\s+");
        System.out.println(Arrays.toString(inputArr));
        burstTimeArray = new int[inputArr.length];

        //convert from String to Integer;
        for(int i=0;i<inputArr.length;i++){
            burstTimeArray[i] = Integer.parseInt(inputArr[i]);
        }
        System.out.print("Converted to int: ");
        System.out.println(Arrays.toString(burstTimeArray));
    }

    public void priorityTextFieldAction(){
        String input = priorityTextField.getText();
        String[] inputArr = input.split("\\s+");
        System.out.println(Arrays.toString(inputArr));
        priorityArray = new int[inputArr.length];

        //convert from String to Integer;
        for(int i=0;i<inputArr.length;i++){
            priorityArray[i] = Integer.parseInt(inputArr[i]);
        }
        System.out.print("Converted to int: ");
        System.out.println(Arrays.toString(priorityArray));
    }

    public void solveButtonMainAction(){
        arrivalTextFieldAction();
        burstTextFieldAction();


        if(mainBox.getValue().equals("Round Robin")){
            RoundRobin rr = new RoundRobin();
            rr.fillJobList(arrivalTimeArray, burstTimeArray);
            rr.solve();
            gcArray = rr.getGcArray();
            System.out.println("This is Round Robin");
        }
        else if(mainBox.getValue().equals("Non-Preemptive SJF")){
            NonPreemptiveSJF nsjf = new NonPreemptiveSJF();
            nsjf.fillJobList(arrivalTimeArray,burstTimeArray);
            nsjf.solve();
            gcArray = nsjf.getGcArray();
            System.out.println("This is Non-Preemptive SJF");
        }
        else if(mainBox.getValue().equals("Preemptive SJF")){
            PreemptiveSJF psjf = new PreemptiveSJF();
            psjf.fillJobList(arrivalTimeArray,burstTimeArray);
            psjf.solve();
            gcArray = psjf.getGcArray();
            System.out.println("This is Preemptive SJF");
        }
    }

}