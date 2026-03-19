import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginTest {

    private static final String LOGIN_PAGE_URL = "https://the-internet.herokuapp.com/login";

    @Test
    public void successfulLoginTest() {
        open(LOGIN_PAGE_URL);
        $("#username").setValue("tomsmith");
        $("#password").setValue("SuperSecretPassword!");
        $(".radius").click();
        $(".flash.success").shouldHave(text("You logged into a secure area!"));
    }

    @Test
    void invalidPasswordTest() {
        open(LOGIN_PAGE_URL);
        $("#username").setValue("tomsmith");
        $("#password").setValue("wrongpassword");
        $(".radius").click();
        $(".flash.error").shouldHave(text("Your password is invalid!"));
    }

    @Test
    void loginPageElementsTest() {
        open(LOGIN_PAGE_URL);
        $("#username").shouldBe(visible);
        $("#password").shouldBe(visible);
        $(".radius").shouldBe(visible, enabled);
    }
}
