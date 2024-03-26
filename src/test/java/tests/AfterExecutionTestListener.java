package tests;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class AfterExecutionTestListener implements AfterTestExecutionCallback {
    //интерфейс, позволяющий определять пользовательские действия после каждого тестового метода
    @Override // переопределение метода
    public void afterTestExecution(ExtensionContext context) {
        // Вызывается сразу после выполнения каждого теста. принимает контекст в как параметр

        makeScreenshot(context);

    }

    @Attachment(value = "screen", type = "image/png", fileExtension = ".png")
    @SuppressWarnings("UnusedReturnValue") //Эта аннотация указывает компилятору Java на то, что предупреждение о неиспользуемом возвращаемом значении для этого метода можно проигнорировать.
    private byte[] makeScreenshot(ExtensionContext context) {
        String dir = "./screenshots/"; //место для сохранения скриншотов
        String timestampAsString = ZonedDateTime.now(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("dd_MM_HH_mm_ss")); //текущее время в виде строки с заданным форматом
        String nameTestMethod = context.getTestMethod().orElseThrow().getName(); //имя тестового метода
        return getCurrentPage(context).screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(dir + timestampAsString + "_" + nameTestMethod + ".png"))
                .setFullPage(true));
    }

    private Page getCurrentPage(ExtensionContext context) {
        Object instance = context.getRequiredTestInstance();
        try {
            BrowserContext testContext = (BrowserContext) instance.getClass().getSuperclass()
                    .getDeclaredField("context").get(instance);
            List<Page> testPages = testContext.pages(); //Cписок страниц, доступных в контексте браузера
            return testContext.pages().get(testPages.size() -1); //Возвращается последняя страница из списка страниц контекста браузера
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}


