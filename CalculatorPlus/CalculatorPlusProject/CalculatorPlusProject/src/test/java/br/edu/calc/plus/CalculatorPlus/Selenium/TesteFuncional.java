package br.edu.calc.plus.CalculatorPlus.Selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TesteFuncional {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp(){

        /*System.setProperty("webdrive.chrome.driver",
                "br/edu/calc/plus/CalculatorPlus/Selenium/chromedriver-win64/chromedriver.exe"
        );*/

        System.setProperty("webdrive.chrome.driver","ChromeDriver");

        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            Logger.getLogger(TesteFuncional.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }

    @Test
    @DisplayName("Testa cadastro")
    public void Teste001() throws Exception{
        webDriver.get("http://localhost:9090/user");
        webDriver.manage().window().setSize(new Dimension(1025, 900));
        webDriver.findElement(By.id("cpNome")).click();
        webDriver.findElement(By.id("cpNome")).sendKeys("Carol");
        webDriver.findElement(By.id("cpLogin")).sendKeys("carol");
        webDriver.findElement(By.id("cpSenha")).sendKeys("123456");
        webDriver.findElement(By.id("cpEmail")).sendKeys("carol@teste");
        webDriver.findElement(By.id("cpCidade")).sendKeys("juiz de fora");
        webDriver.findElement(By.id("cpData")).click();
        webDriver.findElement(By.id("cpData")).sendKeys("23-12-1998");
        webDriver.findElement(By.cssSelector(".btn")).click();
        webDriver.findElement(By.cssSelector("span:nth-child(4)")).click();
        webDriver.findElement(By.cssSelector("span:nth-child(4)")).click();
        webDriver.findElement(By.cssSelector("li:nth-child(2) p")).click();
        webDriver.findElement(By.id("username")).click();
        webDriver.findElement(By.id("username")).sendKeys("carol");
        webDriver.findElement(By.id("password")).sendKeys("123456");
        webDriver.findElement(By.cssSelector(".btn")).click();
        webDriver.findElement(By.cssSelector(".navbar > .container-fluid")).click();
        assertThat(webDriver.findElement(By.cssSelector(".navbar-brand > span")).getText(), is("Bem Vindo, Carol"));
    }

    @Test
    @DisplayName("Testa Login")
    public void Teste002() throws Exception{
        webDriver.get("http://localhost:9090/");
        webDriver.manage().window().setSize(new Dimension(1024, 900));
        webDriver.findElement(By.cssSelector("li:nth-child(2) p")).click();
        webDriver.findElement(By.id("username")).click();
        webDriver.findElement(By.id("username")).sendKeys("carol");
        webDriver.findElement(By.id("password")).sendKeys("123456");
        webDriver.findElement(By.cssSelector(".btn")).click();
        webDriver.findElement(By.cssSelector(".navbar > .container-fluid")).click();
        assertThat(webDriver.findElement(By.cssSelector(".navbar-brand > span")).getText(), is("Bem Vindo, Carol"));
        webDriver.findElement(By.cssSelector(".nav-link")).click();
    }

    @Test
    @DisplayName("Testa acesso a competição")
    public void Teste003() throws Exception{
        webDriver.get("http://localhost:9090/home");
        webDriver.manage().window().setSize(new Dimension(1025, 900));
        webDriver.findElement(By.cssSelector("li:nth-child(2) p")).click();
        webDriver.findElement(By.id("username")).click();
        webDriver.findElement(By.id("username")).sendKeys("carol");
        webDriver.findElement(By.id("password")).sendKeys("123456");
        webDriver.findElement(By.cssSelector(".btn")).click();
        webDriver.findElement(By.cssSelector("li:nth-child(2) p")).click();
        webDriver.findElement(By.cssSelector(".card-title")).click();
        webDriver.findElement(By.cssSelector(".btn")).click();
        {
            WebElement element = webDriver.findElement(By.cssSelector(".layout-content"));
            Actions builder = new Actions(webDriver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = webDriver.findElement(By.cssSelector(".layout-content"));
            Actions builder = new Actions(webDriver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = webDriver.findElement(By.cssSelector(".layout-content"));
            Actions builder = new Actions(webDriver);
            builder.moveToElement(element).release().perform();
        }
        webDriver.findElement(By.cssSelector(".layout-content")).click();
        assertThat(webDriver.findElement(By.cssSelector(".title")).getText(), is("Pergunta 1"));
    }

    @Test
    @DisplayName("Testa acesso ao ranking")
    public void Teste004() throws Exception{
        webDriver.get("http://localhost:9090/");
        webDriver.manage().window().setSize(new Dimension(1025, 900));
        webDriver.findElement(By.cssSelector("li:nth-child(2) p")).click();
        webDriver.findElement(By.id("username")).sendKeys("carol");
        webDriver.findElement(By.id("password")).sendKeys("123456");
        webDriver.findElement(By.cssSelector(".btn")).click();
        webDriver.findElement(By.cssSelector("li:nth-child(3) p")).click();
        webDriver.findElement(By.cssSelector(".card-title")).click();
        assertThat(webDriver.findElement(By.cssSelector(".card-title")).getText(), is("Ranking Geral"));
    }

    @Test
    @DisplayName("Testa o logout")
    public void Teste005() throws Exception{
        webDriver.get("http://localhost:9090/");
        webDriver.manage().window().setSize(new Dimension(1025, 900));
        webDriver.findElement(By.cssSelector("li:nth-child(2) p")).click();
        webDriver.findElement(By.id("username")).sendKeys("carol");
        webDriver.findElement(By.id("password")).sendKeys("123456");
        webDriver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        //webDriver.findElement(By.cssSelector(".nav-link > p")).click();
    }

}
