package tests;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/*позволяет создать один экземпляр тестового класса и повторно использовать между тестами
позволят работать со статическими методами */

public abstract class BaseTest {
    Browser browser;
    Page page;
    BrowserContext context;

    Faker faker;

    @BeforeClass
    public void setUp() {
        browser = Playwright.create().chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));

        faker = new Faker();
        context = browser.newContext();
    }

    @AfterClass
    public void tearDown() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
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