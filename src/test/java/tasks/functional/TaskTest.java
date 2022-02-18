package tasks.functional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    DesiredCapabilities cap = new DesiredCapabilities(new ChromeOptions());
    WebDriver driver;

    private void setup() throws MalformedURLException {
         driver = new RemoteWebDriver(new URL("http://172.23.0.1:4444/wd/hub"), cap);
    }

    @AfterEach
    public void afterEach() {
        driver.close();
    }

    @Test
    public void deveSalvarTarefa() throws MalformedURLException {
        setup();

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
    public void naoDeveSalvarTarefaComDataNoPassado() throws MalformedURLException {
        setup();

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
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
        setup();

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
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
        setup();

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
