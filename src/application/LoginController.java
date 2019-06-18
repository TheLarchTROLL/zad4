package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class LoginController {
	
	public LoginController(){
		
	}
	@FXML
	TextField loginPole = new TextField();
	@FXML
	PasswordField hasloPole = new PasswordField();
	@FXML
	Button loginButton = new Button();
	@FXML
	void initialize() throws IOException
	{	
	}

}
