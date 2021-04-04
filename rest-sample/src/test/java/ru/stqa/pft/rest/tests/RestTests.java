package ru.stqa.pft.rest.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.rest.appmanager.IssueHelper;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(529);
        Set<Issue> oldIssues = IssueHelper.getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = IssueHelper.createIssue(newIssue);
        Set<Issue> newIssue = IssueHelper.getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssue, oldIssues);
    }
}
