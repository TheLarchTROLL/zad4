package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;



public class OknoController {
	LoginController loginController = new LoginController();
	RejestracjaController rejestracjaKontroller = new RejestracjaController();
	WidokAdministratoraController widokAdministratoraController = new WidokAdministratoraController();
	WidokUzytkownikaController widokUzytkownikaController = new WidokUzytkownikaController();
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost:3306/daneuzytkownikow?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String USER = "root";
	String PASS = "superhaslo1337";
	@FXML
	StackPane oknoTresci = new StackPane();
	@FXML
	Button zarejestrujButton = new Button();
	@FXML
	Button zalogujButton = new Button();
	@FXML
	void zarejestrujButtonAction(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("Rejestracja.fxml"));
		this.oknoTresci.getChildren().set(0, loader.load());
		rejestracjaKontroller = loader.getController();
		loader.setController(rejestracjaKontroller);
		this.rejestracjaKontroller.rejestracjaButton.setOnAction((actionEvent) -> {
			this.rejestracja();
		});
	}
	void logowanie() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			String login = this.loginController.loginPole.getText();
			sql = "SELECT login, haslo, uprawnienia FROM users WHERE login='" + login + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String haslo = rs.getString("haslo");
				if (haslo.equals(this.loginController.hasloPole.getText())) {
					String uprawnienia = rs.getString("uprawnienia");
					if (uprawnienia.equals("root")) {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(this.getClass().getResource("WidokAdministratora.fxml"));
						this.oknoTresci.getChildren().set(0, loader.load());
						widokAdministratoraController = loader.getController();
						loader.setController(widokAdministratoraController);
						widokAdministratoraController.loginLabel.setText(login);
					} else {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(this.getClass().getResource("WidokUzytkownika.fxml"));
						this.oknoTresci.getChildren().set(0, loader.load());
						widokUzytkownikaController = loader.getController();
						loader.setController(widokUzytkownikaController);
						widokUzytkownikaController.loginLabel.setText(login);
					}
				} else {
					System.out.println("Bledne haslo");
				}
			} else {
				System.out.println("Bledny login");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("zalogowano");
	}
	void rejestracja() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT COUNT(*) AS COUNT FROM users";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int index = rs.getInt("COUNT") + 1;
			String imie = this.rejestracjaKontroller.imiePole.getText();
			String nazwisko = this.rejestracjaKontroller.nazwiskoPole.getText();
			String login = this.rejestracjaKontroller.loginPole.getText();
			String haslo = this.rejestracjaKontroller.hasloPole1.getText();
			String haslo2 = this.rejestracjaKontroller.hasloPole2.getText();
			String email = this.rejestracjaKontroller.emailPole.getText();
			if (haslo.equals(haslo2)) {
				sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(sql);
				preparedStmt.setInt(1, index);
				preparedStmt.setString(2, imie);
				preparedStmt.setString(3, nazwisko);
				preparedStmt.setString(4, login);
				preparedStmt.setString(5, haslo);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, "user");
				preparedStmt.execute();
				this.rejestracjaKontroller.poleInformacji.setText("zarejestrowano");
			} else {
				this.rejestracjaKontroller.poleInformacji.setText("Hasla sie nie zgadzaj¹!");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	@FXML
	void zalogujButtonAction(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("Login.fxml"));
		this.oknoTresci.getChildren().set(0, loader.load());
		loginController = loader.getController();
		loader.setController(loginController);
		this.loginController.loginButton.setOnAction((actionEvent) -> {
			this.logowanie();
		});
	}
	public OknoController() {
	}
	@FXML
	void initialize() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("Login.fxml"));
			this.oknoTresci.getChildren().add(loader.load());
			this.loginController = loader.getController();
			loader.setController(loginController);
			this.loginController.loginButton.setOnAction((actionEvent) -> {
				this.logowanie();
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
