package tests.Android;

import org.testng.annotations.Test;
import tests.ConfigAppium;

/** 상품상세 **/
public class Product extends ConfigAppium {

    @Test
    public void C265(){
        productPage.checkFirstItem();
        productPage.clickMagnifier(); /** 예외처리 필요!! **/
    }

    @Test
    public void C266(){
        productPage.checkBrandName();
    }

    @Test
    public void C267(){
        productPage.checkRating(); /** 예외처리 필요!! **/
    }

    @Test
    public void C268(){
        productPage.putComparison();
    }

    @Test
    public void C269(){
        productPage.checkRanking();
    }

    @Test
    public void C270(){
        productPage.checkSeller();
    }

    @Test
    public void C271(){
        productPage.clickQnA();
    }

    @Test
    public void C272(){
        productPage.clickGoodsNotify();
    }

    @Test
    public void C273(){
        productPage.clickReturnGuide();
    }

    @Test
    public void C274(){
        productPage.clickPartnerInfo();
    }

    @Test
    public void C275(){
        productPage.WeeklyBest10();
    }

    @Test
    public void C276(){
        productPage.Event();
    }

    @Test
    public void C277(){
        productPage.HotProduct();
    }

    @Test
    public void C278(){
        productPage.similarProduct();
    }

    @Test
    public void C279(){
        productPage.discountProduct();
    }

    @Test
    public void C280(){
        productPage.HotEvent();
    }
    @Test
    public void C281(){
        productPage.clickToText("리뷰/성분");
        productPage.clickReviewTopic();
    }

    @Test
    public void C282(){
        productPage.clickIngredient();
    }

    @Test
    public void C283() {
        productPage.reviewImage();
    }

    @Test
    public void C284(){
        productPage.writeGoodReview();
    }

    @Test
    public void C285(){
        productPage.writeBadReview();
    }

    @Test
    public void C286(){
        productPage.modifyReview();
    }

    @Test
    public void C287(){
        productPage.modifyReviewInMy();
    }

    @Test
    public void C288(){
        productPage.deleteReview();
    }

}
