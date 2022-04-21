package org.product.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String title;
    private String productType;
    private int articleNumber;
    private String description;
    private String image;
    private int productionPersonCount;
    private int productionWorkshopNumber;
    private int minCostForAgent;

    public Product(String title, String productType, int articleNumber, String description, String image, int productionPersonCount, int productionWorkshopNumber, int minCostForAgent) {
        this(-1, title,  productType,  articleNumber,  description,  image,  productionPersonCount,  productionWorkshopNumber,  minCostForAgent);
    }
}
