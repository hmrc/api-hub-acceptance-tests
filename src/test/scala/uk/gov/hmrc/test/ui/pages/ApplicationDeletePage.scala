/*
 * Copyright 2024 HM Revenue & Customs
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

object ApplicationDeletePage extends BasePage {
  private val confirmCheckbox          = "#value_0"
  private val acceptAndContinue        = ".govuk-button--warning"
  private val cancelBtn                = ".govuk-button--secondary"
  private val deleteApplicationWarning = ".govuk-error-summary"
  private val valueError               = "#value-error"
  private val yourApplicationsLink     = ".govuk-grid-column-two-thirds p.govuk-body a[href='/api-hub']"

  def confirmDeletionOfApplication(): Unit = {
    driver.findElement(By.cssSelector(confirmCheckbox)).click()
    clickAcceptAndContinueButton()
  }

  def cancel(): Unit =
    driver.findElement(By.cssSelector(cancelBtn)).click()

  def clickAcceptAndContinueButton(): Unit =
    driver.findElement(By.cssSelector(acceptAndContinue)).click()

  def isConfirmCheckboxDisplayed(): Boolean =
    driver.findElement(By.cssSelector(valueError)).isDisplayed

  def isDeleteApplicationErrorDisplayed(): Boolean =
    driver.findElement(By.cssSelector(deleteApplicationWarning)).isDisplayed

  def returnToYourApplications: Unit =
    driver.findElement(By.cssSelector(yourApplicationsLink)).click()

}
