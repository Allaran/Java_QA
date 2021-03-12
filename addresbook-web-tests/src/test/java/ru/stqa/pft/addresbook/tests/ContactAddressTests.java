package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void checkExistingConditions() {
        if(app.contact().list().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test1"));
            }
            app.goTo().addNewPage();
            app.contact().create(new ContactData()
                    .withFirstName("Kirill")
                    .withLastName("Shuvalov")
                    .withPhone("900")
                    .withAddress("testAddress")
                    .withGroup("test1"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactEditForm = app.contact().infoFromEditFrom(contact);
        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactEditForm.getAddress())));
    }

    public static String cleaned(String address){
        return address.replaceAll("\\n", "").replaceAll("\\s", "");
    }
}
