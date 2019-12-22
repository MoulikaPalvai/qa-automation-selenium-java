package com.crossover.e2e;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import junit.framework.TestCase;


public class GMailTest extends TestCase {
    private WebDriver driver;
    private Properties properties = new Properties();
    
    
    @BeforeMethod
    public void setUp() throws Exception {
        
        properties.load(getClass().getResourceAsStream("/test.properties"));
        //Dont Change below line. Set this value in test.properties file incase you need to change it..
        String property = properties.getProperty("webdriver.chrome.driver");
		System.setProperty("webdriver.chrome.driver",property);
        driver = new ChromeDriver();
    }
    
    
    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    /*
     * Please focus on completing the task
     * 
     */
    @Test
    public void testSendEmail() throws Exception {
        driver.get("https://mail.google.com/");
       
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement userElement = driver.findElement(By.id("identifierId"));
        userElement.sendKeys(properties.getProperty("username"));

        driver.findElement(By.id("identifierNext")).click();

        Thread.sleep(1000);

        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(properties.getProperty("password"));
        driver.findElement(By.id("passwordNext")).click();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        
        //Thread.sleep(1000);

        //WebElement composeElement = driver.findElement(By.xpath("//*[@role='button' and (.)='COMPSE']"));
        WebElement composeElement = driver.findElement(By.xpath("//div[text()='Compose']"));
        composeElement.click();

        driver.findElement(By.name("to")).clear();
        //driver.findElement(By.xpath("//textarea[@name='to']")).clear();
      
        //driver.findElement(By.name("to")).sendKeys(String.format("%s@gmail.com", properties.getProperty("username")));
        driver.findElement(By.name("to")).sendKeys(String.format("moulikapalvai@gmail.com"));
        String emailSubject = properties.getProperty("email.subject");
        String emailBody = properties.getProperty("email.body"); 
        driver.findElement(By.name("subjectbox")).sendKeys(emailSubject);
        driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys(emailBody);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@aria-label,'Send ‪(Ctrl-Enter)‬')]")).click();
        //Thread.sleep(1000);
        
        // emailSubject and emailbody to be used in this unit test.
        
        
    }
}
