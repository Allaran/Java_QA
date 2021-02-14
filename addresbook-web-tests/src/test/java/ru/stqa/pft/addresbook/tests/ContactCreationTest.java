package ru.stqa.pft.addresbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addresbook.model.ContactData;


public class ContactCreationTest extends TestBase {

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
                "testSecondaryAddress"));
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
