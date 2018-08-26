package com.chee.eartraining;

import java.util.TreeMap;
import java.util.TreeSet;

import org.jfugue.pattern.Pattern;

import java.util.Map.Entry;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IntervalFX {
	
	private IntervalTrainer trainer;
	private Stage intervalTrainerStage;
	private int intervalKey;
	private FlowPane intervalTrainingPane;
	private Button next;
	private boolean flagCurrentQuestionCorrect;
	private boolean flagCurrentQuestionWrong;
	private final static Border SOLID_BLACK_BORDER = new Border
			(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null));
	private final static Border SOLID_RED_BORDER = new Border
			(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,null,null));
	private final static Border SOLID_GREEN_BORDER = new Border
			(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID,null,null));
	private Label textLabel;
	private ProgressBar progress;
	private double currentProgressValue;
	private Label progressLabel;
	
	public IntervalFX(TreeMap<Integer, Integer> intervalMapping, int intervalKey) {
		trainer = new IntervalTrainer(intervalMapping);
		intervalTrainerStage = new Stage();
		this.intervalKey = intervalKey;
		intervalTrainingPane = new FlowPane();
		intervalTrainingPane.setAlignment(Pos.CENTER);
	}
	
	public void showIntervalFXWindow() {
		BorderPane mainPane = new BorderPane();
		VBox intervalButtonsPane = buildIntervalButtonPane();
		VBox titlePane = buildTitlePane();
		AnchorPane navigationPane = buildNavigationPane();
		
		mainPane.setCenter(intervalTrainingPane);
		mainPane.setTop(titlePane);
		mainPane.setRight(intervalButtonsPane);
		mainPane.setBottom(navigationPane);
		
		Scene scene = new Scene(mainPane, 700, 500);
		
		intervalTrainerStage.setTitle("Interval Trainer");
		intervalTrainerStage.setScene(scene);
		intervalTrainerStage.show();
	}
	
	private VBox buildIntervalButtonPane() {
		VBox intervalButtons = new VBox();		
		for(int i = 0; i < IntervalRandomizer.NUMBER_OF_INTERVALS; i++) {
			ToggleButton intervalButton = new ToggleButton(Integer.toString(i + 1) + " semitones");
			if(trainer.getIntervalsToTrain().containsValue(i + 1)) {
				intervalButton.setSelected(true);
			}
			intervalButton.setOnAction(new EventHandler<ActionEvent> () {

				@Override
				public void handle(ActionEvent e) {
					handleIntervalMapping(e);
				}
				
			});
			intervalButtons.getChildren().add(intervalButton);
		}
		return intervalButtons;
	}
	
	private VBox buildTitlePane() {
		VBox titlePane = new VBox();
		Label titleLabel = new Label("Interval Trainer\n");
		textLabel = new Label("Welcome! Click Start to start training.\n");
		progress = new ProgressBar(currentProgressValue);
		progressLabel = new Label(Integer.toString(trainer.getCorrect()) + "/" + Integer.toString(trainer.getCorrect() + trainer.getWrong()));
		
		titlePane.setAlignment(Pos.CENTER);
		titleLabel.setFont(Font.font("Cambria", 32));
		textLabel.setFont(Font.font("Cambria", 16));
		titlePane.getChildren().addAll(titleLabel, progress, progressLabel, textLabel);
		
		return titlePane;
	}
	
	private AnchorPane buildNavigationPane() {
		AnchorPane navPane = new AnchorPane();
		Button startButton = new Button("Start Training");
		
		startButton.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent e) {
				startButton.setVisible(false);
				navPane.getChildren().removeAll(startButton);
				buildNavigationPaneTrainingStart(navPane);
				textLabel.setText("Please click the correct answer. If you wish to hear again, click the Hear Again button.");
				handleStartTraining();
			}
		});
		startButton.setAlignment(Pos.CENTER);
		navPane.getChildren().add(startButton);
		AnchorPane.setLeftAnchor(startButton, 320.0);
		
		return navPane;
	}
	
	private void buildNavigationPaneTrainingStart(AnchorPane navPane) {
		Button back = new Button("Back");
		Button hearAgain = new Button("Hear Again");
		next = new Button("Next");
		Button finish = new Button("Finish");
		
		next.setDisable(true);
		hearAgain.setOnAction(new EventHandler<ActionEvent> () {
		
			public void handle(ActionEvent e ) {
				hearAgain();
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent e) {
				intervalTrainerStage.close();
			}
		});

		next.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent e) {
				flagCurrentQuestionCorrect = false;
				flagCurrentQuestionWrong = false;
				textLabel.setText("Please answer the question.");
				buildIntervalTrainingPane();
				nextQuestion();
			}
		});
		
		finish.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent e) {
				intervalTrainerStage.close();
				IntervalFinishFX finishWindow = new IntervalFinishFX(trainer.getCorrect(), trainer.getWrong());
				finishWindow.show();
			}
		});
		
		navPane.getChildren().addAll(back, hearAgain, next, finish);
		AnchorPane.setLeftAnchor(back, 0.0);
		AnchorPane.setLeftAnchor(hearAgain, 160.0);
		AnchorPane.setRightAnchor(next, 0.0);
		AnchorPane.setRightAnchor(finish, 160.0);
	}
	
	private void handleIntervalMapping(ActionEvent e) {
		if(!(e.getSource() instanceof ToggleButton)) {
			throw new IllegalArgumentException("Call method only when used on toggle button");
		}
		ToggleButton clicked= (ToggleButton)e.getSource();
		int semitoneSelected = IntervalOptionsFX.semitoneParser(clicked.getText());
		TreeMap<Integer, Integer> intervalMapping = trainer.getIntervalsToTrain();
		if(clicked.isSelected()) {
			intervalMapping.put(intervalKey, semitoneSelected);
			intervalKey++;
		} else { 
			int splitPoint = -1;
			for(int i = 0; i < intervalKey; i++) {
				if(intervalMapping.get(i) == semitoneSelected) {
					intervalMapping.remove(i);
					if(i == intervalKey - 1) {
						splitPoint = i - 1;
					} else {
						splitPoint = i;
					}
				}
			}
			Entry<Integer, Integer> lastMappedValue = intervalMapping.pollLastEntry();
			if(lastMappedValue == null) {
				System.out.println("No more intervals left to train");
				intervalKey--;
			} else {
				intervalMapping.put(splitPoint ,lastMappedValue.getValue());
				intervalKey--;
				
			}
		}
	}
	
	private void handleStartTraining() {
		buildIntervalTrainingPane();
		trainer.startTraining();
	}
	
	private void buildIntervalTrainingPane() {
		if(!(intervalTrainingPane.getChildren().isEmpty())) {
			intervalTrainingPane.getChildren().remove(0, intervalTrainingPane.getChildren().size());
		}
		TreeMap<Integer, Integer> intervalMappings = trainer.getIntervalsToTrain();
		Set<Entry<Integer, Integer>> intervalMappingEntrySet = intervalMappings.entrySet();
		TreeSet<Integer> sortedIntervalSet = new TreeSet<>();
		for(Entry<Integer, Integer> entry : intervalMappingEntrySet) {
			sortedIntervalSet.add(entry.getValue());
		}
		for(Integer interval : sortedIntervalSet) {
			Button answerButton = new Button(Integer.toString(interval) + " semitones");
			answerButton.setBorder(SOLID_BLACK_BORDER);
			answerButton.setOnAction(new EventHandler<ActionEvent> () {
				
				public void handle(ActionEvent e) {
					handleAnswerButton(e);
				}
			});
			intervalTrainingPane.getChildren().add(answerButton);
		}
	}
	
	private void handleAnswerButton(ActionEvent e) {
		if(!(e.getSource() instanceof Button)) {
			throw new IllegalArgumentException("Call method on answer buttons");
		}
		Button answer = (Button)e.getSource();
		int intervalClicked = IntervalOptionsFX.semitoneParser(answer.getText());
		int currentInterval = trainer.getCurrentInterval();
		if(intervalClicked == currentInterval) {
			if(!flagCurrentQuestionCorrect && !flagCurrentQuestionWrong) {
				textLabel.setText("You have answered correctly. CLick the Next button to continue to the next question.\n");
				System.out.println("Correct");
				trainer.correctAnswerSelected();
				next.setDisable(false);
				turnAnswerBoxGreen(answer);
				increaseProgress();
				flagCurrentQuestionCorrect = true;
			} else if(flagCurrentQuestionCorrect){
				textLabel.setText("Your answer is already correct. Click the Next button to continue to the next question.\n");
			} else if(!flagCurrentQuestionCorrect && flagCurrentQuestionWrong) {
				textLabel.setText("You have answered correctly. CLick the Next button to continue to the next question.\n");
				trainer.correctAnswerSelected();
				turnAnswerBoxGreen(answer);
				flagCurrentQuestionCorrect = true;
			}
		} else {
		if(flagCurrentQuestionCorrect) {
			textLabel.setText("Your answer is already correct. Click the Next button to continue to the next question.\n");
		}
		else {
			if(answer.getBorder() == SOLID_RED_BORDER) {
				textLabel.setText("This answer has already been marked as incorrect");
			} else if(!flagCurrentQuestionWrong) {
				System.out.println("Wrong");
				trainer.wrongAnswerSelected();
				turnAnswerBoxRed(answer);
				decreaseProgress();
				flagCurrentQuestionWrong = true;
				} else {
					turnAnswerBoxRed(answer);
				}
			}
		}
	}
	
	private Pattern nextQuestion() {
		flagCurrentQuestionCorrect = false;
		return trainer.next();
	}
	
	private Pattern hearAgain() {
		return trainer.hearAgain();
	}
	
	private void turnAnswerBoxRed(Button answer) {
		answer.setBorder(SOLID_RED_BORDER);
	}
	
	private void turnAnswerBoxGreen(Button answer) {
		answer.setBorder(SOLID_GREEN_BORDER);
	}
	
	private void increaseProgress() {
		progress.setProgress(currentProgressValue += 0.05);
		progressLabel.setText(Integer.toString(trainer.getCorrect()) + "/" + Integer.toString(trainer.getCorrect() + trainer.getWrong()));
	}
	
	private void decreaseProgress() {
		progress.setProgress(currentProgressValue -= 0.05);
		progressLabel.setText(Integer.toString(trainer.getCorrect()) + "/" + Integer.toString(trainer.getCorrect() + trainer.getWrong()));
	}
	
	public static void main(String[] args) {
		Application.launch(EarTrainer.class);
	}

}
