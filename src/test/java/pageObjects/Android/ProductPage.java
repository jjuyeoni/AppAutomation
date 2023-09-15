package pageObjects.Android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Helpers.ElementHandlers;

public class ProductPage extends Common {
    public AndroidDriver androidDriver;
    public SoftAssert softAssert;
    public ElementHandlers handlers;

    public ProductPage(AndroidDriver androidDriver){
        super(androidDriver);
        this.androidDriver = androidDriver;
        this.handlers = new ElementHandlers(androidDriver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public void shoppingContent(WebElement element){
        try{
            String content = element.getText();
            // 스토어 이름 제외하고 제품명만 남기기
            String expectedName = content.substring(content.indexOf(" ") + 1);

            // 첫번째 제품 클릭
            element.click();
            String realName = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_name")).getText();
            System.out.println(expectedName + " : " +realName);

            // 제품명 동일한지 비교
            softAssert.assertTrue(realName.contains(expectedName));
        } catch (AssertionError e) {
            System.out.println("제품명이 동일하지 않습니다.");
        }
    }

    public void scrollDown(double percent){
        WebElement element = androidDriver.findElement(By.id("android:id/content"));
        handlers.swipeByTouchActions(androidDriver,element,"up", percent);
    }
    public void checkFirstItem(){
        clickToText("화해쇼핑");
        clickToText("전체");
        WebElement item = androidDriver
                .findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.GridView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[1]"));

        shoppingContent(item);
    }

    public void clickMagnifier(){
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout[3]/android.widget.ImageView[2]"))
                .click();
        compareText("검색어를 입력해 보세요");
        pressBackButton();
    }

    public void checkBrandName(){
        WebElement brand = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_brand"));
        String brandName = brand.getText();

        brand.click();

        String brandName2 = androidDriver.findElement(By.id("kr.co.company.hwahae:id/title")).getText();
        softAssert.assertEquals(brandName,brandName2);

        pressBackButton();
    }

    public void checkRating(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_review_count")).click();
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/actionbar_review_purchase")).click(); // 구매하기

        /** 판매중 상품 바텀 레이어 안 뜰 경우? **/
//        compareText("판매 중인 상품");

        WebElement item = androidDriver
                .findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));

        shoppingContent(item);

        pressBackButton();
        pressBackButton();
    }

    public void putComparison(){
        scrollDown(0.2);

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/btn_compare")).click();

        boolean isToastDisplayed = isToastMessageDisplayed("비교함에 담겼습니다.", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '비교함에 담겼습니다.'가 표시되지 않았습니다.");
    }

    public void checkRanking(){
        String rank = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/android.widget.LinearLayout[1]/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[2]"))
                        .getText();
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/layout_detail_ranking")).click();
        compareText("랭킹/수상 정보");

        String rank2 = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[2]"))
                .getText();

        softAssert.assertEquals(rank,rank2);

        pressBackButton();
    }

    public void checkSeller(){
        handlers.scrollToId("kr.co.company.hwahae:id/tab_detail");
        clickToText("상품정보");

        String sellerName = androidDriver.findElement(By.id("kr.co.company.hwahae:id/seller_name")).getText();

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/seller_box")).click();
        String sellerName2 = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.TextView"))
                .getText();

        softAssert.assertEquals(sellerName, sellerName2);

        pressBackButton();
    }

    public void clickQnA(){
        handlers.scrollToId("kr.co.company.hwahae:id/ask_list");

        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]"))
                .click();
        compareText("상품 문의");

        pressBackButton();
    }

    public void clickGoodsNotify(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/goods_notify")).click();
        compareText("상품정보 제공고시");

        pressBackButton();
    }

    public void clickReturnGuide(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/goods_return_guide")).click();
        compareText("배송/교환/반품 안내");

        pressBackButton();
    }

    public void clickPartnerInfo(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/partner_info")).click();
        compareText("판매자 정보");

        pressBackButton();
    }

    public void WeeklyBest10(){
        scrollDown(0.3);
        WebElement product = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
        shoppingContent(product);
        pressBackButton();
    }

    public void Event(){
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/androidx.recyclerview.widget.RecyclerView"))
                .click();
        compareText("이벤트");
        pressBackButton();
    }

    public void HotProduct(){
        scrollDown(0.25);
        WebElement element
                = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView"));
        shoppingContent(element);
        pressBackButton();
    }

    public void similarProduct(){
        WebElement element
                = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView"));
        shoppingContent(element);
        pressBackButton();
    }

    public void discountProduct(){
        scrollDown(0.25);
        WebElement element
                = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
        shoppingContent(element);
        pressBackButton();
    }

    public void HotEvent(){
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/androidx.viewpager.widget.ViewPager")).click();
        compareText("이벤트");

        pressBackButton();

    }

    public void clickReviewTopic(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/rv_good_product_topic")).click();
        compareText("제품 리뷰");
        pressBackButton();
    }

