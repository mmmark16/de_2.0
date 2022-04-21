package org.product.app;

import org.product.app.manager.ProductManager;
import org.product.app.util.ProductTableForm;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ProductTableForm();

    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lol", "root", "1234");
    }
}

/*
    CREATE TABLE IF NOT EXISTS `test` (
        `product` VARCHAR(256) NOT NULL,
        `materialtitle` VARCHAR(256) NOT NULL,
        `countmaterial` INT NOT NULL,
        );

        INSERT INTO productmaterial(ProductID, MaterialID, Count)
        SELECT product.ID as ProductID, material.ID as MaterialID, test.countmaterial as Count
        FROM test
        INNER JOIN product on product.Title = test.product
        INNER JOIN material on material.Title = test.materialtitle
        
        git config --global user.name "Pa4ok"
        git config --global user.email "pa4ok228@mail.ru"
        git init
        git remote add origin всатвить ссылку из гокса
        git add -A
        git commit -m "commit"
        git branch session 1
        git checkout session 1
        git push и потом то что предложит
*/
