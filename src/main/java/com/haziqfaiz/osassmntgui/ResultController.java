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
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
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

    GanttChart gc1 = new GanttChart("P1", 1, 10);
    GanttChart gc2 = new GanttChart("P2", 2, 20);
    GanttChart gc3 = new GanttChart("P3", 3, 30);

    @FXML
    private HBox hBox;

    @FXML
    private Button backButton;

    public void initialize() {

        gcArray.add(gc1);
        gcArray.add(gc2);
        gcArray.add(gc3);

        for (int i = 0; i < gcArray.size(); i++) {
            Line line = new Line();
            Rectangle rectangle = new Rectangle(90, 40, Color.TEAL);
            StackPane stackPane = new StackPane();
            Text text = new Text();
            text.setText(gcArray.get(i).getProcessName());
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

            ObservableList listStackPane = stackPane.getChildren();
            listStackPane.addAll(rectangle, text);

            hBox.getChildren().add(stackPane);
            hBox.getChildren().add(line);
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
