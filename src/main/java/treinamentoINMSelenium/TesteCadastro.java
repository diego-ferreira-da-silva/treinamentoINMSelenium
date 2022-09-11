package treinamentoINMSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TesteCadastro {

    private WebDriver driver;


    @BeforeTest
    public void inicializa(){
        driver = new ChromeDriver();
    }

    @Test
    public void efetuarCadastroComSucessoSiteBatista(){
        //Abrindo o site para automação de um cadastro, na tela de inserção de dados.
        driver.get("https://automacaocombatista.herokuapp.com/users/new");

        //inserindo informações, localizando por nome e id
        driver.findElement(By.name("user[name]")).sendKeys("Diego");
        driver.findElement(By.name("user[lastname]")).sendKeys("Ferreira");
        driver.findElement(By.id("user_email")).sendKeys("teste@teste.com");
        driver.findElement(By.id("user_address")).sendKeys("Restaurante no fim do universo");
        driver.findElement(By.id("user_university")).sendKeys("Life University");
        driver.findElement(By.name("user[profile]")).sendKeys("QA Automation Jr");
        driver.findElement(By.id("user_gender")).sendKeys("Masculino");
        driver.findElement(By.id("user_age")).sendKeys("29");

        //Click no botão de enviar cadastro e localizando através de um Xpath
        driver.findElement(By.xpath("//div[@class='actions btn waves-effect green']")).click();

        //JUnit para comparação de resultados
        Assert.assertEquals("Usuário Criado com sucesso", driver.findElement(By.xpath("//p[@id='notice']")).getText());

    }



    //Neste teste, utilizei um servidor em docker para a automação.
    @Test
    public void validarLoginSemSucessoRocklov() throws InterruptedException {

        //Abrindo página na tela de login
        driver.get("http://rocklov-web:3000");

        //Preenchendo login com dados não existentes
        driver.findElement(By.id("email")).sendKeys("testefalha@teste.com");
        driver.findElement(By.id("password")).sendKeys("senhafake");
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();


        Thread.currentThread().sleep(200);
        //Comparação com JUnit de mensagem com falha no Login
        Assert.assertEquals("Usuário e/ou senha inválidos.", driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div")).getText());
        //Assert.assertEquals("Usuário e/ou senha inválidos.", driver.findElement(By.xpath("//div[@class='alert alert-dark']")).getText());
    }

    @AfterTest
    public void Finaliza(){
        driver.quit();
    }
}
