package uk.gov.hmrc.test.ui.sections

import org.openqa.selenium.{By, WebElement}
import uk.gov.hmrc.test.ui.pages.BasePage

class Header(val rootElement: WebElement) extends BasePage {
  val logo = ".govuk-header__logotype-text"

  def clickLogo(): Unit = {
    rootElement.findElement(By.cssSelector(logo)).click()
  }
}
