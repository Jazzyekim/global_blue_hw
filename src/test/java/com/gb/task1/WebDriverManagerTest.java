package com.gb.task1;

import lombok.extern.slf4j.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class WebDriverManagerTest {

    private final WebDriverManager manager = WebDriverManager.getInstance();
    private final ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        driverLocal.set(manager.getDriver(browser));
    }

    @AfterMethod
    public void tearDown() {
        manager.quitDriver();
    }

    @Test(testName = "Open google.com")
    public void testOpenGooglePage() {
        WebDriver driver = openPage("https://google.com");
        assertThat(driver.getTitle()).contains("Google");
    }

    @Test(testName = "Open github.com" )
    public void testOpenGitHubPage() {
        WebDriver driver = openPage("https://github.com/");
        assertThat(driver.getTitle()).contains("GitHub");
    }

    private WebDriver openPage(String url) {
        log.info("[Thread: {}] Open page: {}", Thread.currentThread().getId(), url);
        WebDriver driver = driverLocal.get();
        driver.get(url);
        return driver;
    }

}
