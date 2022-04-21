package org.product.app.util;

import org.product.app.entity.Product;
import org.product.app.manager.ProductManager;
import org.product.app.ui.BaseForm;
import org.product.app.ui.CustomTableForm;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class ProductTableForm extends BaseForm {
    private JTable table;
    private JButton addButton;
    private JPanel manepanel;
    private CustomTableForm<Product> model;

    public ProductTableForm() {
        super(1200, 800);
        setContentPane(manepanel);
        initField();
        initbutton();
        setVisible(true);
    }


    private void initField(){

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != 1){
                        dispose();
                        new UpdateForm(model.getRows().get(row));
                    }
                }
            }
        });

        table.getTableHeader().setReorderingAllowed(false);
        try {
            model = new CustomTableForm<>(
                    Product.class,
                    new String[]{"ID", "название", "тип", "артикль", "описание", "картинка", "кол-во человек для производства", "номер производства", "минимальная стоимость для агента"},
                    ProductManager.selectAll()
            );
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initbutton(){
        addButton.addActionListener(e -> {
            dispose();
            new InsertForm();
        });
    }
}
