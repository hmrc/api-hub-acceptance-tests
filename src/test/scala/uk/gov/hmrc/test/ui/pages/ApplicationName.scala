package uk.gov.hmrc.test.ui.pages

import faker.Faker
import org.openqa.selenium.By

object ApplicationName extends BasePage {
  val appNameLcr = "value"
  val continue = ".govuk-button"
  val randAppName: String = Faker.ar.loremWord()

  def fillInApplicationName(input: String): CheckYourAnswers.type = {
    driver.findElement(By.id(appNameLcr)).sendKeys(input)
    driver.findElement(By.cssSelector(continue)).click()
    CheckYourAnswers
  }
}
