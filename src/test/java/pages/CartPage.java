package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

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

    public void openCartPage() {
        page.navigate("http://localhost/basket.html");
    }

    // Найдем все элементы с классом "item" на странице корзины
    public double sumAllItems() {
        double ItemsPriceValue = 0.0;
        for (int i = 0; i < items.count(); i++) {
            //цикл, который пройдется по селектору с ценами $0.00
            String ItemPriceString = items.nth(i).innerText().substring(1);//текущее значение тип строка
            double ItemPrice = Double.parseDouble(ItemPriceString);
            ItemsPriceValue+=ItemPrice;
        };
        return ItemsPriceValue;
     }
  

    //String ItemPriceWithout$ = ItemPrice.substring(1); //удаляем $ из строки
    //            double ItemPriceDouble = Double.parseDouble(ItemPriceWithout$);

    public void getItemsSum() {
        System.out.println(sumAllItems());
       }
    
    public double subtotalValue() {
        double orderSubtotalValue = 0.0;
        String orderSubotalString = orderSubtotal.innerText().substring(1);
        double orderSubtotal = Double.parseDouble(orderSubotalString);
        orderSubtotalValue+=orderSubtotal;
        return orderSubtotalValue;
    }
    public void getSubtotal() {
        System.out.println(subtotalValue());
       }
    }


// String orderSubtotalValueWithout$ = orderSubtotalValue.substring(1);
//            double orderSubtotalValueDouble = Double.parseDouble(orderSubtotalValueWithout$);


