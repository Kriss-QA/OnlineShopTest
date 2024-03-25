package tests;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) /*позволяет создать один экземпляр тестового класса
и повторно использовать между тестами. Позволят работать со статическими методами*/

public abstract class BaseTest {
    private Playwright playwright;
    private Browser browser;
    protected Page page;
    public BrowserContext context;
    Faker faker;
    private Boolean isTraceEnabled = false;

    @BeforeAll
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        faker = new Faker();
    }

    @BeforeEach
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
   public void attachFilesToFailedTest(ITestResult result) throws IOException {
      if (!result.isSuccess()) {
        String uuid = UUID.randomUUID().toString();
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
            .setPath(Paths.get("build/allure-results/screenshot_" + uuid + "screenshot.png"))
            .setFullPage(true));

        Allure.addAttachment(uuid, new ByteArrayInputStream(screenshot));
        Allure.addAttachment("source.html", "text/html,", page.content());

        if (isTraceEnabled) {
            String traceFileName = String.format("build/%s_trace.zip", uuid);
            Path tracePath = Paths.get(traceFileName);
            context.tracing()
                    .stop(new Tracing.StopOptions()
                            .setPath(tracePath));
            Allure.addAttachment("trace.zip", new ByteArrayInputStream(Files.readAllBytes(tracePath)));
        }
      }
}
    @AfterAll
    public void closeBrowser() {
        playwright.close();
    }
}


    /* У Playwright есть концепция BrowserContext, которая представляет собой изолированный в памяти
    профиль браузера. Рекомендуется создавать новый BrowserContext для каждого теста, чтобы
    они не мешали друг другу. */
