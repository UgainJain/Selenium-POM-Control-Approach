package com.nagp.Shared.Entitites;

import com.nagp.selenium.Controls.Controls.IAmazonDataCel;

/*
    To be used to store products info for the products for future verification purposes
 */
public class ProductInfo {
    public ProductInfo(IAmazonDataCel cell)
    {
        this.Title = cell.GetTitle();
        this.Rating = cell.GetRating();
        this.Price = cell.GetCost();
    }
    public String Title;
    public float Rating;
    public float Price;
}
