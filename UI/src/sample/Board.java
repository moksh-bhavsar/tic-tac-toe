package sample;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class Board extends Application {
	Socket socket;
	Boolean isHost = false;
	
	public Board(Socket socket, Boolean isHost) {
		this.socket = socket;
		this.isHost =  isHost;
	}

	
	public String curPlayer = "x";
	public int numMoves = 0;//end game if this reaches 9
	
	Button button00 = new Button(" ");
	Button button01 = new Button(" ");
	Button button02 = new Button(" ");
	
	Button button10 = new Button(" ");
	Button button11 = new Button(" ");
	Button button12 = new Button(" ");
	
	Button button20 = new Button(" ");
	Button button21 = new Button(" ");
	Button button22 = new Button(" ");
	
	Label status = new Label("Current move: x");

	Scene buttons;
	
	
	int[][] grid = new int[3][3];
	// This will be used to store the values of the game grid
	// a zero will represent a blank cell, a 1 for "x", and a 2 for "o"
	//
	// grid[row][column]
	//
	//      0   1   2 
	//   0  x | x | o
	//     -----------
	//   1  o | x | o
	//     -----------
	//   2  x | o | x
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("sample.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		

		
		
		/*
		if (isHost) {
			String comand = null;// declare variable to store current command

			InputStream inStream;
			try {
				inStream = socket.getInputStream();
				// declare an InputStream to receive client input
				InputStreamReader reader = new InputStreamReader(inStream);// declare an InputStreamReader to read client input
				BufferedReader in = new BufferedReader(reader);// declare BufferedReader to handle input
				String line = null;// declare a string to store the current line
				while ((line = in.readLine()) != null) {// loop while there is data to process
					System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inSockData();
		}else{
			PrintWriter output2;
			try {
				output2 = new PrintWriter(socket.getOutputStream());
				output2.println("testing sockets by sending this line to the host");
				output2.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outSockData(1,2);
		}
		*/	
		
		
		HBox hbox0 = new HBox(button00,button01,button02);
		HBox hbox1 = new HBox(button10,button11,button12);
		HBox hbox2 = new HBox(button20,button21,button22);
		VBox vbox = new VBox(hbox0,hbox1,hbox2,status);
		
		buttons = new Scene(vbox,vbox.getMaxWidth(),vbox.getMaxHeight());

		button00.setOnAction(actionEvent -> {
			button00.setDisable(true);
			button00.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[0][0] = 1;
			} else {
				grid[0][0] = 2;
			}
			
			
			disableGrid();
			outSockData(0,0);
			nextSteps(true);
			

		});
		
		button01.setOnAction(actionEvent -> {
			button01.setDisable(true);
			button01.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[0][1] = 1;
			} else {
				grid[0][1] = 2;
			}
			
			
			disableGrid();
			outSockData(0,1);
			nextSteps(true);
		});
		
		button02.setOnAction(actionEvent -> {
			button02.setDisable(true);
			button02.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[0][2] = 1;
			} else {
				grid[0][2] = 2;
			}
			
			
			disableGrid();
			outSockData(0,2);
			nextSteps(true);
		});
		
		button10.setOnAction(actionEvent -> {
			button10.setDisable(true);
			button10.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[1][0] = 1;
			} else {
				grid[1][0] = 2;
			}
			
			
			disableGrid();
			outSockData(1,0);
			nextSteps(true);
		});
		
		button11.setOnAction(actionEvent -> {
			button11.setDisable(true);
			button11.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[1][1] = 1;
			} else {
				grid[1][1] = 2;
			}
			
			
			disableGrid();
			outSockData(1,1);
			nextSteps(true);
		});
		
		button12.setOnAction(actionEvent -> {
			button12.setDisable(true);
			button12.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[1][2] = 1;
			} else {
				grid[1][2] = 2;
			}
			
			
			disableGrid();
			outSockData(1,2);
			nextSteps(true);
		});
		
		button20.setOnAction(actionEvent -> {
			button20.setDisable(true);
			button20.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[2][0] = 1;
			} else {
				grid[2][0] = 2;
			}
			
			
			disableGrid();
			outSockData(2,0);
			nextSteps(true);
		});
		
		button21.setOnAction(actionEvent -> {
			button21.setDisable(true);
			button21.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[2][1] = 1;
			} else {
				grid[2][1] = 2;
			}
			
			
			disableGrid();
			outSockData(2,1);
			nextSteps(true);
		});
		
		button22.setOnAction(actionEvent -> {
			button22.setDisable(true);
			button22.setText(curPlayer);
			
			if (curPlayer == "x") {
				grid[2][2] = 1;
			} else {
				grid[2][2] = 2;
			}
			
			
			disableGrid();
			outSockData(2,2);
			nextSteps(true);
		});

		

		if (isHost) {
			disableGrid();
			status.setText("waiting on player");
			inSockData();
			String inCords[] = inSockData().split(",");
			System.out.println("X: " + inCords[1]);
			updateBoard(inCords[0],inCords[1]);

		}else {
			enableBlankGrid();
			status.setText("Your move");
			outSockData(-1,-1);
		}
		
		/*while(true) {
			status.setText("waiting on player");
			String inCords[] = inSockData().split(",");
			System.out.println("X: " + inCords[1]);
			updateBoard(inCords[0],inCords[1]);
		}*/
		
		
		
		
	}
	
	
	
	
	

	public Scene getScene(){
		Stage primaryStage = new Stage();
		start(primaryStage);
		return buttons;
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	public void nextSteps(boolean getNext) {
		//this function will process what happens after a move is made
		//it is responsible for ending the game when needed
		//it will also update the UI to display a message
		//this message will say whose turn it is or if the game is over
		System.out.println("Num moves: " + numMoves + " +1");
		numMoves++;
		
		//change curPlayer
		if(curPlayer == "x") {
			curPlayer = "o";
		} else {
			curPlayer = "x";
		}
		
		if (checkWinner()) {
			if(curPlayer == "x") {
				status.setText("o wins!");
			} else {
				status.setText("x wins!");
			}
			disableGrid();
		} else if (numMoves > 8){
			status.setText("draw");
			disableGrid();
		} else {
			status.setText("Current move: " + curPlayer);
		}
		
		if(getNext) {
			getAction();
		}

	}
	
	
	public boolean checkWinner() {
		for (int i=1; i <= 2; i++) {
			if (grid[0][0] == i && grid[0][1] == i && grid[0][2] == i) {
				return true;
			}
			if (grid[1][0] == i && grid[1][1] == i && grid[1][2] == i) {
				return true;
			}
			if (grid[2][0] == i && grid[2][1] == i && grid[2][2] == i) {
				return true;
			}
			if (grid[0][0] == i && grid[1][0] == i && grid[2][0] == i) {
				return true;
			}
			if (grid[0][1] == i && grid[1][1] == i && grid[2][1] == i) {
				return true;
			}
			if (grid[0][2] == i && grid[1][2] == i && grid[2][2] == i) {
				return true;
			}
			if (grid[0][0] == i && grid[1][1] == i && grid[2][2] == i) {
				return true;
			}
			if (grid[0][2] == i && grid[1][1] == i && grid[2][0] == i) {
				return true;
			}
		}
		return false;
	}
	
	public void disableGrid() {
		button00.setDisable(true);
		button01.setDisable(true);
		button02.setDisable(true);
		button10.setDisable(true);
		button11.setDisable(true);
		button12.setDisable(true);
		button20.setDisable(true);
		button21.setDisable(true);
		button22.setDisable(true);
	}
	
	public void enableGrid() {
		button00.setDisable(false);
		button01.setDisable(false);
		button02.setDisable(false);
		button10.setDisable(false);
		button11.setDisable(false);
		button12.setDisable(false);
		button20.setDisable(false);
		button21.setDisable(false);
		button22.setDisable(false);
	}
	
	public void enableBlankGrid() {
		disableGrid();
		if(button00.getText().length()>0) {
			button00.setDisable(false);
		}
		if(button01.getText().length()>0) {
			button01.setDisable(false);
		}
		if(button02.getText().length()>0) {
			button02.setDisable(false);
		}
		if(button10.getText().length()>0) {
			button10.setDisable(false);
		}
		if(button11.getText().length()>0) {
			button11.setDisable(false);
		}
		if(button12.getText().length()>0) {
			button12.setDisable(false);
		}
		if(button20.getText().length()>0) {
			button20.setDisable(false);
		}
		if(button21.getText().length()>0) {
			button21.setDisable(false);
		}
		if(button22.getText().length()>0) {
			button22.setDisable(false);
		}
	}
	
	public void getAction() {
		System.out.println("in getGrid");
		status.setText("waiting on opp.");
		String inCords[] = inSockData().split(",");
		System.out.println("X: " + inCords[1]);
		updateBoard(inCords[0],inCords[1]);
		enableBlankGrid();
	}
	
	public String inSockData(){
		InputStream inStream;
		try {
			inStream = socket.getInputStream();
			// declare an InputStream to receive client input
			InputStreamReader reader = new InputStreamReader(inStream);// declare an InputStreamReader to read client input
			BufferedReader in = new BufferedReader(reader);// declare BufferedReader to handle input

			String line = null;// declare a string to store the current line
			while ((line = in.readLine()) != null) {// loop while there is data to process
				System.out.println(line);
				//grid = ast.literal_eval(line);
				return line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return "";
	}
	
	public void sendGrid() {
		//outSockData(grid);
	}
	
	public void outSockData(int x, int y) {
		try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(x + "," + y);
            writer.flush();

			System.out.println("test data sent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateBoard(String x, String y) {
		if(x.equals("0")) {
			if (y.equals("0")) {
				button00.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[0][0] = 1;
				} else {
					grid[0][0] = 2;
				}
				
				button00.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
			if (y.equals("1")) {
				button01.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[0][1] = 1;
				} else {
					grid[0][1] = 2;
				}
				
				button01.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
			if (y.equals("2")) {
				button02.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[0][2] = 1;
				} else {
					grid[0][2] = 2;
				}
				
				button02.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
		}
		
		if(x.equals("1")) {
			if (y.equals("0")) {
				button10.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[1][0] = 1;
				} else {
					grid[1][0] = 2;
				}
				
				button10.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
			if (y.equals("1")) {
				button11.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[1][1] = 1;
				} else {
					grid[1][1] = 2;
				}
				
				button11.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
			if (y.equals("2")) {
				button12.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[1][2] = 1;
				} else {
					grid[1][2] = 2;
				}
				
				button12.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
		}
		
		if(x.equals("0")) {
			if (y.equals("0")) {
				button00.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[0][0] = 1;
				} else {
					grid[0][0] = 2;
				}
				
				button00.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
			if (y.equals("2")) {
				button21.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[2][1] = 1;
				} else {
					grid[2][1] = 2;
				}
				
				button21.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
			if (y.equals("2")) {
				button22.setText(curPlayer);
				
				if (curPlayer == "x") {
					grid[2][2] = 1;
				} else {
					grid[2][2] = 2;
				}
				
				button22.setDisable(true);
				
				nextSteps(false);
				
				enableBlankGrid();
				status.setText("Your move");
			}
		}
		
	}
}




