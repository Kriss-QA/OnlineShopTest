package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CataloguePage {
    // создаем локаторы класса CataloguePage
    protected final Page page;
    public final String URL_CATALOGUE_PAGE;
    public final Locator addToCart;
    public final Locator goToCart;

    public CataloguePage(Page page) {
        this.page = page;
        this.URL_CATALOGUE_PAGE = "http://localhost/category.html";
        addToCart = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart"));
        goToCart = page.locator("xpath=//span[@id='numItemsInCart']");
    }
    public void addToCart() {
        page.navigate(URL_CATALOGUE_PAGE);
        page.waitForSelector(".");
        addToCart.click();
        addToCart.click();
        addToCart.click();

        goToCart.click();

    }
    // Проверка на странице корзины суммы товаров
}