package com.chee.eartraining;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorldFX extends Application{

	@Override
	public void start(Stage primaryStage) {
		Button btn = new Button();
		btn.setText("Hello World");
		btn.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});
		
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		
	Scene scene = new Scene(root, 300, 250);
	
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void FormCreationInJavaFXExample(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		Text sceneTitle = new Text("Welcome");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label userName = new Label("username: ");
		Label password = new Label("password: ");
		TextField usernameText = new TextField();
		PasswordField passwordText = new PasswordField();
		
		Button submit = new Button("Login");
		HBox hbButton = new HBox(10);
		hbButton.setAlignment(Pos.BOTTOM_RIGHT);
		hbButton.getChildren().add(submit);
		
		final Text actionTarget = new Text();
		
		submit.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				actionTarget.setFill(Color.FIREBRICK);
				actionTarget.setText("Sign in Button Pressed");
			}
		});
		
		grid.add(sceneTitle, 0, 0, 2, 1);
		grid.add(userName, 0, 1);
		grid.add(usernameText, 1, 1);
		grid.add(password, 0, 2);
		grid.add(passwordText, 1, 2);
		grid.add(hbButton, 1, 4);
		grid.add(actionTarget, 1, 6);
		grid.setGridLinesVisible(true);
		
		Scene scene = new Scene(grid, 300, 275);
		
		primaryStage.setTitle("JavaFX Welcome");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
