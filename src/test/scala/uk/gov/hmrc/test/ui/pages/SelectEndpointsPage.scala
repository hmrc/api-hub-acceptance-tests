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

object SelectEndpointsPage extends BasePage {
  private val allEndpointsCheckboxes: String = ".govuk-checkboxes__input"
  private val continueLink: String           = "button.govuk-button"
  private val errorSummary: String           = ".govuk-error-summary"
  private val errorSummaryMessage: String    = s"$errorSummary .govuk-error-summary__list a"
  private val errorMessage: String           = "#value-error"
  private val scopes: String                 = ".custom-normal-case"

  def getScopeTexts: Array[AnyRef] =
    driver
      .findElements(By.cssSelector(scopes))
      .stream()
      .map(i => i.getText)
      .toArray

  def isErrorSummaryBoxDisplayed: Boolean =
    driver.findElement(By.cssSelector(errorSummary)).isDisplayed

  def getErrorMessage: String =
    driver.findElement(By.cssSelector(errorMessage)).getText.trim

  def getErrorSummaryMessage: String =
    driver.findElement(By.cssSelector(errorSummaryMessage)).getText

  def selectAllEndpoints(): this.type = {
    driver.findElements(By.cssSelector(allEndpointsCheckboxes)).stream().forEach(ele => ele.click())
    this
  }

  def continue(): Unit =
    driver.findElement(By.cssSelector(continueLink)).click()
}
