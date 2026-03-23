import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $(".radius");
    private final SelenideElement flashMessage = $(".flash");

    public SelenideElement getUsernameInput() {
        return usernameInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public void enterUsername(String username) {
        usernameInput.setValue(username);
    }

    public void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public SecureAreaPage loginSuccess(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new SecureAreaPage();
    }

    public String getFlashMessageText() {
        return flashMessage.getText();
    }

    public SelenideElement getFlashMessage() {
        return flashMessage;
    }
}
