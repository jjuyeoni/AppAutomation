package tests.Android;

import org.testng.annotations.Test;
import tests.ConfigAppium;

public class Detail extends ConfigAppium {
    @Test
    public void C289() {
        detailPage.clickToText("홈");
        detailPage.searchProduct();
    }
    @Test
    public void C290() {
        detailPage.requireModify();
    }
    @Test
    public void C291() {
        detailPage.putComparison();
    }
    @Test
    public void C292() {
        detailPage.ComparisonBox();
    }
    @Test
    public void C293() {
        detailPage.switchFilter();
    }
    @Test
    public void C294() {
        detailPage.clickFilter();
    }

}
