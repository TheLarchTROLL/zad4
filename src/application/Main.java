package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("Okno.fxml"));
		StackPane stackPane = loader.load();
		
		OknoController controller = loader.getController();
		loader.setController(controller);
		Scene scene = new Scene(stackPane);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("PSW Lab4");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
