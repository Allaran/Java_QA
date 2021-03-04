package ru.stqa.pft.addresbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensureReconditions() {
        app.goTo().groupPage();
        if (app.group().List().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.group().List();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().List();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
