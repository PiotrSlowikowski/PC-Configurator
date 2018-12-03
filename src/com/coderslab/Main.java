package com.coderslab;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    ComboBox<String> comboBox;
    private Label labelWarning;


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Get connection to database
        Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hardware?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
        // Create statement
        Statement myStmt = myConnection.createStatement();
        // Execute SQL query

        Stage window = primaryStage;
        window.setTitle("PC Configurator");


        //Title label
        Label labelTitle = new Label("PC Hardware Configurator");
        labelTitle.setMinSize(1000, 70);
        labelTitle.setPadding(new Insets(30, 20, 0, 20));
        labelTitle.setAlignment(Pos.TOP_CENTER);
        labelTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //Add pic
        Image image = new Image("File:PCpic.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitHeight(140);
        iv1.setPreserveRatio(true);

        //Choose label
        Label labelChoice = new Label("Choose hardware components from the box below");
        labelChoice.setMinSize(1000, 40);
        labelChoice.setAlignment(Pos.TOP_CENTER);
        labelChoice.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        //Chosen components part label
        Label labelMOBO = new Label("Motherboard: ");
        labelMOBO.setMinSize(100, 15);
        labelMOBO.setAlignment(Pos.CENTER);
        labelMOBO.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Label labelCPU = new Label("Processor: ");
        labelCPU.setMinSize(100, 30);
        labelCPU.setAlignment(Pos.CENTER);
        labelCPU.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Label labelGPU = new Label("Graphics Card: ");
        labelGPU.setMinSize(100, 30);
        labelGPU.setAlignment(Pos.CENTER);
        labelGPU.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Label labelRAM = new Label("RAM: ");
        labelRAM.setMinSize(100, 30);
        labelRAM.setAlignment(Pos.CENTER);
        labelRAM.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Label labelHardDrive = new Label("Solid State Drive: ");
        labelHardDrive.setMinSize(100, 30);
        labelHardDrive.setAlignment(Pos.CENTER);
        labelHardDrive.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Label labelPSU = new Label("Power Supply: ");
        labelPSU.setMinSize(100, 30);
        labelPSU.setAlignment(Pos.CENTER);
        labelPSU.setFont(Font.font("Verdana", FontWeight.BOLD, 15));


        //Fill component labels
        Label labelMOBOFill = new Label("");
        labelMOBOFill.setMinSize(250, 30);
        labelMOBOFill.setPadding(new Insets(0, 0, 0, 20));
        labelMOBOFill.setAlignment(Pos.CENTER);
        labelMOBOFill.setFont((Font.font("Verdana", FontWeight.BOLD, 13)));

        Label labelCPUFill = new Label("");
        labelCPUFill.setMinSize(250, 30);
        labelCPUFill.setPadding(new Insets(0, 0, 0, 20));
        labelCPUFill.setAlignment(Pos.CENTER);
        labelCPUFill.setFont((Font.font("Verdana", FontWeight.BOLD, 13)));

        Label labelGPUFill = new Label("");
        labelGPUFill.setMinSize(250, 30);
        labelGPUFill.setPadding(new Insets(0, 0, 0, 20));
        labelGPUFill.setAlignment(Pos.CENTER);
        labelGPUFill.setFont((Font.font("Verdana", FontWeight.BOLD, 13)));

        Label labelRAMFill = new Label("");
        labelRAMFill.setMinSize(250, 30);
        labelRAMFill.setPadding(new Insets(0, 0, 0, 20));
        labelRAMFill.setAlignment(Pos.CENTER);
        labelRAMFill.setFont((Font.font("Verdana", FontWeight.BOLD, 13)));

        Label labelHardDriveFill = new Label("");
        labelHardDriveFill.setMinSize(250, 30);
        labelHardDriveFill.setPadding(new Insets(0, 0, 0, 20));
        labelHardDriveFill.setAlignment(Pos.CENTER);
        labelHardDriveFill.setFont((Font.font("Verdana", FontWeight.BOLD, 13)));

        Label labelPSUFill = new Label("");
        labelPSUFill.setMinSize(250, 30);
        labelPSUFill.setPadding(new Insets(0, 0, 0, 20));
        labelPSUFill.setAlignment(Pos.CENTER);
        labelPSUFill.setFont((Font.font("Verdana", FontWeight.BOLD, 13)));

        //Quantityy
        Label labelMOBOQuantity = new Label("");
        labelMOBOQuantity.setPadding(new Insets(0, 10, 0, 0));
        labelMOBOQuantity.setMinSize(230, 30);
        labelMOBOQuantity.setAlignment(Pos.BASELINE_RIGHT);
        labelMOBOQuantity.getStyleClass().add("labels");

        Label labelCPUQuantity = new Label("");
        labelCPUQuantity.setPadding(new Insets(0, -3, 0, 0));
        labelCPUQuantity.setMinSize(230, 30);
        labelCPUQuantity.setAlignment(Pos.BASELINE_RIGHT);
        labelCPUQuantity.getStyleClass().add("labels");

        Label labelGPUQuantity = new Label("");
        labelGPUQuantity.setPadding(new Insets(0, 10, 0, 0));
        labelGPUQuantity.setMinSize(230, 30);
        labelGPUQuantity.setAlignment(Pos.BASELINE_RIGHT);
        labelGPUQuantity.getStyleClass().add("labels");

        Label labelRAMQuantity = new Label("");
        labelRAMQuantity.setPadding(new Insets(0, -5, 0, 0));
        labelRAMQuantity.setMinSize(230, 30);
        labelRAMQuantity.setAlignment(Pos.BASELINE_RIGHT);
        labelRAMQuantity.getStyleClass().add("labels");

        Label labelHardDriveQuantity = new Label("");
        labelHardDrive.setPadding(new Insets(0, -33, 0, 0));
        labelHardDriveQuantity.setMinSize(230, 30);
        labelHardDriveQuantity.setAlignment(Pos.BASELINE_RIGHT);
        labelHardDriveQuantity.getStyleClass().add("labels");

        Label labelPSUFillQuantity = new Label("");
        labelPSUFillQuantity.setPadding(new Insets(0, 0, 0, 0));
        labelPSUFillQuantity.setMinSize(230, 30);
        labelPSUFillQuantity.setAlignment(Pos.BASELINE_RIGHT);
        labelPSUFillQuantity.getStyleClass().add("labels");

        labelWarning = new Label();
        labelWarning.setAlignment(Pos.CENTER);


        Label labelNotification = new Label();
        labelNotification.setAlignment(Pos.CENTER);
        labelNotification.getStyleClass().add("labels");

        comboBox = new ComboBox<>();
        comboBox.setPromptText("Select components");
        comboBox.setVisibleRowCount(8);

        // Fill combobox with data
        fillComboBox(comboBox, myStmt);
        comboBox.getStyleClass().add("combobox");

        // Add component button
        Button addButton = new Button("Add component to the list");
        addButton.getStyleClass().add("buttons");
        addButton.setOnAction(event ->  {

            try {
                String cpuQuantity = "select * from quantity WHERE Name = '"+comboBox.getValue()+"'";
                ResultSet rs1 = myStmt.executeQuery(cpuQuantity);
                while (rs1.next()) {
                    if (comboBox.getValue().contains("CPU")) {
                        labelCPUFill.setText(comboBox.getValue());
                        labelCPUQuantity.setText(rs1.getString("Quantity"));
                    } else if (comboBox.getValue().contains("GPU")) {
                        labelGPUFill.setText(comboBox.getValue());
                        labelGPUQuantity.setText(rs1.getString("Quantity"));
                    }  else if (comboBox.getValue().contains("Motherboard")) {
                        labelMOBOFill.setText(comboBox.getValue());
                        labelMOBOQuantity.setText(rs1.getString("Quantity"));
                    } else if (comboBox.getValue().contains("RAM")) {
                        labelRAMFill.setText(comboBox.getValue());
                        labelRAMQuantity.setText(rs1.getString("Quantity"));
                    } else if (comboBox.getValue().contains("SSD")) {
                        labelHardDriveFill.setText(comboBox.getValue());
                        labelHardDriveQuantity.setText(rs1.getString("Quantity"));
                    } else if (comboBox.getValue().contains("PSU")) {
                        labelPSUFill.setText(comboBox.getValue());
                        labelPSUFillQuantity.setText(rs1.getString("Quantity"));
                    }
                }
            } catch (Exception exc) {
                exc.printStackTrace();
                System.out.println("Database not connected!");
            }

            if (labelCPUFill.getText() != "" && labelMOBOFill.getText() != "" && labelGPUFill.getText() != ""
                    && labelRAMFill.getText() != "" && labelHardDriveFill.getText() != "" && labelPSUFill.getText() != "") {
                labelNotification.setText("All components have been chosen.");
            }
        });

        //Add clear button
        Button clearButton = new Button("Clear list");
        clearButton.getStyleClass().add("buttons");
        clearButton.setOnAction(event -> {
            clearList(labelMOBOFill,labelMOBOQuantity,labelCPUFill,labelCPUQuantity,labelGPUFill,labelGPUQuantity
                    ,labelRAMFill,labelRAMQuantity,labelHardDriveFill,labelHardDriveQuantity,labelPSUFill,labelPSUFillQuantity,
                    labelNotification);
        });


        Label labelComponents = new Label("List of components you have chosen:");
        labelComponents.setPadding(new Insets(15, 10, 10, 10));
        labelComponents.setFont((Font.font("Verdana", FontWeight.BOLD, 15)));
        labelComponents.setAlignment(Pos.BOTTOM_CENTER);

        Label labelStatus = new Label("In stock (pieces)");
        labelStatus.setFont((Font.font("Verdana", FontWeight.BOLD, 15)));
        labelStatus.setAlignment(Pos.BASELINE_RIGHT);
        labelStatus.setPadding(new Insets(10, 90, 10, 10));
        labelStatus.setMinSize(1000, 50);


        //Hbox with combobox and addbutton, clearbutton
        HBox hbox = new HBox(40);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(comboBox, addButton, clearButton);

        //Hbox bottom with components
        HBox hboxMOBOComponents = new HBox(100);
        hboxMOBOComponents.setAlignment(Pos.CENTER);
        hboxMOBOComponents.getStyleClass().add("labels");
        hboxMOBOComponents.getChildren().addAll(labelMOBO, labelMOBOFill, labelMOBOQuantity);

        HBox hboxCPUComponents = new HBox(100);
        hboxCPUComponents.setAlignment(Pos.CENTER);
        hboxCPUComponents.getChildren().addAll(labelCPU, labelCPUFill, labelCPUQuantity);

        HBox hboxGPUComponents = new HBox(100);
        hboxGPUComponents.setAlignment(Pos.CENTER);
        hboxGPUComponents.getChildren().addAll(labelGPU, labelGPUFill, labelGPUQuantity);

        HBox hboxRAMComponents = new HBox(100);
        hboxRAMComponents.setAlignment(Pos.CENTER);
        hboxRAMComponents.getChildren().addAll(labelRAM, labelRAMFill, labelRAMQuantity);

        HBox hboxHardDriveComponents = new HBox(100);
        hboxHardDriveComponents.setAlignment(Pos.CENTER);
        hboxHardDriveComponents.getChildren().addAll(labelHardDrive, labelHardDriveFill, labelHardDriveQuantity);

        HBox hboxPSUComponents = new HBox(100);
        hboxPSUComponents.setAlignment(Pos.CENTER);
        hboxPSUComponents.getChildren().addAll(labelPSU, labelPSUFill, labelPSUFillQuantity);


        VBox vboxComponents = new VBox(30);
        vboxComponents.setAlignment(Pos.CENTER);
        vboxComponents.getChildren().addAll(hboxMOBOComponents, hboxCPUComponents, hboxGPUComponents, hboxRAMComponents, hboxHardDriveComponents, hboxPSUComponents);

        Button exitButton = new Button("Exit");
        exitButton.setAlignment(Pos.CENTER);
        exitButton.getStyleClass().add("buttons");
        exitButton.setOnAction(event -> window.close());

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setStyle("-fx-background-color: #9dbdf2");
        vbox.getChildren().addAll(labelTitle, iv1, labelChoice, hbox, labelWarning, labelNotification, labelComponents, labelStatus, vboxComponents, exitButton);


        Scene scene = new Scene(vbox, 1020, 900);
        scene.getStylesheets().add("css/style.css");

        window.setResizable(true);
        window.setScene(scene);
        window.show();

    }



    public static void clearList(Label labelMOBOFill, Label labelMOBOQuantity, Label labelCPUFill,
                                 Label labelCPUQuantity, Label labelGPUFill, Label labelGPUQuantity,
                                 Label labelRAMFill, Label labelRAMQuantity, Label labelHardDriveFill,
                                 Label labelHardDriveQuantity, Label labelPSUFill, Label labelPSUFillQuantity, Label labelNotification) {


        labelMOBOFill.setText("");
        labelMOBOQuantity.setText("");
        labelCPUFill.setText("");
        labelCPUQuantity.setText("");
        labelGPUFill.setText("");
        labelGPUQuantity.setText("");
        labelRAMFill.setText("");
        labelRAMQuantity.setText("");
        labelHardDriveFill.setText("");
        labelHardDriveQuantity.setText("");
        labelPSUFill.setText("");
        labelPSUFillQuantity.setText("");
        labelNotification.setText("List cleared!");

    }

    // Fill combobox with data
    public static void fillComboBox(ComboBox comboBox, Statement myStmt) {

        String fillComboBox = "select * from quantity";
        try {
            ResultSet rs = myStmt.executeQuery(fillComboBox);
            while (rs.next()) {
                comboBox.getItems().addAll(rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

