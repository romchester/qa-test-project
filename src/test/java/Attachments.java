import io.qameta.allure.Attachment;

public class Attachments {
    @Attachment(value = "Скриншот", type = "image/png", fileExtension = "png")
    public static byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }
}
