package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import tests.steps.WebSteps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class AllureReportsTest {

    @Test
    void allureReportsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/Anth0nySt/AllureReports");
            $("#issues-tab").shouldBe(Condition.visible);
    }

    @Test
    void allureReportsTestWithLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open remote repository", () -> {
        });
        open("https://github.com/Anth0nySt/AllureReports");
        step("Check issues tab", () -> {
            $("#issues-tab").shouldBe(Condition.visible);
        });
        Allure.getLifecycle().addAttachment(
                "Page Source",
                "text/html",
                "html",
                WebDriverRunner.getWebDriver().getPageSource().getBytes()
        );
    }

    @Test
    void allureReportsTestWithSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openBrowserPage();
        steps.attachScreenshot();
        steps.shouldSeeIssue();
    }
}
