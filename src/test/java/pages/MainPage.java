package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.github.javafaker.Faker;
import com.microsoft.playwright.options.AriaRole;


public class MainPage {
    protected final Page page;
    public final Faker faker = new Faker();
    public final Locator RegisterButton;
    public final Locator usernameField;
    public final Locator firstNameField;
    public final Locator lastNameField;
    public final Locator emailField;
    public final Locator passwordField;
    public final Locator RegistrationButton;
    public final Locator catalogueButton;


    //  cоздаем конструктор, определяем  локаторы
    public MainPage(Page page) {
        this.page = page;

        RegisterButton = page.getByText("Register");
        usernameField = page.locator("#register-username-modal");
        firstNameField = page.getByText("first name");
        lastNameField = page.getByText("last name");
        emailField = page.getByText("email");
        passwordField = page.locator("#register-password-modal");
        RegistrationButton = page.locator("#tabRegister").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Register"));

        catalogueButton = page.locator("#tabCatalogue").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Catalogue"));
    }

public void openMainPage() {

    page.navigate("http://localhost/");
}

    //  регистрация пользователя заполняем поля
    public void registrationUser() {

        page.waitForLoadState();
        RegisterButton.click();
        page.waitForLoadState();
        usernameField.fill(faker.name().username());
        firstNameField.fill(faker.name().firstName());
        lastNameField.fill(faker.name().lastName());
        emailField.fill(faker.internet().emailAddress());
        passwordField.fill(faker.internet().password());
        RegistrationButton.click();
        page.waitForLoadState();

        catalogueButton.click();
        page.waitForLoadState();
    }
}
