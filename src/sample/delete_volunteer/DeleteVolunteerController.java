package sample.delete_volunteer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.DBConnector;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteVolunteerController implements Initializable {
    public Button delete, back;
    public TextField idField;
    public Text check;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        delete.setOnAction(event -> {

            Connection con;
            try {
                con = DBConnector.getConnection();

                Statement st = con.createStatement();

                String query = "DELETE FROM volunteers " +
                        "WHERE id = " + Integer.parseInt(idField.getText());
                st.executeUpdate(query);


                con.close();
                System.out.println("Volunteer was deleted successfully!");
                check.setText("Volunteer was deleted");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        back.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root = null;

                stage = (Stage) back.getScene().getWindow();
                try {
                    root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
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
