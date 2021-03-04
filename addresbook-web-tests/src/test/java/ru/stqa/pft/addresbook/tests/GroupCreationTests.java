package ru.stqa.pft.addresbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addresbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().List();
        GroupData group = new GroupData("test1", "test1", "test2");
        app.group().initGroupCreation();
        app.group().fillGroupForm(group);
        app.group().submitGroupCreation();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().List();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}