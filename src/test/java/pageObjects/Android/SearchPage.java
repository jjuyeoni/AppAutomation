package pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Helpers.ElementHandlers;

public class SearchPage extends Common{

    public AndroidDriver androidDriver;
    public ElementHandlers handlers;
    public SoftAssert softAssert;

    public SearchPage(AndroidDriver androidDriver){
        super(androidDriver);
        this.androidDriver = androidDriver;
        this.handlers = new ElementHandlers(androidDriver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public void scrollViewDown(){
        WebElement element = androidDriver.findElement(By.id("android:id/content"));
        handlers.swipeByTouchActions(androidDriver,element,"up", 0.05);
    }

    public void clickSearch(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/toolbar_search_edit")).click();
    }

    // 큰 검색창 검색 버튼
    public void pressSearchButton(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/actionbar_search_product_execute_btn")).click();
    }

    // 결과 내 재검색 검색 버튼
    public void pressFilterButton(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/filter_query_search")).click();
    }

    public void clickSearchTextView(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/actionbar_search_product_edittext")).click();
    }

    public void inputSearchTextView(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/actionbar_search_product_edittext")).sendKeys("크림");
        pressSearchButton();

        clickToText("샘플체험");
        WebElement item = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[2]"));
        shoppingContent(item);
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

    public void cmpItem(){
        WebElement item = androidDriver.findElement(By.id("kr.co.company.hwahae:id/product_name_text_view"));
        shoppingContent(item);
    }

    public void cmpItemDetail(){
        androidDriver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"제품\"]/android.widget.TextView")).click();
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/filter_query_input")).sendKeys("수분");
        pressFilterButton();

        WebElement item
                = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));

        shoppingContent(item);
    }

    public void clickFirstRecommendItem(){
        androidDriver
                .findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]"))
                .click();
        compareText("브랜드");

    }

    public void clickCategory(){
        androidDriver
                .findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.TextView[2]"))
                .click(); // 카테고리 > 스킨케어 [전체보기]
    }

    public void bestItem(){
        WebElement item = androidDriver
                .findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView[1]"));
        String itemName = item.getText();

        item.click();

        String itemName2 =
                androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_name")).getText();

        softAssert.assertEquals(itemName,itemName2);
    }

    public void clickBottomBanner(){
        scrollViewDown();
        isTextDisplayed("인기 기획전");

        androidDriver
                .findElement(By.id("kr.co.company.hwahae:id/shopping_tab_banners"))
                .click();

        softAssert.assertTrue(androidDriver.findElement(By.id("kr.co.company.hwahae:id/image_2")).isSelected());
    }

}
