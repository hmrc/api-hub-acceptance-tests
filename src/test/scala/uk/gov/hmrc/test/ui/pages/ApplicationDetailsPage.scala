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

object ApplicationDetailsPage extends BasePage {
  val applicationName  = "div.govuk-grid-column-two-thirds > div:nth-child(2) > div.govuk-grid-column-three-quarters > p"
  val addApisLink      = ".govuk-body a[href='/api-hub/apis']"
  val addedApiNameRows = "th[scope='row']"

  def getApplicationName: String =
    driver.findElement(By.cssSelector(applicationName)).getText.trim()

  def addApis(): HipApisPage.type = {
    driver.findElement(By.cssSelector(addApisLink)).click()
    HipApisPage
  }

  def isApiNameAddedToApplication(input: String): Boolean = {
    val elements: util.List[WebElement] = driver.findElements(By.cssSelector(addedApiNameRows))
    elements.stream().forEach((ele: WebElement) => println("ele text: " + ele.getText))
    elements.stream().anyMatch((element: WebElement) => element.getText.trim().equals(input));
  }
}
