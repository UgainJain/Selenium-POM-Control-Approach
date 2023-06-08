package com.nagp.selenium.Controls.Concrete;

import com.nagp.selenium.Controls.Controls.IAmazonDataCelGrid;
import com.nagp.selenium.Controls.Controls.IAmazonDataCel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
/*
    This control is specifically created for amazon and can not be reused is any other application.
    Thus, name includes the application name.
    The control name also include the widget used for creating this control.
    Depicts the whole grid of cel widget for the result.
 */
public class AmazonDataCelGrid extends WebElementWrapped implements IAmazonDataCelGrid {
    public AmazonDataCelGrid(WebElement element) {
        super(element);
    }

    private List<IAmazonDataCel> cardsNoAds = null;
    private List<IAmazonDataCel> cardsWithAds = null;

    @Override
    public List<IAmazonDataCel> getAllCards() {
        if(cardsWithAds != null)
            return cardsWithAds;
        return getCards();
    }

    @Override
    public List<IAmazonDataCel> getAllCardsWithoutAds() {
        if(cardsNoAds != null)
            return cardsNoAds;
        return getCardsNoAds();
    }

    @Override
    public IAmazonDataCel getTopResult() {
        return getAllCardsWithoutAds().stream().findFirst().orElse(null);
    }

    @Override
    public IAmazonDataCel getCardWithTitle(String title) {
        return getAllCards().stream()
                .filter(card-> card.GetTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int GetVisibleResultsCount() {
        return getAllCards().size();
    }

    private List<IAmazonDataCel> getCardsNoAds()
    {
        List<IAmazonDataCel> cards =  new ArrayList<IAmazonDataCel>();
        for(WebElement item  : element.findElements(By.cssSelector("div[class*='s-result-item'][data-component-type = 's-search-result']:not([class*='AdHolder'])")))
        {
            cards.add(new AmazonDataCel(item));
        }
        cardsNoAds = cards;
        return cards;
    }
    private List<IAmazonDataCel> getCards()
    {
        List<IAmazonDataCel> cards =  new ArrayList<IAmazonDataCel>();
        for(WebElement item  : element.findElements(By.cssSelector("div[class*='s-result-item'][data-component-type = 's-search-result']")))
        {
            cards.add(new AmazonDataCel(item));
        }
        cardsWithAds = cards;
        return cards;
    }
}
