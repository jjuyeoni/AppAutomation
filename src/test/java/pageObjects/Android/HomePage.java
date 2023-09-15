package pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Helpers.ElementHandlers;

import java.time.Duration;


public class HomePage extends Common{

    public AndroidDriver androidDriver;
    public ElementHandlers handlers;
    public SoftAssert softAssert;

    public HomePage(AndroidDriver androidDriver){
        super(androidDriver);
        this.androidDriver = androidDriver;
        this.handlers = new ElementHandlers(androidDriver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public void clickHomeButton(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/button_layout_0")).click();
    }

    /** 전면 팝업 노출 **/
    public void chkBanner(){
        try {
            // 이미지가 잡히지 않아서 [닫기] 로 대체
            androidDriver.findElement(By.xpath("//android.widget.Button[@text='닫기']")).click();
            System.out.println("banner found.");
        } catch (NoSuchElementException e) {
            System.out.println("No banner found.");
        }
    }

    public void rollingBanner(){
        WebElement element = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.FrameLayout"));
        handlers.swipeAction(element,"right");
        handlers.swipeAction(element,"right");
    }

    public void clickBanner(){
        WebElement element = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.FrameLayout"));
        element.click();

        /** 클릭한 페이지에 따른 뒤로가기 분기 - 수정 중
         * 1. 스마트스토어
         * 2. 이벤트페이지
         * 3. 화해쇼핑 **/
    }

    public void clickShortCut(String name){
        try {
            androidDriver.findElement(By.xpath("//android.widget.TextView[@text='"+name+"']")).click();
        }catch (Exception e) {
            System.out.println("Not Found : "+ name);
        }
    }

    public void compareShortCut(String name){
        try{
            softAssert.assertTrue(androidDriver.findElement(By.xpath("//*[contains(@text,'" + name + "')]")).isDisplayed());
        }catch (Exception e) {
            System.out.println("Not Found : " + name);
        }
    }

    public void checkBottomSheet(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_go_explore")).click();
    }

    public void cmpCategoryContent(){
        try{ // 첫번째 제품명 저장
            String skinCare
                    = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[2]/android.widget.TextView[1]"))
                            .getText();

            // 스토어 이름 제외하고 제품명만 남기기
            String expectedName = skinCare.substring(skinCare.indexOf(" ") + 1);

            // 첫번째 제품 클릭
            androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[2]")).click();
            String realName = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_name")).getText();

            // 제품명 동일한지 비교
            softAssert.assertTrue(realName.contains(expectedName));
        } catch (AssertionError e) {
            System.out.println("제품명이 동일하지 않습니다.");
        }
    }

    public void cmpRankContent(){
        try {
            // 첫번째 제품명 저장
            String skinCare
                    = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.TextView[2]"))
                    .getText();
            skinCare = skinCare.replaceAll(" ","");

            // 첫번째 제품 클릭
            androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.TextView[2]")).click();
            String realName = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_name")).getText();
            realName = realName.replaceAll(" ","");

            // 제품명 동일한지 비교
            softAssert.assertTrue(realName.contains(skinCare));
        } catch (AssertionError e) {
            System.out.println("제품명이 동일하지 않습니다.");
        }
    }

    public void cmpShoppingContent(){
        // 제품 금액 비교
        String price = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.TextView[6]")).getText();
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.TextView[1]")).click();
        System.out.println(price);

        WebElement element = androidDriver.findElement(By.id("kr.co.company.hwahae:id/impression_tracking_view"));
        handlers.swipeByTouchActions(androidDriver,element,"up",0.3);
        String realPrice = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_discount_price")).getText();
        System.out.println(realPrice);
        softAssert.assertTrue(price.contains(realPrice));
    }

    public void shoppingContent(WebElement element){
        try{
            String content = element.getText();
            // 스토어 이름 제외하고 제품명만 남기기
            String expectedName = content.substring(content.indexOf(" ") + 1);

            // 첫번째 제품 클릭
            element.click();
            String realName = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_name")).getText();

            // 제품명 동일한지 비교
            softAssert.assertTrue(realName.contains(expectedName));
        } catch (AssertionError e) {
            System.out.println("제품명이 동일하지 않습니다.");
        }
    }

    public void cmpRankingTap(String tap){
        // 2는 카테고리별 > 전체
        // 3은 카테고리별 > 옵션 선택할 경우
        WebElement element;
        String content;
        String expectedName;

        switch(tap){
            case "전체" :
                element = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.TextView[1]"));
                shoppingContent(element);

                break;

            case "옵션" :
                element = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.widget.TextView[1]"));
                shoppingContent(element);

                break;

            case "어워드" :
                element = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.GridView/android.view.ViewGroup[2]/android.widget.TextView[1]"));
                shoppingContent(element);

                break;
        }
    }
    public void similarItem(){
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='비슷템 찾기']")).click();
        compareShortCut("성분 비슷템 분석 결과");
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.ImageView")).click();
        compareShortCut("성분 비슷템");
    }

    public void swipeShortcut(){
        WebElement element = androidDriver.findElement(By.id("kr.co.company.hwahae:id/rv_home_shortcut_category"));
        handlers.swipeByTouchActions(androidDriver, element, "left", 0.25);
    }

    public void scrollToBottom(){
        WebElement element = androidDriver.findElement(By.id("kr.co.company.hwahae:id/container"));
        handlers.swipeByTouchActions(androidDriver,element,"up", 0.05);
    }

    public void scrollToId(String name){
        switch(name){
            case "화해쇼핑" :
                handlers.scrollToId("kr.co.company.hwahae:id/mdpick_container");
                break;
            case "뷰티ON" :
                handlers.scrollToId("kr.co.company.hwahae:id/hwahae_plus_container");
                break;
        }

    }

    public void checkSelectedTab(String tab){
        softAssert.assertTrue(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='" + tab + "']")).isSelected());
    }

    public void checkBeautyOn() {
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]")).click();
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/comment_input_edit")).sendKeys("너무 도움이 되었습니다!");
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/send_comment")).click();

        boolean isToastDisplayed = isToastMessageDisplayed("댓글 등록 완료!", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '댓글 등록 완료!'가 표시되지 않았습니다.");
    }

    public void checkFooter(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/app_recommendation")).click();
        softAssert.assertTrue(androidDriver.findElement(By.id("android:id/profile_tabhost")).isDisplayed());

        androidDriver.findElement(By.id("android:id/contentPanel")).click();
    }

    public void clickEvent(){
        try{
            androidDriver.findElement(By.id("kr.co.company.hwahae:id/com_braze_inappmessage_modal_button_dual_one")).click();
        }catch (Exception e){
            System.out.println("노출되는 이벤트 팝업이 없습니다");
        }
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='꼼꼼한 뷰티평가단']")).click();
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='신청하기']")).click();

