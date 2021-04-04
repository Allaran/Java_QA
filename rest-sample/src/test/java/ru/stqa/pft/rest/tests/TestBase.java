package ru.stqa.pft.rest.tests;


import org.testng.SkipException;
import ru.stqa.pft.rest.appmanager.IssueHelper;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class TestBase {

    public boolean isIssueOpen(int issueId) throws IOException {
        Set<Issue> issues = IssueHelper.getIssues();
        Issue issue = issues.stream()
                .filter(Issue -> Objects.equals(issueId, Issue.getId())).findFirst().get();
        if(issue.getBugStatus().equals("Closed")){
            return false;
        } else {
            return true;
        }
    }
    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
