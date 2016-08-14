import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.*;

// TODO create maven project
public class Calculator extends Application {
	
	protected String appName = "Javaculator";
	protected int appWidth = 255;
	protected int appHeight = 190;
	protected TextField calcInput;	

	public void start(Stage stage) {
		
		GridPane rootPane = createRootPane();
		addHeader(rootPane);
		
		FlowPane inputButtonsPane = createInputButtonsPane(rootPane);
		FlowPane operationButtonsPane = createOperationButtonsPane(rootPane);
		rootPane.add(operationButtonsPane, 0, 2);
		rootPane.add(inputButtonsPane, 1, 2);
		
		Scene scene = new Scene(rootPane, appWidth, appHeight);
		
		stage.setTitle(appName);
		stage.setResizable(false);		
		stage.setScene(scene);
		
		stage.show();
	}
	
	static public void main(String[] args) {
		launch(args);
	}
	
	
	/**
	 * App main pane
	 */
	public <T extends Pane> T createRootPane() {
		GridPane rootPane = new GridPane();
		rootPane.setAlignment(Pos.TOP_CENTER);
		rootPane.setHgap(20);
		rootPane.setVgap(5);
		rootPane.setPadding( new Insets( 10, 10, 10, 10));
		rootPane.getColumnConstraints().add( new ColumnConstraints(85));
		
		rootPane.setGridLinesVisible(false);
		return (T)rootPane;
	}
	
	/**
	 * Add header elements
	 */
	public void addHeader( GridPane pane ) {
		Text appHead = new Text(appName);
		pane.add(appHead, 0, 0, 2, 1);
		calcInput = new TextField();
		calcInput.setPrefWidth(530);
		pane.add(calcInput, 0, 1, 2, 1);
	}
	
	/**
	 * Section of buttons with input values
	 */
	public <T extends Pane, V extends Pane> T createInputButtonsPane( V rootPane ) {
		String[] buttons = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "."};
		return (T)createButtonsPane(buttons);
	}
	
	/**
	 * Section of buttons with operations for input values
	 */
	public <T extends Pane, V extends Pane> T createOperationButtonsPane( V rootPane ) {
		String[] buttons = {"+", "-", "*", "/", "%", "="};
		return (T)createButtonsPane(buttons);
	}
	
	public <T extends Pane> T createButtonsPane( String[] buttons ) {
		FlowPane pane = new FlowPane();
		pane.setVgap(5);
		pane.setHgap(5);
		for ( String buttonText : buttons ) {
			Button button = new Button(buttonText);
			button.setPrefWidth(40);
			pane.getChildren().add(button);
		}
		return (T)pane;
	}
	
}
