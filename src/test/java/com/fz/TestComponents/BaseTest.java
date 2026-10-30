package com.fz.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fz.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver DriverInitialization() throws IOException {


        //create properties object
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
            +"//src//main//java//com//fz//resources//GlobalData.properties");
        prop.load(fis);

        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        if(browser.contains("chrome")){

            ChromeOptions options = new ChromeOptions();
            //WebDriverManager.chromedriver().setup();
            if(browser.contains("headless")){
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
            //driver.manage().window().setSize(new Dimension(1440,990));
        }else
            if(browser.contains("edge")){
            System.setProperty(
                "webdriver.edge.driver",
                "C://Users//Fati//Documents//msedgedriver.exe"
            );
            EdgeOptions options = new EdgeOptions();
            if(browser.contains("headless")){

                options.addArguments("--headless");
                //options.addArguments("--window-size=1920,1080");
            }
            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,990));

        }
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException {
        driver = DriverInitialization();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void closePages(){
        driver.close();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        String getJsonFile = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(getJsonFile, new TypeReference<List<HashMap<String, String>>>(){});

        return data;
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot screen = (TakesScreenshot) driver;
        File source = screen.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "//reports//"+testCaseName+".png";
        File destination = new File(filePath);

        FileUtils.copyFile(source,destination);
        return filePath;
    }
}
