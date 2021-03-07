package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase{

    @Test
    public void testContactData() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditFrom(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditFrom)));
        assertThat(contact.getAllEmails(), equalTo(contactInfoFromEditFrom));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditFrom));
//        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditFrom.getMobilePhone())));
//        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditFrom.getWorkPhone())));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return  phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
