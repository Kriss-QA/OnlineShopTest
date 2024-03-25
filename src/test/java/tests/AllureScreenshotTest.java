package tests;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

class AllureScreenshotListener extends TestListenerAdapter {

    private final Page page;

    public AllureScreenshotListener(Page page) {
        this.page = page;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result.getName());
    }

    private void takeScreenshot(String testName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte[] screenshot = page.screenshot();
            outputStream.write(screenshot);
            Allure.addAttachment(testName + " - Screenshot", new ByteArrayInputStream(outputStream.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}










/*public class AllureScreenshotTest implements TestWatcher {
    private final Page page;

    public AllureScreenshotTest(Page page) {
        this.page = page;
    }

    //@Attachment вложение — это просто метод, который возвращает строку или байт[]


    @Override
    public void testSuccessful(ExtensionContext context) {
        takeScreenshot(context.getDisplayName()); //вернем объект
    }

    private void takeScreenshot(String testName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte[] screenshot = page.screenshot();
            outputStream.write(screenshot);
            Allure.addAttachment(testName + " - Screenshot", new ByteArrayInputStream(outputStream.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/



/*
    // Создаем поток для записи данных скриншота
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // Сохраняем скриншот на диск
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));

    // Читаем данные скриншота в массив байтов
    byte[] screenshotBytes = outputStream.toByteArray();


    // Добавляем скриншот в отчет Allure
    Allure.getLifecycle().addAttachment(
            "Screenshot", "image/png", "png", screenshotBytes);
 }
}






    /* import java.io.File;
    import java.nio.file.Files;
    import java.nio.file.Paths;

    @Override

    public void testSuccessful(ExtensionContext context) {
        try {
            String screenshotPath = "screenshot.png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
            byte[] screenshotBytes = Files.readAllBytes(new File(screenshotPath).toPath());
            Files.deleteIfExists(new File(screenshotPath).toPath());
            Allure.getLifecycle().addAttachment(
                    "screenshot", "img/png", "png", screenshotBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            // Сохраняем скриншот на диск
            String screenshotPath = "screenshot.png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

            // Читаем скриншот в виде массива байтов
            byte[] screenshotBytes = Files.readAllBytes(new File(screenshotPath).toPath());

            // Удаляем сохраненный скриншот с диска
            Files.deleteIfExists(new File(screenshotPath).toPath());

            // Добавляем скриншот в отчет Allure
            Allure.getLifecycle().addAttachment(
                    "screenshot", "img/png", "png", screenshotBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/