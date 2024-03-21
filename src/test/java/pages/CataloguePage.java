package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class CataloguePage {
    // создаем локаторы для класса CataloguePage
    protected final Page page;
    public final String URL_CATALOGUE_PAGE;
    public final Locator ShowingNineItems;
    public final Locator addToCartItem1;
    public final Locator addToCartItem2;
    public final Locator addToCartItem3;


    public CataloguePage(Page page) {
        this.page = page;
        this.URL_CATALOGUE_PAGE = "http://localhost/category.html";
        ShowingNineItems = page.locator("//div[@id='products-number']/a[3]");

        addToCartItem1 = page.locator("//a[text()='Colourful']/ancestor::div[@class='product']/descendant::a[text()='Add to cart']");
        addToCartItem2 = page.locator("//a[text()='Figueroa']/ancestor::div[@class='product']/descendant::a[text()='Add to cart']");
        addToCartItem3 = page.locator("//a[text()='YouTube.sock']/ancestor::div[@class='product']/descendant::a[text()='Add to cart']");

    }
    public void addToCart() {
        page.navigate(URL_CATALOGUE_PAGE);

        ShowingNineItems.click();
        addToCartItem1.click();
        page.waitForLoadState();
        addToCartItem2.click();
        page.waitForLoadState();
        addToCartItem3.click();
        page.waitForLoadState();
    }
}