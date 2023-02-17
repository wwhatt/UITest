package cases;

import util.RangeDatabyPOI;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.Base;

public class LoginTest extends Base {


    @DataProvider(name="qsm")
    public Object[][] getExcelDate() throws Exception{
        String filepath = "C:/Users/Tester/Desktop/test/test.xlsx";
        Object[][]  array = RangeDatabyPOI.poiRangeData(filepath);
        System.out.println(array.toString()+"----------------------");
        return array;
    }

    @Parameters("url")
    @BeforeTest()
    public void tourl(String url){
        to(url);
    }

    @Test(dataProvider = "qsm")
    public void login(String name,String password) throws InterruptedException {

//        driver.findElement(By.name("acct")).sendKeys(name);
//        driver.findElement(By.name("password")).sendKeys(password);
        Thread.sleep(2000);
        sendKeys(getElement("登陆页","账号"),name);
        sendKeys(getElement("登陆页","密码"),password);



    }

}
