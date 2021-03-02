package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void checkForExistingPreconditions() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoGroupPage();
            if (!app.getGroupHelper().isThereAGroup()){
                app.getGroupHelper().createGroup(new GroupData(
                        "test1",
                        null,
                        null));
            }
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData(
                    "Kirill",
                    "Shuvalov",
                    "testModifyNickNameByEdit",
                    "testModifyTitle",
                    "testModifyAddress",
                    "900",
                    "testModifyEmail@work.com",
                    "testModifySecondaryAddress",
                    "test1"
            ), true);
            app.getNavigationHelper().gotoHomePage();
        }
    }
    @Test
    public void testEditContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initEditContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),
                "Kirill",
                "Shuvalov",
                "testModifyNickNameByEdit",
                "testModifyTitle",
                "testModifyAddress",
                "900",
                "testModifyEmail@work.com",
                "testModifySecondaryAddress",
                null);

        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testDetailContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initDetailContactModification(before.size() - 1);
        app.getContactHelper().submitNewContact();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),
                "Kirill",
                "Shuvalov",
                "testModifyNickNameByDetail",
                "testModifyTitle",
                "testModifyAddress",
                "900",
                "testModifyEmail@work.com",
                "testModifySecondaryAddress",
                null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        app.getContactHelper().submitModifyContact();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
