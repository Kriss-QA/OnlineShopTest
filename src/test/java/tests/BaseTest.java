package tests;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

/*позволяет создать один экземпляр тестового класса и повторно использовать между тестами
позволят работать со статическими методами
@TestInstance(TestInstance.Lifecycle.PER_CLASS)*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public abstract class BaseTest {
    private Playwright playwright;
    private Browser browser;
    protected Page page;
    public BrowserContext context;
    Faker faker;

    /*@BeforeClass
    public void setUp() {
        browser = Playwright.create().chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));

        context = browser.newContext();
        faker = new Faker();
    }*/

    @BeforeAll
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        faker = new Faker();

    }
    /*@AfterClass
    public void tearDown() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
    }*/

    @AfterAll
    public void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
        public void closeContext() {
            context.close();
        }
}


    /* У Playwright есть концепция BrowserContext, которая представляет собой изолированный в памяти
    профиль браузера. Рекомендуется создавать новый BrowserContext для каждого теста, чтобы
    они не мешали друг другу. */
    /*@BeforeEach
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void closeContext() {
        context.close(); //закрываем браузер
    }
}*/