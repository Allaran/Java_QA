package ru.stqa.pft.addresbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;
import ru.stqa.pft.addresbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

//    @BeforeMethod
//    public void checkForExistingPreconditions() {
//        if (app.contact().list().size() == 0) {
//            app.goTo().groupPage();
//            if (app.group().all().size() == 0) {
//                app.group().create(new GroupData().withName("test1"));
//            }
//            app.goTo().addNewPage();
//            app.contact().create(new ContactData()
//                    .withFirstName("Kirill")
//                    .withLastName("Shuvalov")
//                    .withPhone("900")
//                    .withEmail("testModifyEmail@work.com")
//                    .withGroup("test1"), true);
//            app.goTo().homePage();
//        }
//    }

    @Test
    public void testContactDeletion () throws Exception {
        Contacts before = (Contacts) app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Contacts after = (Contacts) app.contact().all();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
