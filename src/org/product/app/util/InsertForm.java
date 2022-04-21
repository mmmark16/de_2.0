package org.product.app.util;

import org.product.app.entity.Product;
import org.product.app.manager.ProductManager;
import org.product.app.ui.BaseForm;
import org.product.app.ui.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class InsertForm extends BaseForm {
    private JTextField titleField;
    private JTextField typeField;
    private JSpinner articlespinner;
    private JTextField descroptionField;
    private JTextField imageField;
    private JSpinner productperscountspinner;
    private JSpinner productworknumberspinner;
    private JSpinner mincostagentspinner;
    private JButton addButton;
    private JButton backButton;
    private JPanel manepanel;

    public InsertForm() {
        super(600, 400);
        setContentPane(manepanel);
        initbutton();
        setVisible(true);
    }

    private void initbutton() {
        addButton.addActionListener(e -> {
            String Title = titleField.getText();
            if (Title.isEmpty() || Title.length() > 100){
                DialogUtil.showError(this, "некоректно введено название");
                return;
            }
            String Type = typeField.getText();
            int ArticleNumbet = (int) articlespinner.getValue();
            String Description = descroptionField.getText();
            String Image = imageField.getText();
            int ProductionPersonCount = (int) productperscountspinner.getValue();
            int ProductionWorkshopNumber = (int) productworknumberspinner.getValue();
            int MinCostForAgent = (int) mincostagentspinner.getValue();

            Product product = new Product(Title, Type, ArticleNumbet, Description, Image, ProductionPersonCount, ProductionWorkshopNumber, MinCostForAgent);

            try {
                ProductManager.insert(product);
                DialogUtil.showInfo(this, "запись успешно добавлена");
                dispose();
                new ProductTableForm();
            } catch (SQLException er) {
                er.printStackTrace();
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new ProductTableForm();
        });

    }

}
