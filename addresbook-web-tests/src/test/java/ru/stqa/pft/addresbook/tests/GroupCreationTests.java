package ru.stqa.pft.addresbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addresbook.model.GroupData;
import ru.stqa.pft.addresbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().initGroupCreation();
        app.group().fillGroupForm(group);
        app.group().submitGroupCreation();
        app.group().returnToGroupPage();
        Groups after = app.group().all();
        assertEquals(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}