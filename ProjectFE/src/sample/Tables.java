package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.View;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;

import static sample.Controller.*;

/**
 * Created by Asus on 4/24/2017.
 */
public class Tables implements Views {

     static TextField textfield=null;
     static BorderPane border=null;

    public void showFilesTableView()  {
        TableView table=new TableView();
        //table = new TableView<AllInfo>();

        table.setEditable(true);



        TableColumn img = new TableColumn<AllInfo, ImageView>("Icon");
        img.setCellValueFactory(new PropertyValueFactory<AllInfo, ImageView>("icon"));
        table.getColumns().add(img);


        TableColumn Name = new TableColumn<AllInfo, String>("Name");
        Name.setMinWidth(100);
        Name.setCellValueFactory(new PropertyValueFactory<AllInfo, String>("name"));
        table.getColumns().add(Name);

        TableColumn Size = new TableColumn<AllInfo, String>("Size");
        Size.setCellValueFactory(new PropertyValueFactory<AllInfo, String>("size"));
        table.getColumns().add(Size);

        TableColumn dmd = new TableColumn<AllInfo, String>("Date Modified");
        dmd.setCellValueFactory(new PropertyValueFactory<AllInfo, String>("date"));
        table.getColumns().add(dmd);

        SimpleDateFormat s = new SimpleDateFormat("MMM d, yyyy");

        ObservableList<AllInfo> data = FXCollections.observableArrayList();
        File[] files = (new File(textfield.getText())).listFiles();

        for (File f : files) {

            ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(f);
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
            Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView iv = new ImageView(fxImage);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setFitWidth(50);

            AllInfo obj = new AllInfo(iv, f.getName(), String.valueOf(f.length()), String.valueOf(s.format(f.lastModified())));
            obj.loc = f.getName();
            String temp1 = obj.loc;

            data.add(obj);


            Name.setCellFactory(tc -> {
                TableCell<AllInfo, String> cell = new TableCell<AllInfo, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item);
                    }

                    ;
                };
                cell.setOnMouseClicked(e ->{
                    if (! cell.isEmpty()) {
                        String userId = cell.getItem();

                        String temp=textfield.getText()+"\\"+userId;

                        if ((new File(temp)).isDirectory()) {
                            textfield.setText(temp);
                            table.getColumns().clear();
                            showFilesTableView();

                        }

                    }


                });
                return cell;

            });



        }

        table.getItems().clear();
        table.setItems(data);
        table.setVisible(true);
        border.setCenter(table);


    }


    @Override
    public void draw() {
        showFilesTableView();
    }
}
