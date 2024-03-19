package Pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.intellij.lang.annotations.Pattern;

import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {
    protected Page page;
    public Locator itemPrice;
    public Locator totalPrice;
    public Locator orderSubtotal;
    public Locator shippingPrice;
    public Locator taxPrice;
    public int itemsSum = 0;

    public void openCartPage(String url) {
        page.navigate(url);

        itemPrice = page.locator(".item");
        orderSubtotal = page.locator("#orderSubtotal");
        shippingPrice = page.locator("#orderShipping");
        taxPrice = page.locator("#orderTax");
        totalPrice = page.locator("#cartTotal");
    }

    // Найдем все элементы с классом "item" на странице корзины
    public void sumAllItems() {
        List <Locator> itemPrice = page.querySelectorAll(".item");
        for (Locator element : itemPrice) ;
    }
    {
        // Получим текстовое содержимое каждого элемента и преобразуем его в число
        int value = Integer.parseInt(itemPrice.textContent());

        // Добавим значение к общей сумме
        itemsSum = itemsSum + value;
    }

    public void totalPrice() {

        String orderSubtotal1 = orderSubtotal.innerText();
        String shippingPrice1 = shippingPrice.innerText();
        String taxPrice1 = taxPrice.innerText();
        String totalPrice1 = totalPrice.innerText();

        // извлечения числа из текста через регулярное выражение
        double orderSubtotalValue = extractValue(orderSubtotal1);
        double shippingPriceValue = extractValue(shippingPrice1);
        double taxPriceValue = extractValue(taxPrice1);
        double totalPriceValue = extractValue(totalPrice1);

        double sumValue = orderSubtotalValue + shippingPriceValue + taxPriceValue;

        assertEquals(itemsSum, orderSubtotalValue);

        assertEquals(totalPriceValue, sumValue);
    }
    private double extractValue(String text) {
        Pattern pattern = Pattern.compile("\\$([\\d.]+)");
        Matcher matcher = pattern.value(String text);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            return 0.0;
        }
    }
    }


    /* expect(locator).toHaveValue()  - проверка что элемент
    имеет такое значение */