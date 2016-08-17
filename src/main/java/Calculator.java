import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.HashMap;
import java.io.*;

// Главное javafx приложение
public class Calculator extends Application {
	// Параметры калькулятора
	protected String appName = "Javaculator";
	protected int appWidth = 255;
	protected int appHeight = 190;
	// Поле ввода
	protected TextField calcInput;	
	// Два массива для храннения ссылок на кнопки (для цифровых кнопок и для кнопок операций)
	protected HashMap<String, Button> inputButtons;
	protected HashMap<String, Button> operationButtons;

	public void start(Stage stage) {
		// Создаём основной слой приложения
		GridPane rootPane = createRootPane();
		// Добавляем элементы в шапку приложения
		addHeader(rootPane);
		// Создаём слой с кнопками цифр
		FlowPane inputButtonsPane = createInputButtonsPane(rootPane);
		// Добавляем слой с цифрами в основной слой
		rootPane.add(inputButtonsPane, 1, 2);
		// Создаём слой с кнопками операций
		FlowPane operationButtonsPane = createOperationButtonsPane(rootPane);
		// Добавляем слой с операциями в основной слой
		rootPane.add(operationButtonsPane, 0, 2);
		
		// Устанавливаем основной слой сцены и размер окна
		Scene scene = new Scene(rootPane, appWidth, appHeight);
		// Подключаем css		
		scene.getStylesheets().add("css/style.css");
		// Заголовок приложения
		stage.setTitle(appName);
		// Запрещаем изменение размера окна
		stage.setResizable(false);	
		// Устанавливаем сцену окна	
		stage.setScene(scene);
		// Отображаем окно
		stage.show();
	}
	// Для консоли
	static public void main(String[] args) {
		launch(args);
	}
	
	
	/**
	 * App main pane
	 * Создаёт главный, корневой слой приложения
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
	 * Добавление некоторых элементов в шапку
	 */
	public void addHeader( GridPane pane ) {
		// Текст в шапке
		Text appHead = new Text(appName);
		pane.add(appHead, 0, 0, 2, 1);
		// Поле ввода
		calcInput = new TextField();
		// Его ширина
		calcInput.setPrefWidth(530);
		// Тобавляем в главный слой
		pane.add(calcInput, 0, 1, 2, 1);
	}
	
	/**
	 * Section of buttons with input values
	 * Создаёт кнопки с цифрами. Помещает их в отдельный слой
	 */
	public <T extends Pane, V extends Pane> T createInputButtonsPane( V rootPane ) {
		String[] buttons = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "."};
		return (T)createButtonsPane(buttons);
	}
	
	/**
	 * Section of buttons with operations for input values
	 * Создаёт кнопки с операциями. Помещает их в отдельный слой
	 */
	public <T extends Pane, V extends Pane> T createOperationButtonsPane( V rootPane ) {
		String[] buttons = {"+", "-", "*", "/", "%", "="};
		return (T)createButtonsPane(buttons);
	}
	
	/**
	 * Общая функция для создания отдельного слоя и заполнения его кнопками
	 */
	public <T extends Pane> T createButtonsPane( String[] buttons ) {
		// Новый слой
		FlowPane pane = new FlowPane();
		pane.setVgap(5);
		pane.setHgap(5);
		// Создаём кнопки в цикле, и добавляем обработчик событий.
		for ( final String buttonText : buttons ) {
			Button button = new Button(buttonText);
			button.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					// При клике просто добавляем значение в поле ввода
					String currValue = calcInput.getText();
					calcInput.setText(currValue + buttonText);
				}
			});
			button.setPrefWidth(40);
			// Добавим css селектор
			button.getStyleClass().add("btn");
			pane.getChildren().add(button);
		}
		return (T)pane;
	}
	
	// TODO Implemet
	protected String findGroups( String input ) {
		// Use regexp "/(\((?>[^)(]+|(?R))+\))/gx"
		return "";
	}
	
}
