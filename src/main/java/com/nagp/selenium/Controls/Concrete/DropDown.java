package com.nagp.selenium.Controls.Concrete;

import com.nagp.selenium.Controls.Controls.IDropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

//DropDown control implementation
public class DropDown extends WebElementWrapped implements IDropDown {
    private Select selectElement;
    public DropDown(WebElement element) {
        super(element);
        if(element.getTagName().equals("select"))
            selectElement = new Select(element);
        else
            selectElement = new Select(element.findElement(By.tagName("select")));
    }
    private List<String > OptionsValue;
    @Override
    public void Select(String selection) {
        selectElement.selectByVisibleText(selection);
    }

    @Override
    public String GetSelectedItem() {
        return selectElement.getFirstSelectedOption().getText();
    }

    @Override
    public List<String> GetOptionsValue() {
        if(OptionsValue == null)
            OptionsValue = GetDropdownOptionsValue();
        return OptionsValue;
    }
    private List<String > GetDropdownOptionsValue()
    {
        List<String> values =  new ArrayList<String>();
        selectElement.getOptions().stream().forEach(x->values.add(x.getText()));
        return values;
    }
}
