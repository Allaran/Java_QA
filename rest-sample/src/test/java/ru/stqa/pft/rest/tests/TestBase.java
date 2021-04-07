package ru.stqa.pft.rest.tests;


import com.google.gson.JsonElement;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.stqa.pft.rest.appmanager.IssueHelper;
import ru.stqa.pft.rest.model.Issue;

import org.hibernate.service.spi.ServiceException;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class TestBase {

    public boolean isIssueOpen(int issueId) throws IOException, ServiceException {
        String json = IssueHelper.getExecutor()
                .execute(Request.Get("https://bugify.stqa.ru/api/" + String.format("issues/%s.json", issueId)))
                .returnContent()
                .asString();
        JsonElement parsed = parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement line = issues.getAsJsonObject().get(0);
        if (line.getAsJsonObject().get("state_name").getAsString().equals("Resolved") ||
                line.getAsJsonObject().get("state_name").getAsString().equals("Closed")) {
            return false;
        } else {
            return true;
        }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
