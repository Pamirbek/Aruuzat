package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public javafx.scene.control.Button show, add, delete;
    public VBox vboxRoot;
    public TextField nameField, surnameField, idField, telephoneField;
    public Text check;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        Connection con;
//        try {
//            con = DBConnector.getConnection();
//
//            Statement st = con.createStatement();
//
//            String query = "CREATE TABLE IF NOT EXISTS volunteers (" +
//                    "name varchar(70), surname varchar(70), id INT ,telephone varchar(15)," +
//                    "PRIMARY KEY (id))";
//            st.executeUpdate(query);
//
//            con.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        show.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root = null;

                stage = (Stage) show.getScene().getWindow();
                try {
                    root = FXMLLoader.load(getClass().getResource("all_volunteers/volunteersTable.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert root != null;
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        add.setOnAction(event -> {

            if (!nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !idField.getText().isEmpty()
                    && !telephoneField.getText().isEmpty()) {
                String idFieldStr = idField.getText();
                if (idFieldStr.matches("[0-9]+")) {

                    Connection con;
                    try {
                        con = DBConnector.getConnection();
//
                        Statement st = con.createStatement();

                        String query = "INSERT INTO volunteers (name, surname, id, telephone)  " + "VALUES ( '" +
                                nameField.getText() + "', '" + surnameField.getText() + "', '" +
                                Integer.parseInt(idField.getText()) + "', '" + telephoneField.getText() + "')";
                        st.executeUpdate(query);

                        con.close();
                        System.out.println("Student successfully added!");
                        check.setText("Student was added");


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else { check.setText("ID must contain digits"); }
            } else { check.setText("One or several forms are empty"); }
        });

        delete.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root = null;

                stage = (Stage) add.getScene().getWindow();
                try {
                    root = FXMLLoader.load(getClass().getResource("delete_volunteer/deleteVolunteer.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert root != null;
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

    }
}
