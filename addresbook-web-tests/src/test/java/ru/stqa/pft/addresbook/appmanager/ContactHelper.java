package ru.stqa.pft.addresbook.appmanager;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addresbook.model.ContactData;


public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickName"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("address"), contactData.getAddress());
        type(By.name("phone"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("secondaryAddress"), contactData.getSecondaryAddress());
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void updateContact() {
        click(By.name("ipdate"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void initEditContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void initDetailContactModification() {
        click(By.xpath("//img[@alt='Details']"));
    }

    public void submitModifyContact() {
        click(By.name("modify"));
    }

    public void submitNewContact() {
        click(By.xpath("(//input[@name='submit'][2])"));
    }
}
