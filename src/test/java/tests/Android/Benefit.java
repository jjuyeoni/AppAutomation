package tests.Android;

import org.testng.annotations.Test;
import tests.ConfigAppium;

public class Benefit extends ConfigAppium {

    @Test
    public void C219() {
        benefitPage.clickToText("혜택");
        benefitPage.todayWeather();
        benefitPage.pressBackButton();
    }

    @Test
    public void C220() {
        benefitPage.attendanceCheck();
        benefitPage.pressBackButton();
    }
}
