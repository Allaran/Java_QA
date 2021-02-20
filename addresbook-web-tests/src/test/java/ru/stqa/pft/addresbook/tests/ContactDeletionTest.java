package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void checkForExistingPreconditions() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoGroupPage();
            if (!app.getGroupHelper().isThereAGroup()) {
                app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            }
            app.getNavigationHelper().gotoAddNewPage();
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
            app.getNavigationHelper().gotoHomePage();
        }
    }

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlert();
    }


}
