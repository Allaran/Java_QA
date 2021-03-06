package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

    @BeforeMethod
    public void checkForExistingPreconditions() {
        if (app.contact().list().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test1"));
            }
            app.goTo().addNewPage();
            app.contact().create(new ContactData()
                    .withFirstName("Aleksey")
                    .withLastName("Shorshin")
                    .withEmail("aaa-a@email.ru")
                    .withEmail2("123456@email.com")
                    .withEmail3("aaa123@email.рф")
                    .withGroup("test1"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditFrom(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream()
                .filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
