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
        labelChoice.getStyleClass().add("labels");

        Label labelStatus = new Label("In stock (pieces)");
        labelStatus.getStyleClass().add("labels");
//        labelStatus.setAlignment(Pos.BASELINE_RIGHT);
        labelStatus.setPadding(new Insets(0, 0, 0, 650));
//        labelStatus.setMinSize(500, 50);

        Label labelPrice = new Label("Price");
        labelPrice.setPadding(new Insets(0,0,0,50));
        labelPrice.getStyleClass().add("labels");

        HBox hboxProperties = new HBox(40);
        hboxProperties.getChildren().addAll(labelStatus, labelPrice);

        //Chosen components part label
        Label labelMOBO = new Label("Motherboard: ");
        labelMOBO.setMinSize(0, 15);
        labelMOBO.setPadding(new Insets(0,0,0,0));
        labelMOBO.setAlignment(Pos.BASELINE_CENTER);
        labelMOBO.getStyleClass().add("labels");

        Label labelCPU = new Label("Processor: ");
        labelCPU.setMinSize(0, 15);
        labelCPU.setPadding(new Insets(0,0,0,0));
        labelCPU.setAlignment(Pos.BASELINE_CENTER);
        labelCPU.getStyleClass().add("labels");

        Label labelGPU = new Label("Graphics Card: ");
        labelGPU.setMinSize(100, 15);
        labelGPU.setPadding(new Insets(0,0,0,-10));
        labelGPU.setAlignment(Pos.BASELINE_CENTER);
        labelGPU.getStyleClass().add("labels");

        Label labelRAM = new Label("RAM: ");
        labelRAM.setMinSize(100, 15);
        labelRAM.setPadding(new Insets(0,0,0,-30));
        labelRAM.setAlignment(Pos.BASELINE_CENTER);
        labelRAM.getStyleClass().add("labels");

        Label labelHardDrive = new Label("Hard Drive: ");
        labelHardDrive.setMinSize(100, 15);
        labelHardDrive.setPadding(new Insets(0,0,0,20));
        labelHardDrive.setAlignment(Pos.BASELINE_RIGHT);
        labelHardDrive.getStyleClass().add("labels");

        Label labelPSU = new Label("Power Supply: ");
        labelPSU.setMinSize(100, 15);
        labelPSU.setAlignment(Pos.BASELINE_CENTER);
        labelPSU.getStyleClass().add("labels");


        //Fill component labels
        Label labelMOBOFill = new Label("");
        labelMOBOFill.setMinSize(300, 30);
        labelMOBOFill.setPadding(new Insets(0, 0, 0, 20));
        labelMOBOFill.setAlignment(Pos.TOP_CENTER);
        labelMOBOFill.getStyleClass().add("labels");

        Label labelCPUFill = new Label("");
        labelCPUFill.setMinSize(300, 30);
        labelCPUFill.setPadding(new Insets(0, 0, 0, 50));
        labelCPUFill.setAlignment(Pos.TOP_CENTER);
        labelCPUFill.getStyleClass().add("labels");

        Label labelGPUFill = new Label("");
        labelGPUFill.setMinSize(300, 30);
        labelGPUFill.setPadding(new Insets(0, 0, 0, 20));
        labelGPUFill.setAlignment(Pos.CENTER);
        labelGPUFill.getStyleClass().add("labels");

        Label labelRAMFill = new Label("");
        labelRAMFill.setMinSize(300, 30);
        labelRAMFill.setPadding(new Insets(0, 0, 0, 30));
        labelRAMFill.setAlignment(Pos.CENTER);
        labelRAMFill.getStyleClass().add("labels");

        Label labelHardDriveFill = new Label("");
        labelHardDriveFill.setMinSize(300, 30);
        labelHardDriveFill.setPadding(new Insets(0, 0, 0, 30));
        labelHardDriveFill.setAlignment(Pos.CENTER);
        labelHardDriveFill.getStyleClass().add("labels");

        Label labelPSUFill = new Label("");
        labelPSUFill.setMinSize(300, 30);
        labelPSUFill.setPadding(new Insets(0, 0, 0, 0));
        labelPSUFill.setAlignment(Pos.CENTER);
        labelPSUFill.getStyleClass().add("labels");

        //Quantityy
        Label labelMOBOQuantity = new Label("");
        labelMOBOQuantity.setPadding(new Insets(0, 0, 0, -10));
        labelMOBOQuantity.setMinSize(100, 30);
