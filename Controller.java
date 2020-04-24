package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.io.*;

public class Controller {


    static ArrayList<String> fileList = new ArrayList<>();
    static ArrayList<String> copiedFileList = new ArrayList<>();
    static String srcDir;
    static String destDir;


    @FXML
    private TextField fileListBox;

    @FXML
    private TextField srcDirBox;

    @FXML
    private TextField descDirBox;

    public static void main(String[] args) {

    }



    public void getSrcDirectory(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Source Folder");
        File file = chooser.showDialog(new Stage());

        if(file != null) {
            srcDirBox.setText(file.toString());
            srcDir = file.toString();
        }
    }


    public void getDestDirectory(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Destination Folder");
        File file = chooser.showDialog(new Stage());

        if(file != null) {
            descDirBox.setText(file.toString());
            destDir = file.toString();
        }
    }



    public void getFileList(ActionEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());

        if(file != null) {
            fileListBox.setText(file.toString());
            String fileListPath = file.toString();
            try {
                File listFile = new File(fileListPath);
                FileReader fileReader = new FileReader(listFile);
                BufferedReader reader = new BufferedReader(fileReader);
                String line = null;

                while ((line = reader.readLine()) != null) {
                    fileList.add(line);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }


    }

    public void performCopy() {
        if(!fileList.isEmpty()) {
            for (String file: fileList) {
                try {
                    File srcFile = new File(srcDir + File.separator + file);
                    File destFile = new File(destDir+ File.separator + file);
                    Files.copy(srcFile.toPath(), destFile.toPath());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
