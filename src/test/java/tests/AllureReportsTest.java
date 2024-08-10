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
        $("#issues-tab").click();
        $(".application-main ").shouldHave(Condition.text("Issue1"));
    }

    @Test
    void allureReportsTestWithLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open remote repository", () -> {
            open("https://github.com/Anth0nySt/AllureReports");
        });
        step("Click issues tab",() -> {
            $("#issues-tab").click();
        });
        step("Check issue number", () -> {
            $(".application-main ").shouldHave(Condition.text("Issue1"));
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
        steps.openIssues();
        steps.shouldSeeIssue();
    }
}