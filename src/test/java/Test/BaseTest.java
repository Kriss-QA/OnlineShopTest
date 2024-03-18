package Test;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

/*позволяет создать один экземпляр тестового класса и повторно использовать между тестами
позволят работать со статическими методами */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    Faker faker;
    @BeforeAll
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                            .launch(new BrowserType   //устанавлтваем и открываем браузер, без окна
                            .LaunchOptions()
                            .setHeadless(false));
        faker = new Faker();
    }

    @AfterAll
    public void closeBrowser() {
        playwright.close(); //закрываем браузер
    }

    /* У Playwright есть концепция BrowserContext, которая представляет собой изолированный в памяти
    профиль браузера. Рекомендуется создавать новый BrowserContext для каждого теста, чтобы
    они не мешали друг другу. */
    @BeforeEach
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void closeContext() {
        context.close(); //закрываем браузер
    }
}