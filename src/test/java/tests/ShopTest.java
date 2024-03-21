package tests;

import pages.CartPage;
import pages.MainPage;
import pages.CataloguePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShopTest extends BaseTest {
    public final static String URL_MAIN_PAGE = "http://localhost/";
    public final static String URL_CART_PAGE = "http://localhost/basket/";


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

        CartPage cartPage = new CartPage(page);
        cartPage.openCartPage(URL_CART_PAGE);
        page.waitForLoadState();

        assertEquals(CartPage.itemsSum, CartPage.SubtotalValue);
    }
}