package utils;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
public class ResourcesUtil {
    private static ResourceBundle resources = ResourceBundle.getBundle("strings", new UTF8Control());

    public static String getString(String key){
        return resources.getString(key);
    }

    public static String getTextXpath(String key) throws UnsupportedEncodingException{
        return String.format("//*[@text='%s'])", getString(key));
    }

    public static String getContainsLabelXpath(String key) throws UnsupportedEncodingException{
        return String.format("//*[contains(@label, '%s')]", getString(key));
    }

    public static String getButtonNameXpath(String key) throws UnsupportedEncodingException{
        return String.format("//XCUIElementTypeButton[@name='%s']", getString(key));
    }
}
