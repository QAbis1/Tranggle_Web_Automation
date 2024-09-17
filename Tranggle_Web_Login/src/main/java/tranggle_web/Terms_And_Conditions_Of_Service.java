package tranggle_web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class Terms_And_Conditions_Of_Service {
	// Appium 드라이버 전역 변수 선언 -> SetDriver / Test / EndDriver 함수에서 모두 사용하기 때문에, 전연변수로
	// 선언
	AppiumDriver<MobileElement> driver;

	// sleep 타임 대기 변수 -> 앱 이동 및 동작 간에 대기하는 시간이 발샏하여, 전역변수 선언을 통해 공유하여 사용
	int short_Sleep_Time = 500;
	int short_Sleep_Time_1000 = 1000;
	int normal_Sleep_Time_1500 = 1500;
	int normal_Sleep_Time_2000 = 2000;
	int long_Sleep_Time = 3000;
	int long_Sleep_Time_5000 = 5000;

	// 타임 대기 시간 변수 선언
	int short_Sleep_Seconds_1 = 1;
	int short_Sleep_Seconds_2 = 2;
	int normal_Sleep_Seconds_3 = 3;
	int long_Sleep_Seconds_5 = 5;
	int long_Sleep_Seconds_7 = 7;
	int long_Sleep_Seconds_10 = 10;

	@BeforeClass
	public void SetDriver() throws MalformedURLException {
		// 앱을 실행하기 위한 셋팅 작업 실행
		String mcAPPPackage = "appPackage";
		String mcAPPActivity = "appActivity";

		String pName = "Android";
		String deviceName = "emulator-5554";
		String appPackage = "com.android.chrome";
		String appActivity = "com.google.android.apps.chrome.Main";

		String serverURL = "http://127.0.0.1:4723/wd/hub";

		boolean no_Reset = true;
		boolean full_Reset = false;

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, pName);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(mcAPPPackage, appPackage);
		capabilities.setCapability(mcAPPActivity, appActivity);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, no_Reset);
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, full_Reset);

		// Appium 드라이버 초기화 및 연결할 드라이버 생성
		URL appiumServerUrl = new URL(serverURL);
		driver = new AndroidDriver<>(appiumServerUrl, capabilities);

		System.out.println("SetDriver() - Driver Created - driver : " + driver);
	}

	/*
	 * 
	 * 트랭글 웹 - 서비스 이용약관 페이지 출력
	 * 
	 */
	@Test
	public void Service_Detail_Test() throws InterruptedException {
		// Xpath 변수 선언
		String tranggleURL = "https://www.tranggle.com/";

		String tranggle_Home_Xpath = "//android.view.View[@content-desc=\"Tranggle\"]/android.widget.Image";

		String tranggle_Sevice_Detail_Tap_Btn = "//android.view.View[@content-desc=\"서비스 이용약관\"]/android.widget.TextView";

		String tranggle_Sevice_Detail_Page_Title = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.widget.TextView[1]";

		// 서비스 이용약관 버튼 위치 좌표
		int service_Detail_x = 216;
		int service_Detail_y = 1288;

		// 트랭글 홈페이지로 이동
		driver.get(tranggleURL);

		// 페이지를 띄위기 위해, 잠시 10초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		String tranggle_Home = driver.findElementByXPath(tranggle_Home_Xpath).getText();

		// 트랭글 홈페이지 정상 이동 확인
		if (tranggle_Home.equals("Tranggle")) {
			System.out.println("Service_Detail_Test() - Tranggle Homepage Move Success : " + tranggle_Home);

		} else {
			System.out.println("Service_Detail_Test() - Tranggle Homepage Move Failure : " + tranggle_Home);

			throw new InterruptedException("트랭글 홈화면 이동에 실패했습니다.");
		}

		System.out.println("Service_Detail_Test() - Tranggle Home Resource : " + driver.getPageSource());

		/*
		 * 서비스이용약관 페이지 이동을 위해, [서비스이용약관] 버튼 위치로 이동 > 최하단으로 내리기 위해, 총 4회 스크롤 하단 이동 실시
		 */
		for (int i = 0; i < 4; i++) {
			// 아래의 함수에서 스크롤 하단으로 이동 진행
			scrollDown(driver);

			System.out.println("Service_Detail_Test() - scrollDown count : " + (i + 1));
		}

		// 스크롤 이동을 위한 3초 대기
		Thread.sleep(long_Sleep_Time);

		/*
		 * 서비스 이용약관 위치를 클릭 -> 아래의 함수에서 진행 -> 이 떄 서비스 이용약관에 좌표값을 파라미터로 넘김 -> 좌표 값으로 클릭 하는
		 * 이유 -> 해당 영역을 위치를 찍을 수 없음 -> 특정 좌표 찍는 것으로 서비스이용약관 화면으로 이동함
		 */
		clickOnCoodinates(driver, service_Detail_x, service_Detail_y);

		// 페이지 이동을 위한 2초 sleep 대기 후 페이지 다 뜰떄까지 대기처리까지 추가함(페이지 모두 로드 될때까지 기다렸으나, 다 뜨기도 전에 엘리먼트를 찾아
		// 버려서 생기는 문제가 발생 -> 클릭 후 대기 시간을 2초 정도 주어 예외 방지함
		Thread.sleep(normal_Sleep_Time_2000);
		
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		/*
		 * MobileElement service_Detail_Tap_Btn =
		 * driver.findElementByXPath(tranggle_Sevice_Detail_Tap_Btn);
		 * 
		 * String service_Detail_Tap_Text = service_Detail_Tap_Btn.getText();
		 * 
		 * if (service_Detail_Tap_Text.equals(service_Detail_Tap_Text)) { System.out.
		 * println("Service_Detail_Test() - Terms_And_Conditions_Of_Service Page Move Success :"
		 * + service_Detail_Tap_Text);; } else { System.out.
		 * println("Service_Detail_Test() - Terms_And_Conditions_Of_Service Page Move Failure :"
		 * + service_Detail_Tap_Text); }
		 */

		// 서비스이용약관 페이지 정상 이동 확인을 위한, 서비스 이용약관 내용을 앨리먼트로 가져옮
		MobileElement service_Detail_Page_Title = driver.findElementByXPath(tranggle_Sevice_Detail_Page_Title);

		String service_Detail_Page_Title_Text = service_Detail_Page_Title.getText();

		// 서비스 이용약관 타이틀 문구 확인으로 서비스 이용약관 페이지 정상 이동을 확인
		// 2024.09.17 baek ilsung - 서비스 이용약관 타이틀 문구 변경 (서비스 이용약관 (시행일 2024.05.08))
		if (service_Detail_Page_Title_Text.equals("서비스 이용약관 (시행일자 2024.05.08)")) {
			System.out.println("Service_Detail_Test() - Terms_And_Conditions_Of_Service Page Move Success :"
					+ service_Detail_Page_Title_Text);
		} else {
			System.out.println("Service_Detail_Test() - Terms_And_Conditions_Of_Service Page Move Failure :"
					+ service_Detail_Page_Title_Text);

			throw new InterruptedException("서비스 이용약관 페이지 정상 이동 실패 or 서비스 이용약관 > 타이틀 문구가 변경되었습니다.");
		}

	}

	// 화면 하단으로 스크롤 이동하는 함수
	public static void scrollDown(AppiumDriver<MobileElement> driver) {
		Dimension size = driver.manage().window().getSize();

		int startX = size.width / 2;
		int startY = (int) (size.height * 0.8);
		int endY = (int) (size.height * 0.2);

		System.out.println("scrollDown() - Start ExecuteScript");

		// 스크롤 액션 수행
		driver.executeScript("mobile:shell", ImmutableMap.of("command", "input swipe", "args",
				ImmutableList.of("0", String.valueOf(startY), "0", String.valueOf(endY))));

		System.out.println("scrollDown() - End() ExecuteScript");

	}

	// 특정 위치에 좌표를 클릭 하는 함수
	public static void clickOnCoodinates(AppiumDriver<MobileElement> driver, int x, int y) {
		// 좌표를 클릭하기 위해 TouchAction을 사용

		TouchAction touchAction = new TouchAction(driver);

		System.out.println("clickOnCoodinates() - Start");

		// 특정 위치에 Tap 함수로 클릭
		touchAction.tap(PointOption.point(x, y)).perform();

		System.out.println("clickOnCoodinates() - End");
	}

	// Appium 드라이버 종료
	@AfterClass
	public void EndDriver() {
		try {
			if (driver != null) {
				driver.quit();
				System.out.println("EndDriver() - Driver quit Success");
			}

		} catch (Exception e) {
			System.out.println("EndDriver() - Driver quit Failure : " + e);
			// TODO: handle exception
		}
	}

}
