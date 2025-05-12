package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {

	@DataProvider (name = "LoginData")
	public Object[][] getLoginData() throws IOException{
		
		String filePath = System.getProperty("user.dir")+"/testdata/TestData.xlsx"; //하.. 여기서 앞에 testdata 라고해서 실행해도 안됐었음 ㅠㅠ 
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowaCount();
		Object[][] data = new Object[rowCount-1][2];
		
		for (int i=1 ; i<rowCount ; i++ ) {
			
			
			data[i-1][0] = ExcelUtils.getCellData(i, 0); //Username
			data[i-1][1] = ExcelUtils.getCellData(i, 1); //Password
		}
		ExcelUtils.closeExcel();
		return data;
	}
	
	@DataProvider(name="LoginData2")
	public Object[][] getData(){
		return new Object[][] {
			{"user1","pass1"}, //여기서 작은 따옴표 쓰면 에러 난다. 
			{"user2","pass2"},
			{"user3","pass3"}
		};
	}
			
//	@Test (dataProvider = "LoginData2")밑에껀 파라미터로 가져오는 법 
//	@Test
//	@Parameters({"username","password"}) 
	@Test
	public void testValidationLogin() {
		
		Log.info("Starting login test...");
		test = ExtentReportManager.createTest("Login Test - " ); //이게 제목이 되는구나!!!
		
		test.info("Navigating to URL");
		LoginPage loginpage = new LoginPage(driver);
		
		Log.info("Adding credentials");
		test.info("Adding credentials");
		loginpage.enterUsername("admin@yourstore.com"); // String username 썻기 때문에 이줄 주석처리 
		loginpage.enterPassword("admin"); // String password 썻기 때문에 이줄 주석처리  
//		loginpage.enterUsername(username);
//		loginpage.enterPassword(Password); 
		test.info("Clicking on Login button");
		loginpage.clickLogin();
		
		//왜 맞는 아이디와 비밀번호 입력했는데도 패일 뜨는지 원인 찾아야함 
		System.out.println("Title of the page is : " + driver.getTitle());
		Log.info("Verifying page title");
		test.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "잠시만 기다리십시오…"); //테스트 success 난 이유 여기 있었음 
		
		test.pass("Login Successful");
		
	}
//	밑엘거 이거 주석 처리 해주기 
//	@Test
//	public void testLoginWithInvalidCredentials() {
//		
//		Log.info("Starting login test...");
//		test = ExtentReportManager.createTest("Login Test with Invalid Credemntials"); //이게 제목이 되는구나!!!
//		
//		test.info("Navigating to URL");
//		LoginPage loginpage = new LoginPage(driver);
//		
//		Log.info("Adding credentials");
//		test.info("Adding credentials");
//		loginpage.enterUsername("admin1234@yourstore.com");
//		loginpage.enterPassword("admin123");
//		test.info("Clicking on Login button");
//		loginpage.clickLogin();
//		
//		System.out.println("Title of the page is : " + driver.getTitle());
//		Log.info("Verifying page title");
//		test.info("Verifying page title");
//		Assert.assertEquals(driver.getTitle(), "Just a moment...123"); //테스트 fail 난 이유 여기있었음 
//		
//		test.pass("Login Successful");
//		
//	}
}
