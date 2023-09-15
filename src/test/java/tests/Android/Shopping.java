package tests.Android;

import org.testng.annotations.Test;
import pageObjects.Android.ShoppingPage;
import tests.ConfigAppium;

public class Shopping extends ConfigAppium {

    @Test
    public void C221() {
        shoppingPage.clickToText("화해쇼핑");

        shoppingPage.clickToText("화해배송");

        // 230906 only 화해 탭으로 이동되는 스펙으로 반영
        shoppingPage.checkSelectedTab("ONLY화해");

        shoppingPage.clickToText("화해쇼핑");
        shoppingPage.checkSelectedTab("쇼핑홈");
    }

    @Test
    public void C222() {
        shoppingPage.clickToText("온리화해");
        shoppingPage.cmpProductByPrice("shop");

        shoppingPage.pressBackButton();
    }

    @Test
    public void C223() {
        shoppingPage.clickToText("한정특가");
        shoppingPage.cmpProductByPrice("shop");

        shoppingPage.pressBackButton();
    }

    @Test
    public void C224() {
        shoppingPage.clickToText("전체");
        shoppingPage.clickToText("스킨케어");
        shoppingPage.clickToText("스킨/토너");
        shoppingPage.cmpProductByPrice("shop");

        shoppingPage.pressBackButton();
    }

    @Test
    public void C225() {
        shoppingPage.ongoingOffer();
        shoppingPage.cmpProductByPrice("offer");
    }

    @Test
    public void C226() {
        shoppingPage.upcomingOffer();
    }

}
