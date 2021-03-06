package sample;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;

public class Main extends Application {


    ServerSocket hostSocket = null;
    Socket joinSocket;
    InetAddress ipAddress = null;
    int port1;
    private Scene mainScene, joinScene, hostScene, aboutScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Main Screen

        //Create labels and buttons for main screen navigation
        Label label1= new Label("Tic-Tac-Toe");
        label1.setFont(new Font("Arial", 30));
        label1.setLayoutX(140);
        label1.setLayoutY(50);

        Button joinButton= new Button("Join Game");
        Button hostButton = new Button("Host Game");
        Button aboutButton = new Button("About");

        //set buttons to maneuver between screens
        hostButton.setOnAction(actionEvent -> primaryStage.setScene(hostScene));

        joinButton.setOnAction(e -> primaryStage.setScene(joinScene));

        aboutButton.setOnAction(e -> primaryStage.setScene(aboutScene));



        Pane layout1 = new Pane();

        joinButton.setLayoutX(175);
        joinButton.setLayoutY(100);
        hostButton.setLayoutX(175);
        hostButton.setLayoutY(150);
        aboutButton.setLayoutX(185);
        aboutButton.setLayoutY(200);

        layout1.getChildren().addAll(label1, joinButton, hostButton, aboutButton);
        mainScene = new Scene(layout1, 450, 450);


        //Join Game Screen

       /* Label label2= new Label("Join Game");*/
        Button playGame= new Button("Join");
        Button joinBack = new Button("Back");
        Label IP2 = new Label("IP:");
        Label port2 = new Label("Port:");
        TextField joinPort = new TextField("12312");
        TextField joinIP = new TextField("localhost");

        joinBack.setOnAction(e -> primaryStage.setScene(mainScene));

        Pane layout2= new Pane();
        playGame.setLayoutX(200);
        playGame.setLayoutY(400);
        //label2.setLayoutX(390);
        IP2.setLayoutY(100);
        IP2.setFont(new Font("Arial", 20));
        port2.setLayoutY(200);
        port2.setFont(new Font("Arial", 20));
        joinIP.setLayoutY(100);
        joinPort.setLayoutY(200);
        joinIP.setLayoutX(130);
        joinPort.setLayoutX(130);

        layout2.getChildren().addAll(/*label2,*/ playGame, joinBack, IP2, port2, joinPort, joinIP);
        joinScene = new Scene(layout2,450,450);

        playGame.setOnAction(e -> primaryStage.setScene(joinScene));


        // Host game screen

        Pane layout3 = new Pane();
        Label IP = new Label("IP:");
        Label port = new Label("Port:");
        TextField portNum = new TextField("12312");
        TextField hostIP = new TextField("localhost");
        Button backHost = new Button("Back");
        Button hostGame = new Button("Host");

        backHost.setOnAction(actionEvent -> primaryStage.setScene(mainScene));

        IP.setLayoutY(100);
        IP.setFont(new Font("Arial", 20));
        hostIP.setLayoutX(130);
        hostIP.setLayoutY(100);
        port.setLayoutY(200);
        port.setFont(new Font("Arial", 20));
        portNum.setLayoutY(200);
        portNum.setLayoutX(130);

        hostGame.setLayoutX(200);
        hostGame.setLayoutY(400);

        layout3.getChildren().addAll(IP, port, backHost, portNum, hostIP,hostGame);
        hostScene =  new Scene(layout3, 450 ,450);

        // About Screen

        Pane aboutLayout = new Pane();

        Label descriptionTitle = new Label("Project Description:");
        Text description = new Text("This project is a 2-player multi-threaded tic-tac-toe game that uses socket\nprogramming to connect both players." +
                " The project was written by Alec Tozac,\nManhoor Yousaf, Moksh Bhavsar, Utsav Dwivedi.");

        description.setLayoutY(50);

        Label howToTitle = new Label("How to run:");

        howToTitle.setLayoutY(100);

        Text howTo = new Text("To run the program the user must first launch the Main application in order to\nhost or join a game. " +
                "After this, they connect to the server through the user interface\nand begin to play the game.");

        howTo.setLayoutY(150);

        Button aboutBack = new Button("Back");

        aboutBack.setLayoutX(200    );
        aboutBack.setLayoutY(400);

        aboutBack.setOnAction(event -> primaryStage.setScene(mainScene));

        aboutLayout.getChildren().addAll(descriptionTitle, description, howToTitle, howTo, aboutBack);
        aboutScene = new Scene(aboutLayout, 450, 450);

        // user can join game if IPaddress and port are correct, and see the game board
        playGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // retrieving ipaddress and port number from user
                    ipAddress = InetAddress.getByName(joinIP.getText());
                    port1 = Integer.parseInt(joinPort.getText());

                    // binding socket to the user provided ipaddress and port number
                    joinSocket = new Socket(ipAddress, port1);

                    // show the game board if the socket is connected
                    if (joinSocket.isConnected()){
                        System.out.println("Connected Successfully!!!");
                        Scene board = new Board(joinSocket, false).getScene();
                        primaryStage.setScene(board);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // user can host a game with given IPaddress and port number, and see
        // the game board after another player joins.
        hostGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // retrieving ipaddress and port number from user
                    ipAddress = InetAddress.getByName(hostIP.getText());
                    port1 = Integer.parseInt(portNum.getText());

                    // binding socket to the user provided ipaddress and port number
                    hostSocket = new ServerSocket(port1, 10, ipAddress);

                    // checking if the socket is not closed
                    if (!hostSocket.isClosed()){
                        System.out.println("Server hosted!!");

                        Scene board = new Board(hostSocket.accept(), true).getScene();


                        System.out.println("Waiting");

                        //show user the game board once another player joins the server.
                        if (hostSocket.isBound()) {
                            primaryStage.setScene(board);
                        }
                        
                        
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });


        primaryStage.setScene(mainScene);
        primaryStage.show();
        if (hostSocket != null){
            hostSocket.close();
        }
        if (joinSocket != null) {
            joinSocket.close();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
