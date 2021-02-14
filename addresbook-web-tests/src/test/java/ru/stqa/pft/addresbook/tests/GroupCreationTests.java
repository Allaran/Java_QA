package ru.stqa.pft.addresbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addresbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test1", "test2"));
        app.submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }
}