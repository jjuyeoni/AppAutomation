package tests.Android;

import org.testng.annotations.Test;
import tests.ConfigAppium;

public class Sample extends ConfigAppium {

    @Test
    public void C295(){
        samplePage.clickToText("샘플 체험");
        samplePage.sampleDetail();

    }

    @Test
    public void C296(){
        samplePage.clickCartBtn();
    }

    @Test
    public void C297(){
        samplePage.checkInCart();
    }

    @Test
    public void C298(){
        samplePage.checkPayment();
    }
}
