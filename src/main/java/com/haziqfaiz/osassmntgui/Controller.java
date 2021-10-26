package com.haziqfaiz.osassmntgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

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

    public void setMainBox(){
        mainBox.setOnAction((event) -> {
            int selectedIndex = mainBox.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 2){
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
            if (!priorityBox.getValue().equals("Priority")){

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

    public void arrivalTextAction(){
        String input = arrivalTextField.getText();
        String[] inputArr = input.split("\\s+");
        System.out.println(Arrays.toString(inputArr));
        System.out.println(inputArr[1]);
    }


}