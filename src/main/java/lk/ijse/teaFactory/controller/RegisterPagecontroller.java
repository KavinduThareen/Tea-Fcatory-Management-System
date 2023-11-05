package lk.ijse.teaFactory.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.teaFactory.dto.registerDto;
import lk.ijse.teaFactory.dto.tm.registerTm;
import lk.ijse.teaFactory.model.registerModel;

import java.sql.SQLException;
import java.util.List;


public class RegisterPagecontroller {

    @FXML
    private TextField confirmPasswordTxt;

    @FXML
    private TextField contacTxt;

    @FXML
    private JFXButton createAccountBtn;

    @FXML
    private TextField passwordTxt;

    @FXML
    private TextField useridTxt;

    @FXML
    private TextField usernameTxt;


    @FXML
    void createAccountBtnOnAction(ActionEvent event) {

        String userid = useridTxt.getText();
        String username = usernameTxt.getText();
        String contac = contacTxt.getText();
        String password = passwordTxt.getText();


            var dto = new registerDto(userid, username, contac, password);

            var model = new registerModel();

            try {
                boolean isSaved = model.registerUser(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "You are registerd!").show();
                    clearFields();
                }


            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }


    void clearFields() {
        usernameTxt.setText("");
        useridTxt.setText("");
        contacTxt.setText("");
        passwordTxt.setText("");
        confirmPasswordTxt.setText("");
    }

}