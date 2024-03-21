package tests;

import pages.CartPage;
import pages.MainPage;
import pages.CataloguePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShopTest extends BaseTest {

    /**
     * Вызываем методы с MainPage открываем url главной страницы и регистрируем пользователя
      */

    @Test
    public void authUser(){
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
}