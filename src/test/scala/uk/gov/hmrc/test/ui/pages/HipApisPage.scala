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

import org.openqa.selenium.{By, WebElement}

import java.security.SecureRandom
import java.util

object HipApisPage extends BasePage {
  private val allApis: String  = ".govuk-body a.govuk-link"
  private val apiNames: String = ".api-panel a"
  var chosenApi: String        = _

  //TODO: for now just the first api should be added, but consider adding by name instead.
  def chooseApiByText(input: String): Unit =
    waitForElementsPresent(By.cssSelector(apiNames))
      .stream()
      .filter(item => item.getText.trim.toLowerCase() == input.toLowerCase())
      .findFirst()
      .get()
      .click()

  def numberOfApis(): util.List[WebElement] = {
    waitForElementsPresent(By.cssSelector(allApis))
  }

  def chooseApiByIndex(index: Integer): this.type = {
    val elements: util.List[WebElement] = numberOfApis()
    chosenApi = elements.get(index).getText.trim()
    elements.get(index).click()
    this
  }

  def selectRandomApi(): ApiDetailsPage.type = {
    val apiIndex = new SecureRandom().nextInt(numberOfApis().size())
    chooseApiByIndex(apiIndex)
    ApiDetailsPage
  }

  def getSelectedApiName: String =
    chosenApi

}
