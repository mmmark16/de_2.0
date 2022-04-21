package org.product.app.util;

import org.product.app.entity.Product;
import org.product.app.manager.ProductManager;
import org.product.app.ui.BaseForm;
import org.product.app.ui.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class UpdateForm extends BaseForm {
    private JTextField titleField;
    private JTextField typeField;
    private JSpinner articlespinner;
    private JTextField descroptionField;
    private JTextField imageField;
    private JSpinner productperscountspinner;
    private JSpinner productworknumberspinner;
    private JSpinner mincostagentspinner;
    private JButton upgateButton;
    private JButton backButton;
    private JPanel manepanel;
    private JButton deletebutton;
    private JTextField idField;
    private Product product;

    public UpdateForm(Product product) {
        super(600, 400);
        this.product = product;
        setContentPane(manepanel);
        initfield();
        initbutton();
        setVisible(true);
    }

    private void initfield(){
        idField.setText(String.valueOf(product.getId()));
        titleField.setText(product.getTitle());
        typeField.setText(product.getProductType());
        articlespinner.setValue(product.getArticleNumber());
        productperscountspinner.setValue(product.getProductionPersonCount());
        productworknumberspinner.setValue(product.getProductionWorkshopNumber());
        descroptionField.setText(product.getDescription());
        imageField.setText(product.getImage());
        mincostagentspinner.setValue(product.getMinCostForAgent());
    }

    private void initbutton() {
        upgateButton.addActionListener(e -> {
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

            product.setTitle(Title);
            product.setDescription(Description);
            product.setImage(Image);
            product.setProductType(Type);
            product.setProductionPersonCount(ProductionPersonCount);
            product.setArticleNumber(ArticleNumbet);
            product.setProductionWorkshopNumber(ProductionWorkshopNumber);
            product.setMinCostForAgent(MinCostForAgent);
            try {
                ProductManager.update(product);
                DialogUtil.showInfo(this, "запись успешно отредактирована");
                dispose();
                new ProductTableForm();
            } catch (SQLException er) {
                er.printStackTrace();
            }
        });

        deletebutton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(this, "вы уверены?", "удаление записи", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                try {
                    ProductManager.delete(product);
                    DialogUtil.showInfo(this, "запись успешно удалена");
                    dispose();
                    new ProductTableForm();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(e -> {
            dispose();
            new ProductTableForm();
        });

    }

}
