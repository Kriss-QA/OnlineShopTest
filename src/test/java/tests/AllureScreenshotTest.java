package tests;

//import java.io.ByteArrayOutputStream;
//import java.nio.file.Paths;


/*public class AllureScreenshotTest implements TestWatcher {
    private final Page page;

    public AllureScreenshotTest(Page page) {
        this.page = page;
    }
@Override
    public void testSuccessful(ExtensionContext context) {
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