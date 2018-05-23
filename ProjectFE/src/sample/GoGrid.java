package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.PopupWindow;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Asus on 4/24/2017.
 */

class Folder extends VBox {

    String loc;

}

public class GoGrid implements Views {


    static TextField textfield=null;
    static BorderPane border=null;
    //static ScrollPane scroll=null;





    public void gridView() {

        VBox content = new VBox();
        ScrollPane scroller = new ScrollPane(content);
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);

        GridPane gpane = new GridPane();
        gpane.setMaxSize(300,300);



        File[] files = (new File(textfield.getText())).listFiles();
/*

        String[] fnames=new String[4];

        int j=0;
        for(File f:files)
        {
           fnames[j]=f.getName();
            j++;
        }
        Arrays.sort(fnames, Collections.reverseOrder());

        for (int k = 0; k <fnames.length ; k++) {
            System.out.println(fnames[k]);
        }
*/
        int i = 0;//,q=0;
        for (File f : files) {

            ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(f);
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
            Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView iv = new ImageView(fxImage);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setFitWidth(50);
            Label l = new Label(f.getName());
            //Label l = new Label(fnames[q]);
            l.setMaxSize(70,70);
            Folder vb = new Folder();
            vb.loc = f.getName();
            //vb.loc = fnames[q];
            vb.setAlignment(Pos.CENTER);
            vb.setMaxSize(200,200);
            vb.getChildren().addAll(iv, l);

            vb.setOnMouseClicked((e) -> {
                if (e.getClickCount() == 2) {
                    String temp = textfield.getText() + "\\" + ((Folder) e.getSource()).loc;
                    if ((new File(temp)).isDirectory()) {
                        textfield.setText(temp);
                        gridView();
                    }
                }
            });
            gpane.add(vb, i % 10, i / 10);
            i++;
            //q++;
        }
        //scrollPane.setContent(flowPane);
        //scroll.setContent
        AnchorPane acp=new AnchorPane();
        acp.getChildren().add(gpane);

         content.getChildren().add(acp);

//        scrl.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrl.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrl.setContent(gpane);
          scroller.setVisible(true);
        //scrl.setMaxSize(1000,1000);
        border.setCenter(scroller);

    }


    @Override
    public void draw() {
        gridView();
    }
}
