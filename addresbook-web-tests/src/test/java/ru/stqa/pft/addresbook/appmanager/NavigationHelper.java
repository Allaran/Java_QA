package ru.stqa.pft.addresbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.name("new"))
                && isElementPresent(By.name("delete"))
                && isElementPresent(By.name("edit"))){
            return;
        }
        click(By.linkText("groups"));
    }

    public void gotoAddNewPage() {
        if (isElementPresent(By.tagName("h1")) && isElementPresent(By.name("submit"))) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }
}
