package ru.stqa.pft.addresbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void checkForExistingGroup() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().gotoAddNewPage();
        ContactData contact = new ContactData(
                "Kirill",
                "Shuvalov",
                "testNickName",
                "testTitle",
                "testAddress",
                "900",
                "testEmail@workmail.com",
                "testSecondaryAddress",
                "test1");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
