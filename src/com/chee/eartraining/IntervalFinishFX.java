package com.chee.eartraining;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IntervalFinishFX {

	private Stage finishStage;
	private int correct;
	private int wrong;
	
	public IntervalFinishFX(int correct, int wrong) {
		finishStage = new Stage();
		finishStage.setTitle("Interval Trainer Results");
		this.correct = correct;
		this.wrong = wrong;
	}
	
	public IntervalFinishFX() {
		
	}
	
	public void show() {
		BorderPane mainPane = new BorderPane();
		VBox scorePane = buildScorePane();
		AnchorPane navigationPane = buildNavigationPane();
		HBox titlePane = buildTitlePane();
		
		mainPane.setCenter(scorePane);
		mainPane.setBottom(navigationPane);
		mainPane.setTop(titlePane);
		
		Scene scene = new Scene(mainPane, 700, 500);
		
		finishStage.setScene(scene);
		finishStage.show();
	}
	
	public VBox buildScorePane() {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		double score = calculateScore();
		Label scoreLabel = new Label();
		scoreLabel.setFont(Font.font("Cambria", 32));
		String resultText = "Score: " + String.format("%.2f", score) + "%";
		scoreLabel.setText(resultText);
		Label correctWrongLabel = new Label();
		correctWrongLabel.setText("Correct: " + this.correct + "\n" + "Wrong: " + this.wrong);
		correctWrongLabel.setFont(Font.font("Cambria", 16));
		box.getChildren().addAll(scoreLabel, correctWrongLabel);
		return box;
	}
	
	public AnchorPane buildNavigationPane() {
		AnchorPane navPane = new AnchorPane();
		Button close = new Button("Close");
		navPane.getChildren().add(close);
		AnchorPane.setLeftAnchor(close, 0.0);
		close.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent e) {
				finishStage.close();
			}
		});
		return navPane;
	}
	
	public HBox buildTitlePane() {
		HBox box = new HBox();
		Label resultsText = new Label("Results Page");
		resultsText.setFont(Font.font("Cambria", 50));
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(resultsText);
		return box;
	}
	
	public double calculateScore() {
		double result = (double)correct / ((double)wrong + (double)correct);
		result *= 100.0;
		return result;
	}
	
	public static void main(String[] args) {
		Application.launch(EarTrainer.class);
	}
}
