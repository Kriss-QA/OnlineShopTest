package tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CataloguePage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(ScreenshotTest)
public class ShopTest extends BaseTest {

    /* Вызываем методы с MainPage открываем url главной страницы и регистрируем пользователя
     */

    @Test
    @Owner("Kristina Vayukova")

    public void authUser() {
        MainPage mainPage = new MainPage(page);
        mainPage.openMainPage();
        mainPage.registrationUser();

        CataloguePage cataloguePage = new CataloguePage(page);
        new CataloguePage(page).addToCart();
        page.waitForLoadState();

        CartPage cartPage = new CartPage(page);
        cartPage.openCartPage();
        page.waitForLoadState();
        cartPage.getItemsSum();
        cartPage.getSubtotal();

        assertEquals(cartPage.sumAllItems(), cartPage.subtotalValue());
    }

    public class AllureScreenshot {
        @Attachment(value = "Screenshot", type = "image/png")
        public static byte[] attachScreenshot(byte[] screenshot) {
            return screenshot;
        }

        public void addScreenShot() {
            CartPage cartPage = new CartPage(page);
            cartPage.openCartPage();
            byte[] screenshot = page.screenshot();
            AllureScreenshot.attachScreenshot(screenshot);
        }
    }
}