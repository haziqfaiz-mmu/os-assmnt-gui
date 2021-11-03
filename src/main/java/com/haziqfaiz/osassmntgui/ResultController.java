package com.haziqfaiz.osassmntgui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultController {

    ArrayList<GanttChart> gcArray = new ArrayList<GanttChart>();
    ArrayList<Job> finishedJobList = new ArrayList<Job>();

    public void setGcArray(ArrayList<GanttChart> gcArray){
        this.gcArray = gcArray;
    }
    public void setFinishedJobList(ArrayList<Job> finishedJobList){this.finishedJobList = finishedJobList;}
    private double averageTT=0, averageWT=0, totalTT=0, totalWT=0;

    public ArrayList<GanttChart> getGcArray(){
        return this.gcArray;
    }

    @FXML
    private HBox hBox,hBox1;

    @FXML
    private Button backButton;

    @FXML
    TableView tableView;

    @FXML
    TableColumn<Job, String> processColumn;
    @FXML
    TableColumn<Job,Integer> waitTimeColumn;
    @FXML
    TableColumn<Job,Integer> turnAroundColumn;
    @FXML
    TableColumn<Job,Integer> burstTimeColumn;
    @FXML
    TableColumn<Job,Integer> arrivalTimeColumn;
    @FXML
    Label averageTText;
    @FXML
    Label averageWTText;

    public void initialize() {

        System.out.println("gcArray in result: "+gcArray);

        //Calculate Average TT and WT
        System.out.println("Finished JobList : "+finishedJobList);
        for(int i=0;i<finishedJobList.size();i++){
            totalTT = totalTT + finishedJobList.get(i).getTurnAroundTime();
            System.out.println(totalTT);
        }
        averageTT = totalTT/finishedJobList.size();

        for(int i=0;i<finishedJobList.size();i++){
            totalWT = totalWT + finishedJobList.get(i).getWaitTime();
        }
        averageWT = totalWT/finishedJobList.size();

        averageTText.setText(String.valueOf(averageTT));
        averageWTText.setText(String.valueOf(averageWT));

        //Fill in tables and columns
        for (int i = 0; i < gcArray.size(); i++) {
            //Fill in hBox
            Line line = new Line();
            line.setStroke(Color.WHITE);
            Rectangle rectangle = new Rectangle(90, 40, Color.TEAL);
            StackPane stackPane = new StackPane();
            Text text = new Text();
            text.setText(gcArray.get(i).getProcessName());
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

            ObservableList listStackPane = stackPane.getChildren();
            listStackPane.addAll(rectangle, text);

            hBox.getChildren().add(stackPane);
            hBox.getChildren().add(line);

            //Fill in hBox1
            Line line2 = new Line();
            line2.setStroke(Color.BLACK);
            Rectangle bigRectangle = new Rectangle(90, 40, Color.WHITE);
            StackPane stackPane2 = new StackPane();
            Text text2 = new Text();
            String str = gcArray.get(i).getTimeStart()+"       "+ gcArray.get(i).getTimeEnd();
            text2.setText(str);
            text2.setFont(Font.font("verdana", FontPosture.REGULAR, 15));

            ObservableList listStackPane2 = stackPane2.getChildren();
            listStackPane2.addAll(bigRectangle, text2);

            hBox1.getChildren().add(stackPane2);
            hBox1.getChildren().add(line2);
        }
        //Fill in table
        processColumn.setCellValueFactory(new PropertyValueFactory<>("JobID"));
        tableView.getColumns().add(processColumn);
        waitTimeColumn.setCellValueFactory(new PropertyValueFactory<>("WaitTime"));
        tableView.getColumns().add(waitTimeColumn);
        turnAroundColumn.setCellValueFactory(new PropertyValueFactory<>("TurnAroundTime"));
        tableView.getColumns().add(turnAroundColumn);
        burstTimeColumn.setCellValueFactory(new PropertyValueFactory<>("BurstTime"));
        tableView.getColumns().add(burstTimeColumn);
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("ArrivalTime"));
        tableView.getColumns().add(burstTimeColumn);

        for(int i=0;i<finishedJobList.size();i++){
            tableView.getItems().add(finishedJobList.get(i));
        }
    }

    public void backButtonAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Scheduling Algorithm Solver");
        stage.setScene(scene);
        stage.show();
    }
}
