package ru.stqa.pft.addresbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;
import ru.stqa.pft.addresbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void checkForExistingGroup() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactCreation() {
        Contacts before = (Contacts) app.contact().all();
        app.goTo().addNewPage();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData()
                .withFirstName("Kirill")
                .withLastName("Shuvalov")
                .withPhone("900")
                .withEmail("testModifyEmail@work.com")
                .withGroup("test1").withPhoto(photo);
        app.contact().create(contact, true);
        app.goTo().homePage();
        Contacts after = (Contacts) app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
