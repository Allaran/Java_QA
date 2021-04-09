package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;
import ru.stqa.pft.addresbook.model.GroupData;
import ru.stqa.pft.addresbook.model.Groups;

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
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().homePage();
        ContactData contactData = app.db().contactNotInGroup();
        app.contact().selectContactNotInGroup(contactData);
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        app.contact().selectGroup(group);
        app.contact().pushAddToGroup();
        ContactData contactData1 = app.db().contactById(contactData.getId());
        assertTrue(contactData1.getGroups().contains(group));
    }
}
