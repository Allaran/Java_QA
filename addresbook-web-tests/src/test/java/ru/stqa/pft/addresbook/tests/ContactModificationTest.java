package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

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
        app.getContactHelper().initEditContactModification();
        app.getContactHelper().fillContactForm(new ContactData(
                "Kirill",
                "Shuvalov",
                "testModifyNickNameByEdit",
                "testModifyTitle",
                "testModifyAddress",
                "900",
                "testModifyEmail@work.com",
                "testModifySecondaryAddress",
                null
        ), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public void testDetailContactModification() {
        app.getContactHelper().initDetailContactModification();
        app.getContactHelper().submitNewContact();
        app.getContactHelper().fillContactForm(new ContactData(
                "Kirill",
                "Shuvalov",
                "testModifyNickNameByDetail",
                "testModifyTitle",
                "testModifyAddress",
                "900",
                "testModifyEmail@work.com",
                "testModifySecondaryAddress",
                null
        ), false);
        app.getContactHelper().updateContact();
        app.getContactHelper().submitModifyContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
