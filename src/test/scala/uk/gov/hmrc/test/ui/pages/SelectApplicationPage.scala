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

import java.util

object SelectApplicationPage extends BasePage {
  val radioButtons = ".govuk-radios .govuk-radios__item .govuk-label"
  val continueLink = ".govuk-grid-column-two-thirds .govuk-button"

  def selectApplicationRadioButton(name: String): this.type = {
    val elements: util.List[WebElement] = driver.findElements(By.cssSelector(radioButtons))
    elements.forEach((element: WebElement) => println(element.getText.trim))
    val result                          =
      elements.stream().filter((element: WebElement) => element.getText.trim == name).findFirst().orElse(null)
    try result.click()
    catch {
      case ex: NoSuchElementException => println("element could not be found")
    }
    this
  }

  def continue(): SelectEndpointsPage.type = {
    driver.findElement(By.cssSelector(continueLink)).click()
    SelectEndpointsPage
  }
}