        // 아래로 스크롤
        WebElement element = androidDriver.findElement(By.id("kr.co.company.hwahae:id/scroll_view"));
        handlers.swipeByTouchActions(androidDriver,element,"up",0.10);

        // 상품선택
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/option_selection_button")).click();
        androidDriver.findElement(By.className("android.widget.ScrollView")).click();

        // 이용자 동의
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/terms_checkbox")).click();

        // 이벤트 신청하기
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/bottom_button")).click();

        String text = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")).getText();
        softAssert.assertEquals("이벤트 신청 완료\n" +
                "다른 이벤트도 참여해보세요", text);

        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.ImageView")).click();
    }

    public void searchBrand(){
        androidDriver.findElement(By.className("android.widget.EditText")).sendKeys("이니스");
        androidDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"search\"]")).click();
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]")).click();

        String brandName =
                androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.TextView[1]")).getText();

        clickShortCut(brandName+" 브랜드관 보기");

        String title =
                androidDriver.findElement(By.id("kr.co.company.hwahae:id/title")).getText();

        softAssert.assertEquals(brandName,title);
    }

    public void shoppingCart(){
        WebElement element = androidDriver.findElement(By.id("kr.co.company.hwahae:id/container"));
        handlers.swipeByTouchActions(androidDriver, element,"down", 0.1);

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/linear_container_toolbar_cart_image")).click();
    }
}
