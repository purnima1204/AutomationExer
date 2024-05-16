package com.automationexercise.Pages;

import org.testng.annotations.Test;

import com.automationexercise.Base.BaseClass;

public class ProductDetails extends BaseClass {
	ProductPage ProdPage;
  @Test
  public void ProdDetails() {
	  ProdPage = new ProductPage();
	  ProdPage.verifyAllProducts();
	  
  }
  
  @Test
  public void searchProduct() {
	  ProdPage = new ProductPage();
	  ProdPage.searchProduct();
  }
}