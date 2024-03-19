package Test;

import Pages.CartPage;
import Pages.MainPage;
import Pages.CataloguePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopTest extends BaseTest {
    public final static String URL_MAIN_PAGE = "http://localhost.html";
    public final static String URL_CATALOGUE_PAGE = "http://localhost/category.html";
    public final static String URL_CART_PAGE = "http://localhost/basket.html";

    /*@Test
    public void newTest() {
        assertTrue(true);
    }*/

    /**
     * Вызываем методы с MainPage открываем url главной страницы и регистрируем пользователя
      */

    @Test
    public void authUser(){
        MainPage mainPage = new MainPage(page);
        mainPage.openMainPage(URL_MAIN_PAGE);
        new MainPage(page).registrationUser();
        new MainPage(page).catalogueButton();
        new CataloguePage(page).addToCart();
        page.waitForLoadState();

        Assert.assertEquals(CartPage.getItemsSum(), CartPage.getSubTotalValue());
    }
}