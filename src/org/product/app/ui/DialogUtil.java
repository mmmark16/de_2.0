package org.product.app.ui;

import javax.swing.*;
import java.awt.*;

public class DialogUtil extends JOptionPane {

    public static void showInfo(Component component, String text){
        JOptionPane.showMessageDialog(component, text, "информация", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(Component component, String text){
        JOptionPane.showMessageDialog(component, text, "ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
