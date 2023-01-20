package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object CheckYourAnswers extends BasePage {
  val registeredAppName = ".govuk-summary-list__row .govuk-summary-list__value"

  def getRegisteredApplicationName(): String = {
    driver.findElement(By.cssSelector(registeredAppName)).getText
  }
}
