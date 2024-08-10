package tests.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    @Step("Open remote repository")
    public void openBrowserPage() {
        open("https://github.com/Anth0nySt/AllureReports");

    }
    @Attachment(value = "Screenshot", type = "image/jpg", fileExtension = "jpg")
    public byte[] attachScreenshot () {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Check issues tab")
    public void shouldSeeIssue(){
        $("#issues-tab").shouldBe(Condition.visible);
        attachScreenshot();
    }
}