    public void clickIngredient(){
        handlers.scrollToId("kr.co.company.hwahae:id/ingredient_container");
        clickToText("성분 구성");
        compareText("성분");

        clickToText("목적별");
        WebElement element = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView[2]"));
        element.click();

        /** 즐겨찾기 추가 **/
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/ingredient_result_popup_favoritebtn")).click();
        compareText("즐겨찾기 폴더에 추가");
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/category_list_view")).click();

        boolean isToastDisplayed = isToastMessageDisplayed("성분 즐겨찾기에 추가되었어요 ^^", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '성분 즐겨찾기에 추가되었어요 ^^'가 표시되지 않았습니다.");

        /** 즐겨찾기 해제 **/
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/ingredient_result_popup_favoritebtn")).click();

        isToastDisplayed = isToastMessageDisplayed("즐겨찾기 취소!", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '즐겨찾기 취소!'가 표시되지 않았습니다.");

        pressBackButton();
        pressBackButton();
    }

    public void reviewImage(){
        handlers.scrollToId("kr.co.company.hwahae:id/review_information_image_recycler_view");
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]"))
                .click();
        clickToText("리뷰 보러가기");
        compareText("리뷰");

        pressBackButton();
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/image_back")).click();
    }

    public void writeGoodReview(){
        clickToText("리뷰 쓰기");
        try {
            WebElement frameLayoutElement = androidDriver.findElement(By.xpath("//android.widget.FrameLayout"));
            frameLayoutElement.click();
        } catch (Exception e) {
            System.out.println("Not Found : mainLayout");
        }

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/rating_5")).click();

        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText"))
                .sendKeys("좋았던 점 입력하기. 좋았던 점 입력하기. 좋았던 점 입력하기. 좋았던 점 입력하기. 좋았던 점 입력하기. ");
        pressBackButton();

        clickToText("임시 저장");
    }

    public void writeBadReview(){
        clickToText("리뷰 쓰기");
        clickToText("예");

        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText"))
                .sendKeys("아쉬운 점 입력하기. 아쉬운 점 입력하기. 아쉬운 점 입력하기. 아쉬운 점 입력하기. 아쉬운 점 입력하기. ");

        scrollDown(0.2);
        /** 추천여부 **/
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/recommendation_y")).click();

        /** 사진첩에서 사진 첨부 **/
        clickToText("일반 첨부");
        if (androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).isDisplayed())
            clickToText("허용");

        clickToText("전체보기");
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.GridView/android.widget.FrameLayout[1]")).click();
        clickToText("다음");
        clickToText("저장");

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/filter_button_container")).click();
        clickToText("예");

        clickToText("확인");
        compareText("리뷰");

        pressBackButton();
    }

    public void modifyReview(){
        scrollDown(0.2);
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/review_more_button")).click();

        // 유용한순 필터 클릭
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/filter_chip_order")).click();
        // 내가 등록한 리뷰 우측 ... 버튼
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/review_writer_menu_image_view")).click();
        clickToText("수정하기");

        try {
            WebElement frameLayoutElement = androidDriver.findElement(By.xpath("//android.widget.FrameLayout"));
            frameLayoutElement.click();
        } catch (Exception e) {
            System.out.println("Not Found : mainLayout");
        }

        compareText("리뷰 수정");
        scrollDown(0.2);

        // 사진 삭제
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/review_image_delete")).click();
        clickToText("예");

        clickToText("등록");

        compareText("이제 화해에 리뷰를 등록할까요? :)");
        clickToText("예");

        compareText("리뷰 작성 완료\n" +
                "\n" +
                "작성된 리뷰는 다음 영업일(주말/공휴일 제외)까지 검수 후 최종 등록이 완료됩니다.\n" +
                "※ 화해 리뷰 기준에 맞지 않을 경우, 수정 요청을 드릴 수 있습니다.");
        clickToText("확인");

        compareText("리뷰");

        pressBackButton(); // 제품 리뷰 화면으로 이동
        pressBackButton(); // 제품 상세 화면으로 이동
        pressBackButton(); // 화해 쇼핑 > 온리화해 탭으로 이동
        pressBackButton(); // 화해 쇼핑 홈으로 이동
    }

    public void modifyReviewInMy(){
        clickToText("마이");

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_my_review")).click();
        compareText("내가 쓴 리뷰");

        androidDriver.findElement(MobileBy.id("kr.co.company.hwahae:id/review_product_menu_image_view")).click();
        clickToText("수정하기");

        scrollDown(0.2);
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText"))
                .sendKeys("꿀팁 문구 넣기");

        clickToText("등록");

        compareText("이제 화해에 리뷰를 등록할까요? :)");
        clickToText("예");

        compareText("리뷰 작성 완료\n" +
                "\n" +
                "작성된 리뷰는 다음 영업일(주말/공휴일 제외)까지 검수 후 최종 등록이 완료됩니다.\n" +
                "※ 화해 리뷰 기준에 맞지 않을 경우, 수정 요청을 드릴 수 있습니다.");
        clickToText("확인");

    }
    public void deleteReview() {
        androidDriver.findElement(MobileBy.id("kr.co.company.hwahae:id/review_product_menu_image_view")).click();
        clickToText("삭제하기");

        compareText("리뷰를 삭제할까요?");
        clickToText("예");

        compareText("리뷰 삭제 완료!");
        clickToText("확인");

        pressBackButton();
    }
}
