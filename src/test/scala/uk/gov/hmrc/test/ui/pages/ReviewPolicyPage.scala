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

import org.openqa.selenium.By

object ReviewPolicyPage extends BasePage {
  private val confirmApplicationCheckbox: String = ".govuk-checkboxes__input"
  private val acceptAndContinueBtn: String       = "button.govuk-button"
  private val cancelBtn: String                  = ".govuk-button--secondary"

  def confirmCheckbox(): this.type = {
    driver.findElement(By.cssSelector(confirmApplicationCheckbox)).click()
    this
  }

  def acceptAndContinue(): CheckYourAnswersPage.type = {
    driver.findElement(By.cssSelector(acceptAndContinueBtn)).click()
    CheckYourAnswersPage
  }

  def cancel(): ApiDetailsPage.type = {
    driver.findElement(By.cssSelector(cancelBtn)).click()
    ApiDetailsPage
  }
}
