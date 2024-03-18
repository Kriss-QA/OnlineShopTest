package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.github.javafaker.Faker;


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


    public void openMainPage(String url){

        page.navigate(url);
    }
    //  browser.newPage browser.newPage(new Browser.NewPageOptions().setUrl(""))

    //  cоздаем конструктор, определяем  локаторы
    public MainPage(Page page) {
        this.page = page;

        RegisterButton = page.getByText("Register");
        usernameField = page.getByText("username");
        firstNameField = page.getByText("first name");
        lastNameField = page.getByText("last name");
        emailField = page.getByText("email");
        passwordField = page.locator("#password-modal");
        RegistrationButton = page.locator(".fa fa-sign-in");

        catalogueButton = page.getByText("CATALOGUE");

        // logInButton = page.getByText("Log in");
    }


/* String username1=faker.name().username();
String password1=faker.internet().password();

    public String generateRandomUsername() {
           return faker.name().username();
       }
    public String generateRandomPassword() {
           return faker.internet().password();
    } */


    //  регистрация пользователя заполняем поля
    public void registrationUser() {

        RegisterButton.click();
        usernameField.fill(faker.name().username());
        firstNameField.fill(faker.name().firstName());
        lastNameField.fill(faker.name().lastName());
        emailField.fill(faker.internet().emailAddress());
        passwordField.fill(faker.internet().password());

        RegistrationButton.click();
    }
    //  переход на страницу каталога
        public void catalogueButton() {
        catalogueButton.click();

    }
}
