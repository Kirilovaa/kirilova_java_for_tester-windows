package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.appmanager.RestHelper;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak","config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        if (issue.getResolution().getName() == "open") {
            return true;
        } else if (issue.getResolution().getName() == "reopened") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isIssueOpenRest(int issueId) throws IOException {
        String json= RestHelper.getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/"+issueId+".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement  issuesJ=parsed.getAsJsonObject().get("issues");
        Set<Issue> issues = new Gson().fromJson( issuesJ, new TypeToken<Set<Issue>>(){}.getType());
        Issue issue = issues.iterator().next();
        System.out.println("???????????? ????????????: "+issue.getState_name());
        if (issue.getState_name().equals("Resolved")|issue.getState_name().equals("Closed")) {
            return false;
        } else {return true;}
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
    public void skipIfNotFixedRest(int issueId) throws IOException {
        if (isIssueOpenRest(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
