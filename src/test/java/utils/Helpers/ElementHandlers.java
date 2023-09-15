package utils.Helpers;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import tests.ConfigAppium;

import java.time.Duration;

public class ElementHandlers extends ConfigAppium {

    public static final int TIME_OUT_IN_SECONDS = 30;

    public ElementHandlers(AndroidDriver androidDriver){
        this.androidDriver = androidDriver;
    }


    //Long Click Gesture (java script event를 이용)
    public void longPressAction(WebElement ele){
        ((JavascriptExecutor)androidDriver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),
                        "duration", 2000));
    }

    public void scrollToText(String text){
        androidDriver.findElement(AppiumBy.androidUIAutomator
                ("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }

    public void swipeAction(WebElement ele, String direction) {
        ((JavascriptExecutor) androidDriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)ele).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }
    public void scrollToEndAction(){
        //쬐끔씩 스크롤 원할 때 -> do while로 페이지 끝까지 갈 수 있음. 빼면 지정한 범위만큼만 스크롤 반복
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while (canScrollMore);
    }

    public void pressEnterKey(){
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void pressBackKey(){
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    // Touch 액션으로 swipe 하기
    public void swipeByTouchActions(AndroidDriver androidDriver, WebElement element, String direction, double percent) {
        int startX = element.getLocation().getX();
        int startY = element.getLocation().getY();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        int swipeStartX, swipeEndX, swipeStartY, swipeEndY;

        // 스와이프 방향에 따라 시작점과 끝점 좌표 계산
        switch (direction.toLowerCase()) {
            case "left":
                swipeStartX = startX + (int) (width * (1 - percent));
                swipeEndX = startX + (int) (width * percent);
                swipeStartY = startY + height / 2;
                swipeEndY = startY + height / 2;
                break;
            case "right":
                swipeStartX = startX + (int) (width * percent);
                swipeEndX = startX + (int) (width * (1 - percent));
                swipeStartY = startY + height / 2;
                swipeEndY = startY + height / 2;
                break;
            case "up":
                swipeStartX = startX + width / 2;
                swipeEndX = startX + width / 2;
                swipeStartY = startY + (int) (height * (1 - percent));
                swipeEndY = startY + (int) (height * percent);
                break;
            case "down":
                swipeStartX = startX + width / 2;
                swipeEndX = startX + width / 2;
                swipeStartY = startY + (int) (height * percent);
                swipeEndY = startY + (int) (height * (1 - percent));
                break;
            default:
                throw new IllegalArgumentException("Invalid direction. Supported directions are: left, right, up, down.");
        }

        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.press(PointOption.point(swipeStartX, swipeStartY))
                .waitAction()
                .moveTo(PointOption.point(swipeEndX, swipeEndY))
                .release()
                .perform();
    }

    public void scrollToId(String targetId) {
        while (!isIdisible(targetId)) {
            scrollDown();
        }
    }

    private boolean isIdisible(String targetId) {
        try {
            return androidDriver.findElement(By.id(targetId)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    private void scrollDown() {
        Dimension size = androidDriver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.7);
        int endY = (int) (size.height * 0.1);

        androidDriver.performTouchAction(
                new io.appium.java_client.TouchAction(androidDriver)
                        .press(io.appium.java_client.touch.offset.PointOption.point(startX, startY))
                        .waitAction(io.appium.java_client.touch.WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(io.appium.java_client.touch.offset.PointOption.point(startX, endY))
                        .release()
        );
    }

}
