package com.nagp.selenium.Controls.Concrete;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Controls.Controls.IDropDownOption;
import com.nagp.selenium.Controls.Controls.IPopOverDropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;

import java.util.ArrayList;
import java.util.List;
//PopOverDropDown control implementation
public class PopOverDropDown extends WebElementWrapped implements IPopOverDropDown {
    public PopOverDropDown(WebElement element) {
        super(element);
    }
    private List<IDropDownOption> Options;
    private List<String > OptionsValue;
    @Override
    public void Select(String selection) {
        WebDriverExtension.ClickUsingJavaScript(element.findElement(By.tagName("select")));
        GetOptions().stream().filter(x->x.GetOptionValue().equals(selection)).findFirst().orElse(null).Select();
    }

    @Override
    public String GetSelectedItem() {
        return GetOptions().stream().filter(x->x.isSelected() == true).findFirst().orElse(null).GetOptionValue();
    }

    @Override
    public List<String> GetOptionsValue() {
        if(OptionsValue == null)
            OptionsValue = GetDropdownOptionsValue();
        return OptionsValue;
    }

    private  List<IDropDownOption> GetOptions(){
        if(Options == null)
            Options = GetDropdownOptions();
        return  Options;
    }

    private List<IDropDownOption> GetDropdownOptions()
    {
        List<IDropDownOption> dropDownOptions = new ArrayList<IDropDownOption>();
        WebDriver driver = ((WrapsDriver)element).getWrappedDriver();
        driver.findElements(By.cssSelector("[aria-modal ='true'] ul[role = 'listbox'] li")).stream().forEach(x->dropDownOptions.add(new DropDownOption(x)));
        return dropDownOptions;
    }
    private List<String > GetDropdownOptionsValue()
    {
        List<String> values =  new ArrayList<String>();
        GetOptions().stream().forEach(x->values.add(x.GetOptionValue()));
        return values;
    }
}
