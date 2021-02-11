package ru.stqa.pft.addresbook.tests;

import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

public class ContactModificationTest extends TestBase {

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
                "testModifySecondaryAddress"
        ));
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
                "testModifySecondaryAddress"
        ));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
