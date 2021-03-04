package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void checkForExistingPreconditions() {
        if (!app.getContactHelper().isThereAContact()) {
            app.goTo().groupPage();
            if (!app.group().isThereAGroup()) {
                app.group().create(new GroupData("test1", null, null));
            }
            app.goTo().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData(
                            "Kirill",
                            "Shuvalov",
                            "testNickName",
                            "testTitle",
                            "testAddress",
                            "900",
                            "testEmail@workmail.com",
                            "testSecondaryAddress",
                            "test1"),
                    true);
            app.goTo().gotoHomePage();
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlert();
        app.getContactHelper().pauseAfterDeletion();
        app.goTo().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
