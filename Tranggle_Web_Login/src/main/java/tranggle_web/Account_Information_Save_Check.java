package tranggle_web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
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

public class Account_Information_Save_Check {
	//Appium 드라이버 전역 변수 선언 -> SetDriver / Test / EndDriver 함수에서 모두 사용하기 때문에, 전연변수로 선언
	AppiumDriver<MobileElement> driver;

	//Appium Xpath 경로 전역 변수 선언 -> 재사용을 위한 전역 선언
	String tranggleURL = "https://www.tranggle.com/";

	String tranggle_Home_Xpath = "//android.view.View[@content-desc=\"Tranggle\"]/android.widget.Image";

	String tranggle_Menu_Xpath = "//android.view.View[@content-desc=\"javascript:void(0);\"]";
	String tranggle_RLB_LoginText_Xpath = "//android.view.View[@content-desc=\"로그인\"]/android.widget.TextView";
	String tranggle_Loginpage_id_Srh_Xpath = "//android.view.View[@content-desc=\"아이디 찾기\"]/android.widget.TextView";

	String tranggle_ID_Input_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]";
	String tranggle_PW_Input_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]";

	String tranggle_Loginpage_LoginBtn = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.Button[1]";

	String tranggle_RLB_LogoutText_Xpath = "//android.view.View[@content-desc=\"로그아웃\"]/android.widget.TextView";

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
		//앱을 실행하기 위한 셋팅 작업 실행
		
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
	 * 트랭글 - 회원정보 > 닉네임 변경 후 저장 확인 
	 */
	
	@Test
	public void Account_Information_Save_Check_Test() throws Exception {
		// Xpath 변수 선언
		String tranggle_Rnb_Account_Information_Btn_Xpath = "//android.view.View[@content-desc=\"회원정보\"]/android.widget.TextView";

		String tranggle_Account_Inf_Profil_Img_Text_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.widget.ListView/android.view.View[1]/android.widget.TextView[1]";

		String tranggle_Account_Inf_Nickname_Input_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.widget.ListView/android.view.View[4]/android.widget.EditText";

		String tranggle_Accouut_Inf_Nickname_Check_Btn_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.widget.ListView/android.view.View[4]/android.widget.Button";

		String tranggle_Accouut_Inf_Nickname_Available_Guide_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.widget.ListView/android.view.View[4]/android.widget.TextView[2]";

		String tranggle_Account_Inf_Save_Btn_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button";

		String tranggle_Account_Inf_Save_Finish_Popup_Title_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.widget.TextView";

		String tranggle_Account_Inf_Save_Finish_Popup_Confirm_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.widget.Button";

		
		//className 변수 선언 
		String tranggle_Account_Inf_Nickname_Input_Classname = "android.widget.EditText";
		
		// 트랭글 웹 - 회원정보 닉네임 설정 후 저장 테스트 스크립트

		// 트랭글 홈페이지로 이동
		driver.get(tranggleURL);

		// 페이지를 띄위기 위해, 잠시 10초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		//트랭글 홈페이지 > "TRANGGLE"로고 앨리먼트의 텍스트를 가져옮
		String tranggle_Home = driver.findElementByXPath(tranggle_Home_Xpath).getText();

		System.out
				.println("Account_Information_Save_Check_Test() - Tranggle Home Resource : " + driver.getPageSource());

		/*로그인 / 로그아웃 자주 처리하는 동작 내용임으로 로그인 / 로그아웃를 함수로 작성 
		 * 로그인을 해야만 회원정보 페이지에 접근이 가능하기 떄문에, 로그인 작업을 진행한다. 
		*/ 
		Login(driver, tranggle_Home);
		
		/*로그인 함수에서 로그인 후 로그인 상태가 되었는지 확인하고 나서 
		 *아래의 RNB 화면 > [회원정보] 앨리먼트가 가져와 이동할 작업을 시작한다. 
		*/
		MobileElement rnb_Account_Inf_Btn = driver.findElementByXPath(tranggle_Rnb_Account_Information_Btn_Xpath);

		String rnb_Account_Inf_Btn_Text = rnb_Account_Inf_Btn.getText();

		//RNB 화면 > [화원정보] 버튼이 있는지 확인
		if (rnb_Account_Inf_Btn_Text.equals("회원정보")) {
			System.out.println("Account_Information_Save_Check_Test () - rnb_Account_Inf_Btn Check Success : "
					+ rnb_Account_Inf_Btn_Text);
		} else {
			System.out.println("Account_Information_Save_Check_Test () - rnb_Account_Inf_Btn Check Failure : "
					+ rnb_Account_Inf_Btn_Text);
			
			throw new Exception("RNB 화면 > [회원정보] 버튼 엘리먼트를 찾지 못했습니다.");
		}

		//[회원정보] 버튼 클릭하여, 회원정보 페이지로 이동한다.
		rnb_Account_Inf_Btn.click();

		//페이지 이동을 10초정도 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		//회원정보 페이지로 정상 이동 확인을 위한 회원정보 페이지 > "프로필 이미지" 앨리먼트를 가져와 확인 작업을 한다. 
		MobileElement account_Inf_Profil_Img_Text = driver
				.findElementByXPath(tranggle_Account_Inf_Profil_Img_Text_Xpath);

		String account_Inf_Profil_Img_Text_Str = account_Inf_Profil_Img_Text.getText();

		//"프로필 이미지"가 있는지 확인 -> 있으면 회원정보 페이지 정상 이동 확인됨
		if (account_Inf_Profil_Img_Text_Str.equals("프로필 이미지")) {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf Page Move Success : "
					+ account_Inf_Profil_Img_Text_Str);
		} else {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf Page Move Failure : "
					+ account_Inf_Profil_Img_Text_Str);
			
			throw new Exception("회원정보 페이지로 이동 및 프로필 이미지 텍스트 엘리먼트 찾기를 실패하였습니다.");
		}

		System.out.println("Account_Information_Save_Check_Test() - 회원정보 화면 스크를 하기 전 ==========");

		//닉네임 입력 후 중복체크를 하기 위해, 그 위치에 맞는 곳으로 이동 -> 스크롤 하단 1회 실행 
		scrollDown(driver);
		
		//스크롤 이동을 위한 5초 대기
		Thread.sleep(long_Sleep_Time_5000);

		System.out.println("Account_Information_Save_Check_Test() - 회원정보 화면 스크를 한 후 ==========");
		
		System.out.println("Account_Information_Save_Check_Test() - 회원정보 화면 스크를 한 후 > getPageResource : " + driver.getPageSource());
		
		
		System.out.println("Account_Information_Save_Check_Test() - 닉네임 입력창 찾기 전 -------------");

		
		try {
			
			//MobileElement account_Inf_Nickname_Input = driver.findElementByXPath(tranggle_Account_Inf_Nickname_Input_Xpath);
			
			System.out.println("Account_Information_Save_Check_Test() - 닉네임 입력창 찾기 전 -------------");
			
			/*닉네임 입력창 > 닉네임 입력을 위해 닉네임 입력창 앨리먼트를 가져온다. -> xpath로 가져와도 되나, xpath로 가져오면 에러가 발생 
			 * -> 원인 분석 시 해당 입력창이 동적으로 생성하는 것으로 파악됨 -> 중간 xpath 경로가 바뀌는 현상 발생 
			 * -> 그래서 입력창에 className이 edittext에 모두 동일한 값 적용된 것을 확인 
			 * -> 에디트텍스트 입력창 위치 인덱스 확인 시 3번쨰 위치 확인하여, Classname 리스트 가져와 인덱스 (2) -> 3번쨰 위치에 엘리먼트를 가져옮
			 */
			MobileElement account_Inf_Nickname_Input = driver.findElementsByClassName(tranggle_Account_Inf_Nickname_Input_Classname).get(2);
			String account_Inf_Nickname_Input_Text = account_Inf_Nickname_Input.getText();
			
			
			System.out.println("Account_Information_Save_Check_Test() - 닉네임 입력창 찾기 후 -------------");
			
			/* 닉네임이 입력되어 있는지 여부 파악 - 최조 계정 생성에 경우 입력된 내용이 없으나, 
			 * 여기 계정에 경우, 이미 입력이 된 계정으로 가장 중복된 네임이 없을 것같은 닉네임으로 설정하여, 
			 * 중복된 닉네임 체크 후 변경 작업을 진행한다. 
			 */
			if (account_Inf_Nickname_Input_Text.equals("코리아테스트67")) {
				account_Inf_Nickname_Input.sendKeys("코리아테스트68");
				
				System.out.println("Account_Information_Save_Check_Test() - 코리아테스트 68로 닉네임을 입력 완료 =========");
			} else if(account_Inf_Nickname_Input_Text.equals("코리아테스트68")) {
				account_Inf_Nickname_Input.sendKeys("코리아테스트67");
				
				System.out.println("Account_Information_Save_Check_Test() - 코리아테스트 67로 닉네임을 입력 완료 =========");
			} else {
				System.out.println("Account_Information_Save_Check_Test() - 닉네임 기존에 입력되지 않았습니다 ============");
				throw new Exception("닉네임 입력에 실패하였습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("Account_Information_Save_Check_Test() - 닉네임 입력창 못찾은 이유 : " + e);
		}
		
	
		System.out.println("Account_Information_Save_Check_Test() - 닉네임 입력창 > 입력값 전송 한 후 -------------");

		//닉네임 입력 후 3초 대기
		Thread.sleep(long_Sleep_Time);

		System.out.println("Account_Information_Save_Check_Test() - 중복확인 엘리먼트 가져오기 전 -------------");
		
		clickOnCoodinates(driver, 860, 777);
		
		//중복 확인 체크를 시간을 위해 3초 대기
		Thread.sleep(long_Sleep_Time);

		System.out.println("Account_Information_Save_Check_Test() - 저장 버튼을 찾기 위해 스크롤 내린다 - 내리기 전 =======");
		
		//저장 버튼을 클릭하여 저장하기 위해, 그에 맞는 위치로 스크롤한다 -> 스크롤 하단 1회 진행 -> 화면 하단까지 이동됨
		scrollDown(driver);
		
		System.out.println("Account_Information_Save_Check_Test() - 저장 버튼을 찾기 위해 스크롤 내린다 - 내리기 후 =======");
				
		//스크롤 이동을 위한 5초 대기
		Thread.sleep(long_Sleep_Time_5000);

		System.out.println("Account_Information_Save_Check_Test() - 저장 버튼 앨리먼트 가져오기 전 ========");
		
		//[저장] 버튼 앨리먼트를 가져옮
		MobileElement account_Inf_Save_Btn = driver.findElementByXPath(tranggle_Account_Inf_Save_Btn_Xpath);
		String account_Inf_Save_Btn_Text = account_Inf_Save_Btn.getText();
		
		System.out.println("Account_Information_Save_Check_Test() - 저장 버튼 앨리먼트 가져온 후 ========");
		
		//[저장] 버튼 있는지 확인
		if (account_Inf_Save_Btn_Text.equals("저장")) {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf_Save_Btn Found Success : "
					+ account_Inf_Save_Btn_Text);
		} else {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf_Save_Btn Found Failure : "
					+ account_Inf_Save_Btn_Text);
			
			throw new Exception("회원정보 페이지 > [저장] 버튼 앨리먼트를 찾기 못했습니다.");
		}

		System.out.println("Account_Information_Save_Check_Test () - 저장 버튼 클릭 전");
		
		//[저장] 버튼 클릭
		account_Inf_Save_Btn.click();
		
		System.out.println("Account_Information_Save_Check_Test () - 저장 버튼 클릭 후");
		
		//[저장] 버튼 터치 후 회원정보 수정 완료 팝업 출력 및 저장을 위한 시간을 위해 3초 대기 
		Thread.sleep(long_Sleep_Time);

		//회원정보 수정 완료 팝업 > 타이틀 앨리먼트를 가져올 -> 회원정보 수정 팝업이 정상 출력되는지 확인을 위해 가져옮
		MobileElement account_Inf_Save_Finish_Popup_Title = driver
				.findElementByXPath(tranggle_Account_Inf_Save_Finish_Popup_Title_Xpath);

		String account_Inf_Save_Finish_Popup_Title_Text = account_Inf_Save_Finish_Popup_Title.getText();

		//회원정보 수정 완료 팝업 > 타이틀 문구가 있는지 확인 -> 확인되면 팝업이 정상 출력된 것임
		if (account_Inf_Save_Finish_Popup_Title_Text.equals("회원 정보가 수정 되었습니다.")) {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf_Save_Finish_Popup Print Success : "
					+ account_Inf_Save_Finish_Popup_Title_Text);
		} else {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf_Save_Finish_Popup Print Failure : "
					+ account_Inf_Save_Finish_Popup_Title_Text);
			
			throw new Exception("회원정보 수정 완료 팝업 출력 및 팝업 > 타이틀 문구 엘리먼트 찾기 실패했습니다.");
		}

		//회원정보 수정 완료 팝업 닫기 위해, [확인] 버튼 앨리먼트를 가져옮
		MobileElement account_Inf_Save_Finish_Popup_Confirm = driver
				.findElementByXPath(tranggle_Account_Inf_Save_Finish_Popup_Confirm_Xpath);

		//회원정보 수정 완료 팝업 > [확인] 버튼 클릭 -> 팝업을 닫힘
		account_Inf_Save_Finish_Popup_Confirm.click();

		//팝업 닫힘 처리하기 위한 3초 대기
		Thread.sleep(long_Sleep_Time);

		//닉네임이 정상 저장되었는지 확인을 위해 다시 닉네임 입력창 앨리먼트를 가져옮
		MobileElement Account_Inf_Nickname_Input_Check = driver.findElementsByClassName(tranggle_Account_Inf_Nickname_Input_Classname).get(2);

		String Account_Inf_Nickname_Input_Check_Text = Account_Inf_Nickname_Input_Check.getText();

		//닉네임이 정상 저장 및 변경 되었는지 확인
		if (Account_Inf_Nickname_Input_Check_Text.equals("코리아테스트67")) {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf_Save Nickname Save Success : "
					+ Account_Inf_Nickname_Input_Check_Text);
		} 
		else if (Account_Inf_Nickname_Input_Check_Text.equals("코리아테스트68")) {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf_Save Nickname Save Success : "
					+ Account_Inf_Nickname_Input_Check_Text);
		}
		else {
			System.out.println("Account_Information_Save_Check_Test () - account_Inf_Save Nickname Save Failure : "
					+ Account_Inf_Nickname_Input_Check_Text);
			
			throw new Exception("닉네임 저장에 실패하였습니다.");
		}
	}

	//화면을 스크롤 하단으로 이동하기 위한 함수
	public static void scrollDown(AppiumDriver<MobileElement> driver) {
		//모바일 화면의 현재 사이즈를 가져옮
		Dimension size = driver.manage().window().getSize();

		int startX = size.width / 2;
		int startY = (int) (size.height * 0.8);
		int endY = (int) (size.height * 0.2);

		System.out.println("scrollDown() - Start ExecuteScript");

		// 스크롤 액션 수행 -> 화면의 20~80% 영역만큼 스크롤 이동
		driver.executeScript("mobile:shell", ImmutableMap.of("command", "input swipe", "args",
				ImmutableList.of("0", String.valueOf(startY), "0", String.valueOf(endY))));

		System.out.println("scrollDown() - End() ExecuteScript");

	}

	public static void clickOnCoodinates(AppiumDriver<MobileElement> driver, int x, int y) {
		// 좌표를 클릭하기 위해 TouchAction을 사용

		TouchAction touchAction = new TouchAction(driver);

		System.out.println("clickOnCoodinates() - Start");

		//특정 위치에 좌표 값에 위치를 클릭
		touchAction.tap(PointOption.point(x, y)).perform();

		System.out.println("clickOnCoodinates() - End");
	}

	//로그인 처리 하는 함수
	public void Login(AppiumDriver<MobileElement> driver, String tranggle_home) throws Exception {
		// Xpath 변수 선언
		if (tranggle_home.equals("Tranggle")) {
			System.out.println("Login() - Tranggle Homepage Move Success : " + tranggle_home);

		} else {
			System.out.println("Login() - Tranggle Homepage Move Failure : " + tranggle_home);
			
			throw new Exception("트랭글 홈 화면 이동에 실패했습니다.");
		}
		// 홈페이지 > 홈 > 우측 상단 > 메뉴 클릭
		System.out.println("오른쪽 상단 메뉴 누르기 전입니다.");

		MobileElement menu_Icon = driver.findElementByXPath(tranggle_Menu_Xpath);
		menu_Icon.click();

		System.out.println("오른쪽 상단 메뉴 클릭한 후입니다.");

		// RNB 화면 펼쳐지기 위한 5초 대기
		Thread.sleep(long_Sleep_Seconds_5);

		MobileElement Login_Text_Btn = driver.findElementByXPath(tranggle_RLB_LoginText_Xpath);

		String rnb_Login_Text = Login_Text_Btn.getText();

		// RNB 화면 정상적으로 펼쳐져 있는지 확인을 위한 체크 - [로그인] 버튼으로 확인
		if (rnb_Login_Text.equals("로그인")) {
			System.out.println("Login() - Tranggle RNB Open Success : " + rnb_Login_Text);
		} else {
			System.out.println("Login() - Tranggle RNB Open Failure : " + rnb_Login_Text);
			
			throw new Exception("트랭글 RNB 화면 펼치기 실패했거나 로그인 중이라서 실패했습니다.");
		}

		// [로그인] 버튼을 클릭
		Login_Text_Btn.click();

		// 로그인 페이지 이동을 위한 5초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_7, TimeUnit.SECONDS);

		String id_Search_Text = driver.findElementByXPath(tranggle_Loginpage_id_Srh_Xpath).getText();

		// 로그인 페이지 정상 이동 확인을 위한 체크 - [아이디 찾기] 버튼으로 확인
		if (id_Search_Text.equals("아이디 찾기")) {
			System.out.println("Login() - Tranggle Loginpage Move Success : " + id_Search_Text);
		} else {
			System.out.println("Login() - Tranggle Loginpage Move Failure : " + id_Search_Text);
			
			throw new Exception("트랭글 로그인 페이지 이동에 실패했습니다.");
		}

		// 로그인 페이지 > 아이디 정보 입력
		MobileElement id_Input = driver.findElementByXPath(tranggle_ID_Input_Xpath);
		id_Input.sendKeys("koreatest67");

		// 로그인 페이지 > 비밀번호 정보 입력
		MobileElement pw_Input = driver.findElementByXPath(tranggle_PW_Input_Xpath);
		pw_Input.sendKeys("test1234@");

		Thread.sleep(normal_Sleep_Time_2000);

		// 로그인 정보 입력 후 [로그인] 버튼 클릭 하여, 로그인 처리
		MobileElement Login_Btn = driver.findElementByXPath(tranggle_Loginpage_LoginBtn);
		Login_Btn.click();

		// 정상 로그인 처리를 위한 10초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		// 로그인 후 홈으로 이동되어 홈페이지로 정상 이동되었는지 체크
		if (tranggle_home.equals("Tranggle")) {
			System.out.println("Login() - Tranggle Homepage Move Success : " + tranggle_home);
		} else {
			System.out.println("Login() - Tranggle Homepage Move Failure : " + tranggle_home);
			
			throw new Exception("로그인 실패 or 로그인 후 홈화면 이동에 실패했습니다.");
		}

		// 다시 홈페이지 > 우측 > 상단 > 메뉴 클릭
		System.out.println("로그인 후 메뉴 버튼 누르기 전입니다.");

		MobileElement Rmenu_Icon = driver.findElementByXPath(tranggle_Menu_Xpath);
		Rmenu_Icon.click();

		System.out.println("로그인 후 메뉴 버튼 누른 후 입니다.");

		// RNB 화면 정성적으로 펼쳐지기 위해 3초 대기
		Thread.sleep(long_Sleep_Time);

		MobileElement Logout_Text_Btn = driver.findElementByXPath(tranggle_RLB_LogoutText_Xpath);

		// 정상 로그인 되었는지 체크 - RNB 화면 > [로그아웃] 버튼으로 확인
		if (Logout_Text_Btn.getText().equals("로그아웃")) {
			System.out.println("Login() - Tranggle Normal Login Success : " + Logout_Text_Btn.getText());
		} else {
			System.out.println("Login() - Tranggle Normal Login Failure : " + Logout_Text_Btn.getText());
			
			throw new Exception("정상 로그인 실패 or RNB 화면 > [로그아웃] 버튼 앨리먼트 찾기 실패했습니다.");
		}

	}

	public void Logout() throws Exception {
		// 트랭글 홈페이지로 이동
		driver.get(tranggleURL);

		// 페이지를 띄위기 위해, 잠시 10초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		String tranggle_Home = driver.findElementByXPath(tranggle_Home_Xpath).getText();

		// 트랭글 홈페이지 이동 확인 체크
		if (tranggle_Home.equals("Tranggle")) {
			System.out.println("Logout() - Tranggle Homepage Move Success : " + tranggle_Home);

		} else {
			System.out.println("Logout() - Tranggle Homepage Move Failure : " + tranggle_Home);
			
			throw new Exception("로그아웃() - 트랭글 홈화면 이동에 실패 했습니다.");
		}

		// 홈페이지 > 상단 > 메뉴 클릭
		System.out.println("오른쪽 상단 메뉴 누르기 전입니다.");

		MobileElement menu_Icon = driver.findElementByXPath(tranggle_Menu_Xpath);
		menu_Icon.click();

		System.out.println("오른쪽 상단 메뉴 클릭한 후입니다.");

		// RNB 화면 펼쳐지기 위한 3초 대기
		Thread.sleep(long_Sleep_Time);

		MobileElement Logout_Text_Btn = driver.findElementByXPath(tranggle_RLB_LogoutText_Xpath);

		// RNB 화면을 쳐져서 아직도 로그인 상태 체크 - [로그아웃] 버튼으로 확인
		if (Logout_Text_Btn.getText().equals("로그아웃")) {
			System.out.println("Logout() - Tranggle Normal Login Success : " + Logout_Text_Btn.getText());
		} else {
			System.out.println("Logout() - Tranggle Normal Login Failure : " + Logout_Text_Btn.getText());
			
			throw new Exception("RNB 화면 > [로그아웃] 버튼 앨리먼트 찾기 실패했습니다.");
		}

		// 로그아웃 처리를 위해 [로그아웃] 버튼을 클릭
		Logout_Text_Btn.click();

		// 정상 로그아웃 처리를 위한 2초 대기
		Thread.sleep(normal_Sleep_Time_2000);

	}

	@AfterClass
	public void EndDriver() {
		try {
			if (driver != null) {
				/* 회원정보 저장 확인 후 현재 로그인된 상태이기 떄문에, 
				 * 로그아웃 함수를 통해, 로그아웃 작업 진행
				 */
				Logout();
				driver.quit();
				System.out.println("EndDriver() - Driver quit Success");
			}

		} catch (Exception e) {
			System.out.println("EndDriver() - Driver quit Failure : " + e);
			// TODO: handle exception
		}
	}

}
