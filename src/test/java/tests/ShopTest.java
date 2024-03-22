package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import pages.CartPage;
import pages.MainPage;
import pages.CataloguePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShopTest extends BaseTest {

    /* Вызываем методы с MainPage открываем url главной страницы и регистрируем пользователя
      */

    @Test
    @Owner("Kristiva Vayukova")
    @Description("Выполняется регистрация пользователя на главной странице онлайн магазина")
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