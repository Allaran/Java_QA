package ru.stqa.pft.addresbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;
import ru.stqa.pft.addresbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {"test1", "header1", "footer1"});
        list.add(new Object[] {"test2", "header2", "footer2"});
        list.add(new Object[] {"test3", "header3", "footer3"});
        return list.iterator();
    }

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
