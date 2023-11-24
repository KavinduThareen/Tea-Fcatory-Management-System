package lk.ijse.teaFactory.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lk.ijse.teaFactory.model.CustomerModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dashboard1Controller {

    @FXML
    private Label timeTxt;

    @FXML
    private Label lblordersTxt;

    @FXML
    private Label lblCus;

    @FXML
    public void initialize() throws SQLException {
        // Call the method to start updating the time
        generateRealTime();
        generateOrderCount();
        generateCustemereCount();
    }

    private void generateRealTime() {
        timeTxt.setText(LocalDate.now().toString());

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            timeTxt.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


   public void generateOrderCount(){
        var a = new CustomerOrdersController();
        lblordersTxt.setText(String.valueOf(a.count));
   }

    public void generateCustemereCount() throws SQLException {
        CustomerModel customerModel = new CustomerModel();
        int a = customerModel.cusCount();

        lblCus.setText(String.valueOf(a));

    }


}
