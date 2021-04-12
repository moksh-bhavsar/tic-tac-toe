package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene1, scene2, scene3;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Scene 1
        Label label1= new Label("Tic-Tac-Toe");
        label1.setFont(new Font("Arial", 30));
        label1.setLayoutX(140);
        label1.setLayoutY(50);
        Button joinButton= new Button("Join Game");
        Button hostButton = new Button("Host Game");
        Button aboutButton = new Button("About");
        hostButton.setOnAction(actionEvent -> primaryStage.setScene(scene3));
        joinButton.setOnAction(e -> primaryStage.setScene(scene2));
        Pane layout1 = new Pane();
        joinButton.setLayoutX(175);
        joinButton.setLayoutY(100);
        hostButton.setLayoutX(175);
        hostButton.setLayoutY(150);
        aboutButton.setLayoutX(185);
        aboutButton.setLayoutY(200);
        layout1.getChildren().addAll(label1, joinButton, hostButton, aboutButton);
        scene1= new Scene(layout1, 450, 450);


        //Scene 2
        Label label2= new Label("Join Game");
        Button playGame= new Button("Join");
        Button joinBack = new Button("Back");
        Label IP2 = new Label("IP");
        Label port2 = new Label("Port");
        TextField joinPort = new TextField();
        TextField joinIP = new TextField();
        joinBack.setOnAction(e -> primaryStage.setScene(scene1));
        Pane layout2= new Pane();
        playGame.setLayoutX(200);
        playGame.setLayoutY(400);
        label2.setLayoutX(390);
        IP2.setLayoutY(100);
        IP2.setFont(new Font("Arial", 20));
        port2.setLayoutY(200);
        port2.setFont(new Font("Arial", 20));
        joinIP.setLayoutY(130);
        joinPort.setLayoutY(230);
        joinIP.setLayoutX(100);
        joinPort.setLayoutX(100);
        layout2.getChildren().addAll(label2, playGame, joinBack, IP2, port2, joinPort, joinIP);
        scene2= new Scene(layout2,450,450);



        Pane layout3 = new Pane();
        Label IP = new Label("IP:");
        Label port = new Label("Port");
        TextField portNum = new TextField();
        Button backHost = new Button("Back");
        backHost.setOnAction(actionEvent -> primaryStage.setScene(scene1));
        IP.setLayoutY(100);
        IP.setFont(new Font("Arial", 20));
        port.setLayoutY(200);
        port.setFont(new Font("Arial", 20));
        portNum.setLayoutY(230);
        layout3.getChildren().addAll(IP, port, backHost, portNum);
        scene3 =  new Scene(layout3, 450 ,450);


        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
