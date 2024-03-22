package tests;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotTest implements TestWatcher {
    private final Page page;

    public ScreenshotTest(Page page) {
        this.page = page;
    }

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
}