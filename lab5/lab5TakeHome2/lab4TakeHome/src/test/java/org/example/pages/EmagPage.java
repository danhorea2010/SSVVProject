package org.example.pages;

import lombok.var;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.emag.ro")
public class EmagPage extends PageObject {

    @FindBy(name="query")
    private WebElementFacade searchBar;

    @FindBy(xpath ="/html/body/div[4]/nav[1]/div/div/div[2]/div/form/div[1]/div[2]/button[2]/i")
    private WebElementFacade lookupButton;

    @FindBy(xpath = "/html/body/div[3]/div[2]/div/section[1]/div/div[3]/div[1]/div[2]/div[2]/div[1]/div/div/a")
    private WebElementFacade inStockFilter;


    public void enter_keywords(String keyword){
        searchBar.type(keyword);
    }

    public void filter_in_stock(){
        inStockFilter.click();
    }

    public void lookup_terms(){
        lookupButton.click();
    }

    public List<String> getProductName() {
        var products = findAll(By.xpath("/html/body/div[3]/div[2]/div/section[1]/div/div[3]/div[2]/div[5]/div[1]"));

        var productNameList = products.stream()
                .map(element -> element.getText().toLowerCase()
                )
                .collect(Collectors.toList());

//        for(var p : products){
//            System.out.println(p.toString());
//        }
        return productNameList;
    }



}