//        labelMOBOQuantity.setAlignment(Pos.BASELINE_LEFT);
        labelMOBOQuantity.getStyleClass().add("labels");

        Label labelCPUQuantity = new Label("");
        labelCPUQuantity.setPadding(new Insets(0, 0, 0, 8));
        labelCPUQuantity.setMinSize(100, 30);
        labelCPUQuantity.getStyleClass().add("labels");

        Label labelGPUQuantity = new Label("");
        labelGPUQuantity.setPadding(new Insets(0, 10, 0, -6));
        labelGPUQuantity.setMinSize(100, 30);
        labelGPUQuantity.getStyleClass().add("labels");

        Label labelRAMQuantity = new Label("");
        labelRAMQuantity.setPadding(new Insets(0, -5, 0, -0));
        labelRAMQuantity.setMinSize(100, 30);
        labelRAMQuantity.getStyleClass().add("labels");

        Label labelHardDriveQuantity = new Label("");
        labelHardDrive.setPadding(new Insets(0, 0, 0, 10));
        labelHardDriveQuantity.setMinSize(100, 30);
        labelHardDriveQuantity.getStyleClass().add("labels");

        Label labelPSUFillQuantity = new Label("");
        labelPSUFillQuantity.setPadding(new Insets(0, 0, 0, -10));
        labelPSUFillQuantity.setMinSize(100, 30);
        labelPSUFillQuantity.getStyleClass().add("labels");

        //Price
        Label labelMOBOPrice = new Label("");
        labelMOBOPrice.setPadding(new Insets(0, 10, 0, 0));
        labelMOBOPrice.getStyleClass().add("labels");

        Label labelCPUPrice = new Label("");
        labelCPUPrice.setPadding(new Insets(0, 0, 0, 20));
        labelCPUPrice.getStyleClass().add("labels");

        Label labelGPUPrice = new Label("");
        labelGPUPrice.setPadding(new Insets(0, 10, 0, 5));
        labelGPUPrice.getStyleClass().add("labels");

        Label labelRAMPrice = new Label("");
        labelRAMPrice.setPadding(new Insets(0, -5, 0, 10));
        labelRAMPrice.getStyleClass().add("labels");

        Label labelHardDrivePrice = new Label("");
        labelHardDrivePrice.setPadding(new Insets(0, 0, 0, 10));
        labelHardDrivePrice.getStyleClass().add("labels");

        Label labelPSUPrice = new Label("");
        labelPSUPrice.setPadding(new Insets(0, 0, 0, 0));
        labelPSUPrice.getStyleClass().add("labels");

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
                        labelCPUPrice.setText(rs1.getString("Price"));
                    } else if (comboBox.getValue().contains("GPU")) {
                        labelGPUFill.setText(comboBox.getValue());
                        labelGPUQuantity.setText(rs1.getString("Quantity"));
                        labelGPUPrice.setText(rs1.getString("Price"));
                    }  else if (comboBox.getValue().contains("Motherboard")) {
                        labelMOBOFill.setText(comboBox.getValue());
                        labelMOBOQuantity.setText(rs1.getString("Quantity"));
                        labelMOBOPrice.setText(rs1.getString("Price"));
                    } else if (comboBox.getValue().contains("RAM")) {
                        labelRAMFill.setText(comboBox.getValue());
                        labelRAMQuantity.setText(rs1.getString("Quantity"));
                        labelRAMPrice.setText(rs1.getString("Price"));
                    } else if (comboBox.getValue().contains("SSD")) {
                        labelHardDriveFill.setText(comboBox.getValue());
                        labelHardDriveQuantity.setText(rs1.getString("Quantity"));
                        labelHardDrivePrice.setText(rs1.getString("Price"));
                    } else if (comboBox.getValue().contains("PSU")) {
                        labelPSUFill.setText(comboBox.getValue());
                        labelPSUFillQuantity.setText(rs1.getString("Quantity"));
                        labelPSUPrice.setText(rs1.getString("Price"));
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
        labelComponents.getStyleClass().add("labels");
        labelComponents.setAlignment(Pos.BOTTOM_CENTER);

        //Hbox with combobox and addbutton, clearbutton
        HBox hbox = new HBox(40);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(comboBox, addButton, clearButton);

        //Hbox bottom with components
        HBox hboxMOBOComponents = new HBox(100);
        hboxMOBOComponents.setAlignment(Pos.CENTER);
        hboxMOBOComponents.getStyleClass().add("labels");
        hboxMOBOComponents.getChildren().addAll(labelMOBO, labelMOBOFill, labelMOBOQuantity, labelMOBOPrice);

        HBox hboxCPUComponents = new HBox(100);
        hboxCPUComponents.setAlignment(Pos.CENTER);
        hboxCPUComponents.getChildren().addAll(labelCPU, labelCPUFill, labelCPUQuantity, labelCPUPrice);

        HBox hboxGPUComponents = new HBox(100);
        hboxGPUComponents.setAlignment(Pos.CENTER);
        hboxGPUComponents.getChildren().addAll(labelGPU, labelGPUFill, labelGPUQuantity, labelGPUPrice);

        HBox hboxRAMComponents = new HBox(100);
        hboxRAMComponents.setAlignment(Pos.CENTER);
        hboxRAMComponents.getChildren().addAll(labelRAM, labelRAMFill, labelRAMQuantity, labelRAMPrice);

        HBox hboxHardDriveComponents = new HBox(100);
        hboxHardDriveComponents.setAlignment(Pos.CENTER);
        hboxHardDriveComponents.getChildren().addAll(labelHardDrive, labelHardDriveFill, labelHardDriveQuantity, labelHardDrivePrice);

        HBox hboxPSUComponents = new HBox(100);
        hboxPSUComponents.setAlignment(Pos.CENTER);
        hboxPSUComponents.getChildren().addAll(labelPSU, labelPSUFill, labelPSUFillQuantity, labelPSUPrice);


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
        vbox.getChildren().addAll(labelTitle, iv1, labelChoice, hbox, labelWarning, labelNotification, labelComponents, hboxProperties, vboxComponents, exitButton);


        Scene scene = new Scene(vbox, 1000, 900);
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

