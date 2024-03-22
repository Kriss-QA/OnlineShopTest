package pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;


public class MainPage {
    public final Page page;
    public final Faker faker = new Faker();
    public final Locator RegisterButton;
    public final Locator usernameField;
    public final Locator firstNameField;
    public final Locator lastNameField;
    public final Locator emailField;
    public final Locator passwordField;
    public final Locator catalogueButton;


    //  cоздаем конструктор, определяем  локаторы
    public MainPage(Page page) {
        this.page = page;

        RegisterButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register"));
        usernameField = page.locator("#register-username-modal");
        firstNameField = page.getByPlaceholder("first name");
        lastNameField = page.getByPlaceholder("last name");
        emailField = page.getByPlaceholder("email");
        passwordField = page.locator("#register-password-modal");

        catalogueButton = page.locator("#tabCatalogue").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Catalogue"));
    }
    @Step("Открытие главной страницы онлайн магазина")
    public void openMainPage() {
        page.navigate("http://localhost");
}
    @Step("Регистрация пользователя")
    public void registrationUser() {

        page.waitForLoadState();
        RegisterButton.click();
        page.waitForLoadState();
        usernameField.fill(faker.name().username());
        firstNameField.fill(faker.name().firstName());
        lastNameField.fill(faker.name().lastName());
        emailField.fill(faker.internet().emailAddress());
        passwordField.fill(faker.internet().password());
        passwordField.press("Enter");
        page.waitForLoadState();

        catalogueButton.click();
        page.waitForLoadState();
    }
}
