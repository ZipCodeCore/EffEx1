package rocks.zipcode;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.*;

public class SimpleTextEditor extends Application {

    private TextArea textArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the main layout
        BorderPane root = new BorderPane();
        
        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create File menu
        Menu fileMenu = new Menu("File");
        
        // Create Menu Items
        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open...");
        MenuItem saveFile = new MenuItem("Save...");
        MenuItem exitApp = new MenuItem("Exit");

        // Add items to File menu
        fileMenu.getItems().addAll(newFile, openFile, saveFile, new SeparatorMenuItem(), exitApp);
        
        // Add File menu to the menu bar
        menuBar.getMenus().add(fileMenu);

        // Event handling for New File
        newFile.setOnAction(e -> textArea.clear());
        
        // Event handling for Open File
        openFile.setOnAction(e -> openFile(primaryStage));
        
        // Event handling for Save File
        saveFile.setOnAction(e -> saveFile(primaryStage));
        
        // Event handling for Exit
        exitApp.setOnAction(e -> primaryStage.close());

        // Add the MenuBar and TextArea to the layout
        root.setTop(menuBar);
        root.setCenter(textArea);

        // Create a scene with the root layout
        Scene scene = new Scene(root, 600, 400);

        // Set the scene and show the stage
        primaryStage.setTitle("Simple Text Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Open file function
    private void openFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                textArea.setText(stringBuilder.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Save file function
    private void saveFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
