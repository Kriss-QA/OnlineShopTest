package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {
    protected  Page page;
    public Locator itemsPrice;
    public Locator orderSubtotal;



    public void openCartPage(String url) {
        page.navigate(url);
        page.waitForLoadState();
    }

    // Найдем все элементы с классом "item" на странице корзины
    public void sumAllItemsValue() {
        Locator itemsPrice = page.locator("//tr[@class='item']/td[6]");
        double itemsSum = 0;
        for (int i = 0; i < itemsPrice.count(); i++) {
            //цикл, который пройдется по селектору с ценами $0.00

            String currentItemPrice = itemsPrice.nth(i).innerText(); //текущее значение тип строка
            String currentItemPriceWithout$ = currentItemPrice.substring(1); //удаляем $ из строки
            double currentItemPriceDouble = Double.parseDouble(currentItemPriceWithout$);

            itemsSum += currentItemPriceDouble;
        }
        ;
        public void getItemsSum() {
            System.out.println(itemsSum());
        }

        public void SubtotalValue() {
            orderSubtotal = page.locator("th#orderSubtotal");
            String orderSubtotalValue = orderSubtotal.innerText();
            String orderSubtotalValueWithout$ = orderSubtotalValue.substring(1);
            double orderSubtotalValueDouble = Double.parseDouble(orderSubtotalValueWithout$);
        }
        public void getOrderTotal () {
            System.out.println(orderSubtotalValueDouble());
        }
    }
    }


