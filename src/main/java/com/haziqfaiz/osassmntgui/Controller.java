package com.haziqfaiz.osassmntgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    private ArrayList<Job> finishedJobList = new ArrayList<Job>();

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

    public void solveButtonMainAction(ActionEvent event){
        arrivalTextFieldAction();
        burstTextFieldAction();

        if (arrivalTimeArray.length<3||burstTimeArray.length<3) {
            infoBox("Must be at least 3 processes", null, "Failed");
            return;
        }
        if (arrivalTimeArray.length>13||burstTimeArray.length>13) {
            infoBox("Must be at most 13 processes", null, "Failed");
            return;
        }
        if (arrivalTimeArray.length!=burstTimeArray.length) {
            infoBox("The number of inputs do not match", null, "Failed");
            return;
        }

       /** Window owner = solveButtonMain.getScene().getWindow();

        if (arrivalTimeArray.length<3 || burstTimeArray.length<3) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "There must be at least 3 processes");
            return;
        }

        if (arrivalTimeArray.length>13 || burstTimeArray.length>31) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "There must be at most 13 processes");
            return;
        }

        if (arrivalTimeArray.length!=burstTimeArray.length) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "The number of input in both fields do not match");
            return;
        }**/


        if(mainBox.getValue().equals("Round Robin")){

            try{
                RoundRobin rr = new RoundRobin();
                rr.fillJobList(arrivalTimeArray, burstTimeArray);
                rr.solve();
                gcArray = rr.getGcArray();
                finishedJobList = rr.getFinishedJobList();
                System.out.println("This is Round Robin");
                System.out.println("gcArray in main: "+gcArray);
                System.out.println("Finished Job List in main: "+finishedJobList);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
                Parent root = loader.load();
                ResultController resultController = loader.getController();
                resultController.setGcArray(gcArray);
                resultController.setFinishedJobList(finishedJobList);
                resultController.initialize();

                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Scheduling Algorithm Solver");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){}



        }
        else if(mainBox.getValue().equals("Non-Preemptive SJF")){

            try{
                NonPreemptiveSJF nsjf = new NonPreemptiveSJF();
                nsjf.fillJobList(arrivalTimeArray, burstTimeArray);
                nsjf.solve();
                gcArray = nsjf.getGcArray();
                finishedJobList = nsjf.getFinishedJobList();
                System.out.println("This is Non-Preemptive SJF");
                System.out.println("gcArray in main: "+gcArray);
                System.out.println("Finished Job List in main: "+finishedJobList);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
                Parent root = loader.load();
                ResultController resultController = loader.getController();
                resultController.setGcArray(gcArray);
                resultController.setFinishedJobList(finishedJobList);
                resultController.initialize();

                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Scheduling Algorithm Solver");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){}
        }
        else if(mainBox.getValue().equals("Preemptive SJF")){

            try{
                PreemptiveSJF psjf = new PreemptiveSJF();
                psjf.fillJobList(arrivalTimeArray, burstTimeArray);
                psjf.solve();
                gcArray = psjf.getGcArray();
                finishedJobList = psjf.getFinishedJobList();
                System.out.println("This is Preemptive SJF");
                System.out.println("gcArray in main: "+gcArray);
                System.out.println("Finished Job List in main: "+finishedJobList);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
                Parent root = loader.load();
                ResultController resultController = loader.getController();
                resultController.setGcArray(gcArray);
                resultController.setFinishedJobList(finishedJobList);
                resultController.initialize();

                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Scheduling Algorithm Solver");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){}
        }
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}