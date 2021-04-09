package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;
import ru.stqa.pft.addresbook.model.GroupData;
import ru.stqa.pft.addresbook.model.Groups;

import static org.testng.AssertJUnit.assertTrue;

public class DeleteContactFromGroup extends TestBase {

    @BeforeMethod
    public void checkForExistingPreconditions() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        app.goTo().homePage();
        if (contacts.size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Kirill")
                    .withLastName("Shuvalov"), true);
            app.goTo().homePage();
        }

        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroup2"));
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        app.goTo().homePage();
        ContactData contactData = app.db().contactInGroup();
        GroupData groupData = contactData.getGroups().iterator().next();
        app.contact().getGroupData(groupData);
        app.contact().selectContactNotInGroup(contactData);
        app.contact().pushRemoveFromGroup();
        app.goTo().homePage();
        ContactData contactData1 = app.db().contactById(contactData.getId());
        assertTrue(contactData1.getGroups().contains(groupData));
    }
}
