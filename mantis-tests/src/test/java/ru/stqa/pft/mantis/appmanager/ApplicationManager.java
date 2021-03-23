package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.setProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resouces/%s.properties", target))));


        if (browser.equals(BrowserType.FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver-v0.29.0-win64\\geckodriver.exe");
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32\\chromedriver.exe");
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            System.setProperty("webdriver.ie.driver", "C:\\drivers\\IEDriverServer_Win32_3.150.1\\chromedriver.exe");
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wd.quit();
    }
}
