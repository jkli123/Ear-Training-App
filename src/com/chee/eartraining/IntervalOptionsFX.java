package com.chee.eartraining;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IntervalOptionsFX{
	
	private Stage optionStage;
	private TreeMap<Integer, Integer> intervalMapping;
	private int intervalKey;
	
	public IntervalOptionsFX() {
		optionStage = new Stage();
		intervalMapping = new TreeMap<>();
		optionStage.setTitle("Interval Trainer Options");
		intervalKey = 0;
	}

	public void showIntervalOptionsWindow() {
		BorderPane mainPane = new BorderPane();
		FlowPane intervalButtonPane = buildIntervalButtonPane();
		GridPane optionsPane = buildOptionsPane();
		AnchorPane navigationPane = buildNavigationPane();
		VBox titleBox = buildTopPane();
		
		mainPane.setTop(titleBox);
		mainPane.setCenter(intervalButtonPane);
		mainPane.setRight(optionsPane);
		mainPane.setBottom(navigationPane);
		
		Scene scene = new Scene(mainPane, 700, 500);
		
		optionStage.setScene(scene);
		optionStage.show();
	}
	
	private FlowPane buildIntervalButtonPane() {
		ArrayList<ToggleButton> buttonList = new ArrayList<>(14);
		FlowPane intervalButtonPane = new FlowPane();
		
		for(int i = 0; i < IntervalRandomizer.NUMBER_OF_INTERVALS; i++) {
			ToggleButton button = new ToggleButton(Integer.toString(i + 1) + " semitones");
			button.setOnAction(new EventHandler<ActionEvent> () {

				@Override
				public void handle(ActionEvent e) {
					handleIntervalMapping(e);
				}
				
			});
			buttonList.add(button);
		}
		intervalButtonPane.setAlignment(Pos.CENTER);
		intervalButtonPane.getChildren().addAll(buttonList);
		return intervalButtonPane;
	}
	
	private GridPane buildOptionsPane() {
		GridPane optionsPane = new GridPane();
		return optionsPane;
	}
	
	private AnchorPane buildNavigationPane() {
		AnchorPane navigationPane = new AnchorPane();
		Button back = new Button("Back");
		Button start = new Button("Start");
		start.setOnAction(new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent e) {
				System.out.println(intervalMapping);
				IntervalFX trainerWindow = new IntervalFX(intervalMapping, intervalKey);
				trainerWindow.showIntervalFXWindow();
				optionStage.close();
			}
			
		});
		
		back.setOnAction(new EventHandler<ActionEvent> () {
			
			@Override
			public void handle(ActionEvent e) {
				optionStage.close();
			}
		});
		navigationPane.getChildren().addAll(back, start);
		AnchorPane.setLeftAnchor(back, 0.0);
		AnchorPane.setRightAnchor(start, 0.0);
		return navigationPane;
	}
	
	private VBox buildTopPane() {
		VBox topPane = new VBox();
		Label titleLabel = new Label("Interval Trainer Options");
		Label instructionLabel = new Label("Please select which intervals you would like to train.\n"
				+ "Once you have selected the intervals to train, click the start button to start training.\n"
				+ "If you wish to go back to the main menu, click the back button.\n");
		
		titleLabel.setFont(Font.font("Cambria", 32));
		instructionLabel.setFont(Font.font("Cambria", 16));
		instructionLabel.setWrapText(true);
		instructionLabel.setAlignment(Pos.CENTER);
		topPane.setAlignment(Pos.CENTER);
		topPane.getChildren().addAll(titleLabel, instructionLabel);
		return topPane;
	}
	
	private void handleIntervalMapping(ActionEvent e) {
		if(!(e.getSource() instanceof ToggleButton)) {
			throw new IllegalArgumentException("Call method only when used on toggle button");
		}
		ToggleButton clicked = (ToggleButton)e.getSource();
		int semitoneSelected = semitoneParser(clicked.getText());
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
				intervalKey--;
			} else {
				intervalMapping.put(splitPoint ,lastMappedValue.getValue());
				intervalKey--;
				
			}
		}
	}
	
	public static int semitoneParser(String text) {
		String[] splitText = text.split(" ");
		return Integer.parseInt(splitText[0]);
	}
	
	public static void main(String[] args) {
		Application.launch(EarTrainer.class, args);
	}
}
