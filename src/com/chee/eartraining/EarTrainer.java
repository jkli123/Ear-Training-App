package com.chee.eartraining;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EarTrainer extends Application{

	public void start(Stage mainStage) {
		GridPane mainGridPane = new GridPane();
		mainGridPane.setAlignment(Pos.CENTER);
		
		Button btn = new Button("Start Interval Options");
		mainGridPane.add(btn, 0, 0);
		
		btn.setOnAction(new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent arg0) {
				showSomething();
			}
			
		});
		Scene scene = new Scene(mainGridPane, 500, 500);
		
		mainStage.setTitle("Entry Window");
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	public void showSomething() {
		IntervalOptionsFX iop = new IntervalOptionsFX();
		iop.showIntervalOptionsWindow();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
