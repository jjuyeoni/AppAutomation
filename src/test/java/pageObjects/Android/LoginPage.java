package pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    AndroidDriver androidDriver;
    public LoginPage(AndroidDriver androidDriver){
        this.androidDriver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public void Logout(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/button_layout_3")).click();
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/iv_mypage_setting_button")).click();
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[7]")).click();
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[2]")).click();
    }

    /** 다른 sns로 가입 여부 확인 **/
    public boolean AccountCheck(){
        try {
            androidDriver.findElement(By.id("android:id/button2")).click(); // 아니요 클릭
            return true;
        }catch (Exception e) {
            System.out.println("No alert found.");
            return false;
        }
    }

    public void checkEmailSignIn(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/email_sign_up")).click();
        String title  = androidDriver.findElement(By.id("kr.co.company.hwahae:id/header_title")).getText();
        Assert.assertTrue(title.contains("기본정보를 입력해주세요"));

        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.ImageView")).click();
        String text = androidDriver.findElement(By.id("kr.co.company.hwahae:id/email_sign_in")).getText();
        Assert.assertEquals(text, "이메일로 로그인");
    }
    public void clickEmailSignIn(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/email_sign_in")).click();
    }

    public void clickKakaoSignIn(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/kakao_start")).click();
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")).click();
    }

    public void clickNaverSignIn() {
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/naver_start")).click();
        androidDriver.findElement(By.xpath("//android.widget.CheckBox[@text='전체 동의하기']")).click();

        // text 동의하기
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.Button")).click();
    }

    public void clickGoogleSignIn(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/google_start")).click();
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]")).click();
    }

    public void clickFbSignIn(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/facebook_start")).click();
        androidDriver.findElement(By.xpath("//android.widget.Button[1]")).click();

    }

    /** 이메일 로그인 id, pwd 입력 **/
    public void setIdField(String id){
        androidDriver.findElement(By.xpath("//android.widget.EditText[1]")).sendKeys(id);
    }

    public void setPasswordField(String password){
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='비밀번호']")).sendKeys(password);
        clickEmailSignIn();
    }

    /** 카카오톡 아이디 입력 필요할때 **/
    public void setKakaoField(String id, String password){
        androidDriver.findElement(By.xpath("//android.widget.EditText[1]")).sendKeys(id);
        WebElement pwd = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText"));
        pwd.click();
        pwd.sendKeys(password);
        androidDriver.findElement(By.xpath("//android.widget.Button[@text='로그인']")).click();
    }

}
