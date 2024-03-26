package tests;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) /*позволяет создать один экземпляр тестового класса
и повторно использовать между тестами. Позволят работать со статическими методами*/
@ExtendWith(AfterExecutionTestListener.class)
public abstract class BaseTest {
    private Playwright playwright;
    private Browser browser;
    protected Page page;
    public BrowserContext context;
    Faker faker;
    //private Boolean isTraceEnabled = false;

    @BeforeAll
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));

        faker = new Faker();
    }

    @BeforeEach
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterAll
    public void closeBrowser() {
        playwright.close();
    }
}


    /* У Playwright есть концепция BrowserContext, которая представляет собой изолированный в памяти
    профиль браузера. Рекомендуется создавать новый BrowserContext для каждого теста, чтобы
    они не мешали друг другу. */
