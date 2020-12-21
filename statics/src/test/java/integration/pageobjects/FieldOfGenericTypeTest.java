package integration.pageobjects;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import integration.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

public class FieldOfGenericTypeTest extends IntegrationTest {
  @BeforeEach
  void openTestPage() {
    openFile("page_with_selects_without_jquery.html");
  }

  @Test
  void injectsFoundSelenideElementAsSelf() {
    DummyPage page = page(DummyPage.class);
    assertThat(page.body.selects).hasSize(3);
    assertThat(page.body.selects.get(0)).isInstanceOf(ElementsContainer.class);
    assertThat(((ElementsContainer) page.body.selects.get(0)).getSelf()).isEqualTo($("select[name=domain]"));
    assertThat(((ElementsContainer) page.body.selects.get(1)).getSelf()).isEqualTo($("select#hero"));
    assertThat(((ElementsContainer) page.body.selects.get(2)).getSelf()).isEqualTo($("select#gender"));
  }

  static class DummyTypedElement<T> extends ElementsContainer {
    @FindBy(tagName = "select")
    private List<T> selects;
  }

  static class DummyPage {
    @FindBy(tagName = "body")
    private DummyTypedElement<SelenideElement> body;
  }
}
