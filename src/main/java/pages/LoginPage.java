package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Log;

public class LoginPage {

	private WebDriver driver;
	
	@FindBy(id="Email")
	WebElement usernameTextbox;
	
	@FindBy(id="Password")
	WebElement passwordTextbox;
	
	@FindBy(xpath="//*[@id=\\\"main\\\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button")
	WebElement loginButton;
	
//  아래 3개 행을 위에 걸로 대체 가능 
//	private By usernameTextBox = By.id("Email");     
//	private By passwordTextBox = By.id("Password");   
//	private By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");

	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
	
		usernameTextbox.clear();
		usernameTextbox.sendKeys(username);
		// 아래 2개 행을 위에 걸로 대체 가능
//		driver.findElement(usernameTextBox).clear();
//		driver.findElement(usernameTextBox).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
		// 아래 2개 행을 위에 걸로 대체 가능 
//		driver.findElement(passwordTextBox).clear();
//		driver.findElement(passwordTextBox).sendKeys(password);
	}
	
	public void clickLogin() {
		
		Log.info("Clicking login button..");
		
		loginButton.click();
		// 아래 1개 행을 위에 걸로 대체 가능 
//		driver.findElement(loginButton).click();
		
	}
	
	
}
