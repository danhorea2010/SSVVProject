package org.example.steps.serenity;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.example.pages.EmagPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;


public class EmagUserSteps {

    private EmagPage emagPage;

    @Step
    public void enters(String keyword){
        emagPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        emagPage.lookup_terms();
    }

    @Step
    public void is_the_home_page(){
        emagPage.open();
    }

    @Step
    public void looks_for(String term){
        enters(term);
        starts_search();
    }

    @Step
    public void filter_in_stock(){
        emagPage.filter_in_stock();
    }

    @Step
    public void should_see_product_name(String name){
        assertThat(emagPage.getProductName(), hasItem(containsString(name)));
    }


}
