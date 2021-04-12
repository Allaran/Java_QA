package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;
import ru.stqa.pft.addresbook.model.GroupData;
import ru.stqa.pft.addresbook.model.Groups;

import java.util.Optional;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AddContactToGroup extends TestBase {

    @BeforeMethod
    public void checkForExistingPreconditions() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }
        if (contacts.size() == 0) {
            app.goTo().addNewPage();
            app.contact().create(new ContactData()
                    .withFirstName("Kirill")
                    .withLastName("Shuvalov"), true);
            app.goTo().homePage();
        }
        app.goTo().homePage();
    }

    @Test
    public void testAddContactToGroup() {
        Groups allGroups = app.db().groups();
        Contacts allContacts = app.db().contacts();
        ContactData contactNotInGroups = app.contact().contactNotInGroups(allGroups, allContacts);
        GroupData emptyGroups = app.group().emptyGroups(allGroups, contactNotInGroups);

        if (emptyGroups == null) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            emptyGroups = app.db().groups().stream().skip(allGroups.size()).findFirst().get();
        }

        app.contact().addToGroup(contactNotInGroups, emptyGroups);
        Contacts contacts = app.db().contacts();
        assertEquals(allContacts.size(), contacts.size());
        int id = contactNotInGroups.getId();
        Optional<ContactData> alteredContact = contacts
                .stream()
                .filter((q) -> q.getId() == id)
                .findFirst();
        assertTrue(alteredContact.get().getGroups().contains(contactNotInGroups));
    }
}
