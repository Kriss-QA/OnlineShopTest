package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class CartPage {
    protected static Page page;
    public Locator items;
    public static Locator orderSubtotal;
    public static double SubtotalValue;


    public CartPage(Page page) {
        this.page = page;
        items = page.locator("//tr[@class='item']/td[6]");
        orderSubtotal = page.locator("th#orderSubtotal");
    }
    @Step("Открываем страницу корзины")
    public void openCartPage() {
        page.navigate("http://localhost/basket.html");
    }

    @Step("Находим все элементы с классом 'item' на странице корзины. Достаем значения стоимости товара. Цена указана в формате $0.00 - строка, приводим значение к типу дабл")
    public double sumAllItems() {
        double ItemsPriceValue = 0.0;
        for (int i = 0; i < items.count(); i++) {

            String ItemPriceString = items.nth(i).innerText().substring(1);//текущее значение тип строка
            double ItemPrice = Double.parseDouble(ItemPriceString);
            ItemsPriceValue+=ItemPrice;
        };
        return ItemsPriceValue;
     }

    @Step("Выводим числовое значение суммы всех товаров")
    public void getItemsSum() {
        System.out.println(sumAllItems());
       }

    @Step("Достаем из локатора значение общей стримости товаров. Цена указана в формате $0.00 - строка, приводим значение к типу дабл")
    public double subtotalValue() {
        double orderSubtotalValue = 0.0;
        String orderSubotalString = orderSubtotal.innerText().substring(1);
        double orderSubtotal = Double.parseDouble(orderSubotalString);
        orderSubtotalValue+=orderSubtotal;
        return orderSubtotalValue;
    }

    @Step("Выводим числовое значение общей стоимости товаров")
    public void getSubtotal() {
        System.out.println(subtotalValue());
       }
    }



