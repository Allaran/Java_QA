package ru.stqa.pft.addresbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;


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
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().fillContactForm(new ContactData(
                "Kirill",
                "Shuvalov",
                "testNickName",
                "testTitle",
                "testAddress",
                "900",
                "testEmail@workmail.com",
                "testSecondaryAddress",
                "test1"), true);
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
