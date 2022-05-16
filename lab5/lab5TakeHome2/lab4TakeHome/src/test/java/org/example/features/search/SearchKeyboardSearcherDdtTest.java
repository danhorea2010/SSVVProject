package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.EmagUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/keyboards.csv")
public class SearchKeyboardSearcherDdtTest {

    @Managed(uniqueSession=true)
    public WebDriver webDriver;

    @ManagedPages(defaultUrl = "https://www.emag.ro/")
    public Pages pages;

    public String name;
    public String price;

    @Qualifier
    public String setQualifier () {
        return name;
    }

    @Steps
    public EmagUserSteps endUser;

    @Test
    public void searchForKeyboards() {
        endUser.is_the_home_page();
        endUser.looks_for(getName());
        //endUser.filter_in_stock();
        endUser.should_see_product_name(getName());
        endUser.should_see_product_name(getPrice());
    }

    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }




}
