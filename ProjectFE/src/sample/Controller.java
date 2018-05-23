package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sun.reflect.generics.tree.Tree;

import javax.management.StringValueExp;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static java.awt.SystemColor.text;


public class Controller implements Initializable {

    @FXML
    Button button;
    @FXML
    Button tb;
    @FXML
    Button bck;

   @FXML
    Button go;

    @FXML
    TreeView<String> treeid;

    //@FXML
    //ScrollPane scroll;
    @FXML
    BorderPane border;

    @FXML
    TextField textfield;




//GoGrid obj2=new GoGrid();
//Tables obj=new Tables();
int k=0;
FactoryView factor=new FactoryView();
    Views view0=factor.getView(0);
    Views view1=factor.getView(1);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Tables.textfield=this.textfield;
        Tables.border=this.border;
        GoGrid.textfield=this.textfield;
        GoGrid.border=this.border;
        //GoGrid.scroll=this.scroll;

        //Tables obj = new Tables();
        //GoGrid obj2=new GoGrid();
        textfield.setText(Paths.get("").toAbsolutePath().toString());
        TreeItem<String> root = new TreeItem<>("THIS PC");
        File[] path = File.listRoots();

        for (File p : path) {
            root.getChildren().add(new TreeItem<>(p.toString()));
        }
        //root.setExpanded(true);
        treeid.setRoot(root);
        for (TreeItem<String> t : root.getChildren())
            treeIdChildren(t);
        //obj2.gridView();;

        view0.draw();
        k=0;
        //obj.showFilesTableView();

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                //obj.showFilesTableView();
                view1.draw();
                k=1;
            }
        });

        tb.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                //obj2.gridView();
                view0.draw();
                k=0;

                //==



            }
        });


        bck.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                String temp=textfield.getText();
                //System.out.println(temp);
                //System.out.println(temp.substring(0,temp.lastIndexOf("\\")));
                String past=temp.substring(0,temp.lastIndexOf("\\"));
                //System.out.println(temp.lastIndexOf("\\"));


                if((past.compareTo("C:")==0) || (past.compareTo("D:")==0) || (past.compareTo("F:")==0) || (past.compareTo("E:")==0)) {
                    past=past+"\\";
                    textfield.setText(past);
                    //System.out.println(past);

                    if (k == 0) view0.draw();
                    else if (k == 1) view1.draw();
                }
                else {textfield.setText(past);
                    //System.out.println(past);

                    if (k == 0) view0.draw();
                    else if (k == 1) view1.draw();


                }

            }
        });

        go.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String temp=textfield.getText();
                if ((new File(temp)).isDirectory()) {
                    textfield.setText(temp);
                    if (k == 0) view0.draw();
                    else if (k == 1) view1.draw();

                }



            }
        });












    }

    public void setExpandedProperty(TreeItem<String> tv) {
        tv.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                TreeItem t = (TreeItem) ((BooleanProperty) observable).getBean();
                if (newValue) {
                    ObservableList<TreeItem<String>> files = t.getChildren();
                    for (TreeItem<String> f : files) treeIdChildren(f);
                }
            }
        });
    }



    public void treeIdChildren(TreeItem x) {


        if (x.getChildren().isEmpty()) {
            File file = new File(adress(x));

            if (file.listFiles() != null) {
                File[] filess = file.listFiles();

                for (File f : filess) {
                    TreeItem<String> temp = new TreeItem<>(f.getName());

                    x.getChildren().add(temp);
                    // treeIdChildren(temp);
                }
            }


        }
        setExpandedProperty(x);
    }


    public String adress(TreeItem<String> x) {
        String add = new String(x.getValue().toString());
        System.out.println(add);
        while (x.getParent().getValue().compareTo("THIS PC") != 0) {
            add = x.getParent().getValue() + "\\" + add;
            x = x.getParent();
        }
        return add;
    }

    public void expandFolder(MouseEvent m) {
        TreeItem<String> temp = ((TreeView<String>) m.getSource()).getSelectionModel().getSelectedItem();
        if (temp != null && !temp.isLeaf()) {
            textfield.setText(adress(temp));
            if(k==0) view0.draw(); //obj2.gridView();
            else if(k==1) view1.draw(); //obj.showFilesTableView();

        }

    }






    }


