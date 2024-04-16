package br.com.webtask.aula.Funcional;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;

@ActiveProfiles("homolog")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FuncionalTeste {

    private WebDriver webDriver;

    @BeforeEach
    public void setUp(){
        //System.setProperties("webdriver.chrome.driver","chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        try{
            Thread.sleep(4000);
        }catch (InterruptedException ex){
            Logger.getLogger(FuncionalTeste.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }

    @Test
    @DisplayName("Testa Login")
    public void Teste001() throws Exception{
        webDriver.get("http://localhost:9000/login?logout");
        webDriver.manage().window().setSize(new Dimension(697, 727));
        webDriver.findElement(By.id("username")).click();
        webDriver.findElement(By.id("username")).sendKeys("123");
        webDriver.findElement(By.id("password")).sendKeys("123");
        webDriver.findElement(By.cssSelector(".login100-form-btn")).click();
        webDriver.findElement(By.cssSelector("h2")).click();
        Assertions.assertEquals(webDriver.findElement(By.cssSelector("label")).getText(),is("Olá, admin!"));
    }

    @Test
    @DisplayName("Testa cadastro tarefa")
    public void Teste002() throws Exception{
        webDriver.get("http://localhost:9000/");
        webDriver.manage().window().setSize(new Dimension(697, 720));
        webDriver.findElement(By.linkText("Nova Tarefa")).click();
        webDriver.findElement(By.id("cpNome")).click();
        webDriver.findElement(By.id("cpNome")).sendKeys("Selenium");
        webDriver.findElement(By.id("cpData")).click();
        webDriver.findElement(By.id("cpData")).click();
        webDriver.findElement(By.id("cpData")).click();
        webDriver.findElement(By.id("cpData")).sendKeys("2023-10-15");
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
        webDriver.findElement(By.cssSelector(".alert")).click();
        Assertions.assertEquals(webDriver.findElement(By.cssSelector("strong")).getText(), is("Dados alterados com sucesso."));
    }

}
