package tasks.functional;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    WebDriver driver = new ChromeDriver();

    @BeforeAll
    public static void setup(){
        System.setProperty("webdriver.chrome.driver","/usr/chromedriver");
    }

    @AfterEach
    public void afterEach(){
        driver.close();
    }

    @Test
    public void deveSalvarTarefa() {

        driver.navigate().to("http://localhost:8001/tasks");

        // click add todo
        driver.findElement(By.id("addTodo")).click();

        // insert a description
        driver.findElement(By.id("task")).sendKeys("Task 1");

        // insert a date
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2150");

        // click on save button
        driver.findElement(By.id("saveButton")).click();

        // check success message
        String result = driver.findElement(By.id("message")).getText();
        assertEquals("Success!", result);

    }

    @Test
    public void naoDeveSalvarTarefaComDataNoPassado() {

        driver.navigate().to("http://localhost:8001/tasks");

        // click add todo
        driver.findElement(By.id("addTodo")).click();

        // insert a description
        driver.findElement(By.id("task")).sendKeys("Task 1");

        // insert a date
        driver.findElement(By.id("dueDate")).sendKeys("10/10/1950");

        // click on save button
        driver.findElement(By.id("saveButton")).click();

        // check success message
        String result = driver.findElement(By.id("message")).getText();
        assertEquals("Due date must not be in past", result);

    }

    @Test
    public void naoDeveSalvarTarefaSemData() {

        driver.navigate().to("http://localhost:8001/tasks");

        // click add todo
        driver.findElement(By.id("addTodo")).click();

        // insert a description
        driver.findElement(By.id("task")).sendKeys("Task 1");

        // click on save button
        driver.findElement(By.id("saveButton")).click();

        // check success message
        String result = driver.findElement(By.id("message")).getText();
        assertEquals("Fill the due date", result);

    }


    @Test
    public void naoDeveSalvarTarefaSemDescricao() {

        driver.navigate().to("http://localhost:8001/tasks");

        // click add todo
        driver.findElement(By.id("addTodo")).click();

        // insert a date
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2100");

        // click on save button
        driver.findElement(By.id("saveButton")).click();

        // check success message
        String result = driver.findElement(By.id("message")).getText();
        assertEquals("Fill the task description", result);

    }

}
