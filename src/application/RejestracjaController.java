package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class RejestracjaController {
	public RejestracjaController(){
		
	}
	@FXML
	TextField imiePole = new TextField();
	@FXML
	TextField nazwiskoPole = new TextField();
	@FXML
	TextField loginPole = new TextField();
	@FXML
	PasswordField hasloPole1 = new PasswordField();
	@FXML
	PasswordField hasloPole2 = new PasswordField();
	@FXML
	TextField emailPole= new TextField();
	@FXML
	Button rejestracjaButton = new Button();
	@FXML
	Label poleInformacji = new Label();
	@FXML
	void initialize() throws IOException
	{	
	}
}
