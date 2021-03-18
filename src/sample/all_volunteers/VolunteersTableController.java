package sample.all_volunteers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.fxml.FXML;
import javafx.stage.StageStyle;
import sample.DBConnector;
import sample.Volunteer;

public class VolunteersTableController implements Initializable {
    @FXML
    private TableView<Volunteer> tbData;

    @FXML
    public TableColumn<Volunteer, String> name;

    @FXML
    public TableColumn<Volunteer, String> surname;

    @FXML
    public TableColumn<Volunteer, Integer> id;

    @FXML
    public TableColumn<Volunteer, String> telephone;

    ObservableList<Volunteer> oblist = FXCollections.observableArrayList();

    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tbData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        name.prefWidthProperty().bind(tbData.widthProperty().multiply(0.27));
        surname.prefWidthProperty().bind(tbData.widthProperty().multiply(0.27));
        id.prefWidthProperty().bind(tbData.widthProperty().multiply(0.2));
        telephone.prefWidthProperty().bind(tbData.widthProperty().multiply(0.25));
        name.setResizable(false);
        surname.setResizable(false);
        id.setResizable(false);
        telephone.setResizable(false);

        try {
            Connection con = DBConnector.getConnection();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM volunteers");

            while(rs.next()) {
                oblist.add(new Volunteer(rs.getString("name"), rs.getString("surname"),
                        rs.getInt("id"),rs.getString("telephone")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        tbData.setItems(oblist);

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

