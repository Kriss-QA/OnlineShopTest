package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {
    protected static Page page;
    public Locator items;
    public static Locator orderSubtotal;
    public static double itemsSum;
    public static double SubtotalValue;


    public CartPage(Page page) {
        this.page = page;
        items = page.locator("//tr[@class='item']/td[6]");
        orderSubtotal = page.locator("th#orderSubtotal");
    }

    public void openCartPage() {
        page.navigate("http://localhost/basket.html");
    }

    // Найдем все элементы с классом "item" на странице корзины
    public void sumAllItemsValue() {

        for (int i = 0; i < items.count(); i++) {
            //цикл, который пройдется по селектору с ценами $0.00
            String ItemPrice = items.nth(i).innerText(); //текущее значение тип строка
            String ItemPriceWithout$ = ItemPrice.substring(1); //удаляем $ из строки
            double ItemPriceDouble = Double.parseDouble(ItemPriceWithout$);
            itemsSum += ItemPriceDouble;
        };
    }


        public static void SubTotalValue() {
            String orderSubtotalValue = orderSubtotal.innerText();
            String orderSubtotalValueWithout$ = orderSubtotalValue.substring(1);
            double orderSubtotalValueDouble = Double.parseDouble(orderSubtotalValueWithout$);
            SubtotalValue += orderSubtotalValueDouble;
        }
    }



