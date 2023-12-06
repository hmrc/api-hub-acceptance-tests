/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.pages

import faker.Faker
import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.ApplicationDetailsPage.driver

object ProvideSupportingInformationPage extends BasePage {
  val infoTextBox = "#value"
  val continueBtn = "button.govuk-button"

  //TODO: for now just random text, but maybe more flexible to input whatever in future.
  def randomlyFillInTextBoxReason(): this.type = {
    driver.findElement(By.cssSelector(infoTextBox)).sendKeys(Faker.ar.loremParagraph(1))
    this
  }

  def continue(): RequestProductionAccessSuccessPage.type = {
    driver.findElement(By.cssSelector(continueBtn)).click()
    RequestProductionAccessSuccessPage
  }
}
