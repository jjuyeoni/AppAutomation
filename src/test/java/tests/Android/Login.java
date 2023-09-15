package tests.Android;

import org.testng.annotations.Test;
import tests.ConfigAppium;


public class Login extends ConfigAppium {

    @Test
    public void C185(){
        // 앱 실행 케이스
    }
    @Test
    public void C186() {
        loginPage.checkEmailSignIn();
    }
    @Test
    public void C187() {
        loginPage.clickEmailSignIn();
        loginPage.setIdField("qa3@birdview.kr");
        loginPage.setPasswordField("birdviewqa1!");
        homePage.chkBanner();
    }

    public void kakaoSignIn(){
        loginPage.clickKakaoSignIn();
        if(loginPage.AccountCheck()==false){
            homePage.chkBanner();
            loginPage.Logout();
        }

    }

    public void NaverSignIn(){
        loginPage.clickNaverSignIn();
        if(loginPage.AccountCheck()==false){
            homePage.chkBanner();
            loginPage.Logout();
        }
    }

    public void GoogleSignIn() {
        loginPage.clickGoogleSignIn();
        if(loginPage.AccountCheck()==false){
            homePage.chkBanner();
            loginPage.Logout();
        }

    }

    public void FbSignIn(){
        loginPage.clickFbSignIn();
        if(loginPage.AccountCheck()==false){
            homePage.chkBanner();
            loginPage.Logout();
        }

    }


}
