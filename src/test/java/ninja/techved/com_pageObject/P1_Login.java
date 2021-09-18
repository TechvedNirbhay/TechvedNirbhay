package ninja.techved.com_pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ninja.techved.com_testCases.BaseClass;

public class P1_Login {
	
	WebDriver ldriver;
	
	public P1_Login(WebDriver rdriver) {
		
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy(id = "ContentPlaceHolder1_txtEmail")
	WebElement textuname; 
	
	@FindBy(id = "ContentPlaceHolder1_txtPwd")
	WebElement textupass;
	
	@FindBy(xpath ="//label[text()='Employee']")
	WebElement EmpRadioButton;
	
	@FindBy(xpath ="//label[text()='Admin']")
	WebElement AdminRadioButton;
	//input[@value='Login']
	
	@FindBy(xpath ="//input[@value='Login']")
	WebElement SubmitButton;
	
	public void setTextInUnameField(String User_name) {
		
		BaseClass.sendKeys(textuname, 6, User_name);
	}
	
	public void setTextInUpassField(String User_pass) {
		
		BaseClass.sendKeys(textupass, 6, User_pass);
	}
	
	public void selectRadioButtonEmp() {
		
		BaseClass.clickOn(EmpRadioButton, 6);
		
	}
	
	public void selectRadioButtonAdmin() {
		
		BaseClass.doubleClickOn(AdminRadioButton, 5);
	}
	
	public void clickOnSubmitButton() {
		BaseClass.clickOn(SubmitButton, 6);
	}
	
}


