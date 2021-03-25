package ru.stqa.pft.addresbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    private Contacts contactCache;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitNew() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("phone"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());
        attach(By.name("phone"), contactData.getPhoto());
//        if (creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.name("new_group")));
//        }
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelected() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void initModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void initModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id +"']")).click();
    }

    public void update() {
        click(By.name("update"));
    }

    public void modifyDetailsById(int id) {
        wd.findElement(By.xpath("//a[@href='view.php?id=" + id +"']")).click();
    }

    public void submitModify() {
        click(By.name("modifiy"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact, boolean creation) {
        fillForm(contact, true);
        submitNew();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initModificationById(contact.getId());
        fillForm(contact, false);
        update();
        contactCache = null;
    }

    public void delete(int index) {
        select(index);
        deleteSelected();
        closeAlert();
        pauseAfterDeletion();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        deleteSelected();
        closeAlert();
        pauseAfterDeletion();
        contactCache = null;
    }

    public void modifyThroughDetails(ContactData contact) {
        modifyDetailsById(contact.getId());
        submitModify();
        fillForm(contact, false);
        update();
        contactCache = null;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void pauseAfterDeletion() {
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstName)
                    .withLastName(lastName).withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return contacts;
    }

    public ContactData infoFromEditFrom(ContactData contact) {
        initContactModification(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastName")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withAddress(address)
                .withEmail(email)
                .withEmail(email2)
                .withEmail(email3);
    }

    private void initContactModification(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("id"));
        cells.get(7).findElement(By.tagName("a")).click();

//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../..td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]//td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
