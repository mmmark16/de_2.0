package org.product.app.ui;

import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame {
    public BaseForm(int width, int height){
        setTitle("MyApp");
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width/2 - width/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2 - height/2
        );
    }
}
