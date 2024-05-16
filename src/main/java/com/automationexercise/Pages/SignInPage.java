package com.automationexercise.Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationexercise.Base.BaseClass;
import com.automationexercise.config.Constants;

public class SignInPage extends BaseClass{
	SignUpPage delAcc;


	@FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")
	private WebElement signup_loginbtn;

	@FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/h2")
	private WebElement loginToUrAcc;

	@FindBy(xpath = "//input[@data-qa=\"login-email\"]")
	private WebElement email;

	@FindBy(xpath = "//input[@data-qa=\"login-password\"]")
	private WebElement pass;

	@FindBy(xpath = "//button[@data-qa=\"login-button\"]")
	private WebElement login;

	@FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/p")
	private WebElement err;

	@FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")
	private WebElement logoutbtn;

	@FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")
	private WebElement loggedInAsUname;



	private Properties configProperties;

	Constants con;
	public SignInPage() {
		super();
		PageFactory.initElements(driver, this);

		configProperties = new Properties();
		try {
			FileInputStream configFile = new FileInputStream("C:\\Users\\purnima\\eclipse-workspace\\AutomationExcercise\\src\\main\\java\\com\\automationexercise\\config\\config.properties");
			configProperties.load(configFile);
			configFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

	public void logIn(String username, String password) {
		signup_loginbtn.click();

		String actualResult = loginToUrAcc.getText();
		Assert.assertEquals(actualResult, "Login to your account");


		email.sendKeys(username);

		pass.sendKeys(password);
		login = driver.findElement(By.xpath("//button[@data-qa=\"login-button\"]"));
		login.click();


		//		Assert.assertEquals(loggedInAsUname.getText(), " Logged in as " + Name );
		Assert.assertEquals(loggedInAsUname.getText().trim(), "Logged in as " + "Danish12");
		System.out.println("Logged in as username' is visible");

	}



	public void loginWithValidUP(String usernameKey , String passwordkey) {
		String username = configProperties.getProperty("username");
		String password = configProperties.getProperty("password");

		logIn(username, password);
		
		
	}
	public void loginwithInValidUP() {
		signup_loginbtn.click();

		String actualResult = loginToUrAcc.getText();
		Assert.assertEquals(actualResult, "Login to your account");


		email.sendKeys("abassnas@outlook.com");

		pass.sendKeys("Danish@12");

		login.click();

		Assert.assertEquals(err.getText(), "Your email or password is incorrect!");
		System.out.println("Email or password is incorrect ");

	}

	public void logOutUser() {
		signup_loginbtn.click();

		String actualResult = loginToUrAcc.getText();
		Assert.assertEquals(actualResult, "Login to your account");

		email.sendKeys("Danish@889@gmail.com ");

		pass.sendKeys("Danish@23");

		login.click();

		Assert.assertEquals(loggedInAsUname.getText().trim(), "Logged in as " + "Danish@23");
		System.out.println("Logged in as ramdas001' is visible");

		logoutbtn.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
		System.out.println("user is navigated to login page");
	}
}