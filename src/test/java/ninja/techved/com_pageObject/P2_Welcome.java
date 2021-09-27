package ninja.techved.com_pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ninja.techved.com_testCases.BaseClass;

public class P2_Welcome {
	
WebDriver ldriver;
	
	public P2_Welcome(WebDriver rdriver) {
		
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy(id = "lblEmployeeName")
	WebElement get_text; 

	@FindBy(id ="ContentPlaceHolder1_ImgBtnClose")
	WebElement ImgBtnClose;

	public String getText() {
		
		String pagewelcometext= BaseClass.getTextFromPage(get_text, 9);
		return pagewelcometext;
	}
	
	public void imgBtnClose() {
		BaseClass.clickOn(ImgBtnClose, 3);
	}
}
