import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SecureAreaPage {
    private SelenideElement successMessage = $(".flash.success");

    public void verifySuccessMessage(String expectedText) {
        successMessage.shouldHave(text(expectedText));
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }
}
