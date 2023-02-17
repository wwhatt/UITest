package util;

import data.UIElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.URL;


public class Base {

    public static WebDriver driver;
    private static Logger logger = Logger.getLogger(Base.class);
    @BeforeSuite
    @Parameters({"browserType", "driverPath"})
    public void browser(String browserType, String driverPath) throws InterruptedException {

        /**
         * 获取chromedriver地址
         * **/

        if (browserType.equals("chrome")) {
            //定义Chrome驱动
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
        } else if (browserType.equals("edge")) {

        } else if (browserType.equals("firefox")) {
            System.setProperty("Webdriver.gecko.driver", driverPath);
            driver = new FirefoxDriver();
        } else {
            System.out.println("您选择的浏览器暂时不支持");
        }

        //最大化浏览器
        driver.manage().window().maximize();
        Thread.sleep(2000);

    }

    /**
     * 调转地址
     * @param url
     */
    public void to(String url) {
        driver.navigate().to(url);
        logger.info("打开浏览器"+url);
    }

    /**
     * 关闭浏览器
     */
    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }


    /**显示等待
     * @param locator   定位位置
     * @return
     */

    public WebElement waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("元素定位成功");
            return webElement;
        } catch (Exception e) {
            logger.error("元素定位失败");
        }
        return null;
    }

    /** 根据页面关键字+元素关键字来获取元素
     * @param pageKeyword  页面关键字
     * @param elementKeyword   元素关键字
     * @return
     */

    public WebElement getElement(String pageKeyword, String elementKeyword) {
        //根据页面关键字和元素关键字拿到ui库中的uiElement对象
        UIElement uiElement = UILibraryUtil.getUIElement(pageKeyword, elementKeyword);
        //通过拿到的UIElement对象，取出by和value属性值来判断通过什么方式来定位页面元素
        String by = uiElement.getBy();
        String value = uiElement.getValue();
        logger.info("根据{by:"+by+",value:"+value+"}来定位【"+pageKeyword+"】页面的【"+elementKeyword+"】元素");
        By locator = null;
        //通过什么选择器来定位元素，取决于配的by是什么
        if ("id".equals(by)) {
            locator = By.id(value);
        } else if ("name".equals(by)) {
            locator = By.name(value);
        } else if ("xpath".equals(by)) {
            locator = By.xpath(value);
        } else if ("className".equals(by)) {
            locator = By.className(value);
        } else if ("linkText".equals(by)) {
            locator = By.linkText(value);
        } else if ("tagName".equals(by)) {
            locator = By.tagName(value);
        } else if ("cssSelector".equals(by)) {
            locator = By.cssSelector(value);
        }
        return waitElement(locator);
    }


    /**url等待
     * @param part
     * @return
     */
    //封装显示等待，出现想要的url才会停止
    public boolean urlPresenceContent(String part) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            boolean flag = wait.until(ExpectedConditions.urlContains(part));
            return flag;
        } catch (Exception e) {
            System.out.println("超时了");
        }
        return false;
    }
/** 写入数据
 * @param element  页面元素
 * @param value  值
 */
        public void sendKeys(WebElement element,String value){
            logger.info("向【"+element+"】中写入数据：【"+value+"】");
            element.clear();
            element.sendKeys(value);

        /*
        try {
            if (element.isEnabled()) {
                element.clear();
                element.sendKeys(value);
                logger.info("元素赋值内容："+value+"，元素："+element.toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("元素赋值出现异常：\n"+e.getMessage());
        }
        */

        }

}
