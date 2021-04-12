package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class Main extends Application {

    Scene scene1, scene2, scene3;
    ServerSocket hostSocket = null;
    Socket joinSocket;
    InetAddress ipAddress = null;
    int port1;

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

        Button playGame= new Button("Join");
        Button joinBack = new Button("Back");
        Label IP2 = new Label("IP:");
        Label port2 = new Label("Port:");
        TextField joinPort = new TextField();
        TextField joinIP = new TextField();
        joinBack.setOnAction(e -> primaryStage.setScene(scene1));
        Pane layout2= new Pane();
        playGame.setLayoutX(200);
        playGame.setLayoutY(400);
        IP2.setLayoutY(100);
        IP2.setFont(new Font("Arial", 20));
        port2.setLayoutY(200);
        port2.setFont(new Font("Arial", 20));
        joinIP.setLayoutY(100);
        joinPort.setLayoutY(200);
        joinIP.setLayoutX(100);
        joinPort.setLayoutX(100);
        layout2.getChildren().addAll(playGame, joinBack, IP2, port2, joinPort, joinIP);
        scene2= new Scene(layout2,450,450);


        //Scene 3
        Pane layout3 = new Pane();
        Label IP = new Label("IP:");
        Label port = new Label("Port:");
        TextField portNum = new TextField();
        TextField hostIP = new TextField();
        Button backHost = new Button("Back");
        Button hostGame = new Button("Host");
        backHost.setOnAction(actionEvent -> primaryStage.setScene(scene1));
        IP.setLayoutY(100);
        IP.setFont(new Font("Arial", 20));
        hostIP.setLayoutX(130);
        hostIP.setLayoutY(100);
        port.setLayoutY(200);
        port.setFont(new Font("Arial", 20));
        portNum.setLayoutX(130);
        portNum.setLayoutY(200);
        hostGame.setLayoutX(200);
        hostGame.setLayoutY(400);
        layout3.getChildren().addAll(IP, port, backHost, portNum, hostIP, hostGame);
        scene3 =  new Scene(layout3, 450 ,450);

        playGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ipAddress = InetAddress.getByName(joinIP.getText());
                    port1 = Integer.parseInt(joinPort.getText());
                    joinSocket = new Socket(ipAddress, port1);
                    if (joinSocket.isConnected()){
                        System.out.println("Connected Successfully!!!");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        hostGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ipAddress = InetAddress.getByName(hostIP.getText());
                    port1 = Integer.parseInt(portNum.getText());
                    hostSocket = new ServerSocket(port1, 10, ipAddress);
                    if (!hostSocket.isClosed()){
                        System.out.println("Server hosted!!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
