import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {
    @BeforeAll
    public static void setUp(){
        String remote = System.getProperty("remote", "http://selenoid:4444/wd/hub");
        Configuration.remote = remote;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities.setCapability("enableVideo", false);
    }
}
