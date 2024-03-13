import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

public class SingInPage extends Base {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;
    private static Faker faker;

    @BeforeAll
    public static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        context = browser.newContext();
        page = context.newPage();
        faker = new Faker();
}
    @Test
    public void testUserLogin() {
        page.navigate("https://.com");
        page.fill("input[name='username']", "your_username");
        page.fill("input[name='password']", "your_password");
        page.click("button[type='submit']");

        page.waitForSelector(".user-welcome-message"); // Проверка что после входа отображается имя пользователя
        }
        @AfterAll
        public static void tearDown() {
            playwright.close();
        }
    }