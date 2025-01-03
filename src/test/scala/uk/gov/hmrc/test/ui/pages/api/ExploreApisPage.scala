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

package uk.gov.hmrc.test.ui.pages.api

import org.openqa.selenium.{By, WebElement}
import uk.gov.hmrc.test.ui.pages.api.ExploreApisPage._
import uk.gov.hmrc.test.ui.pages.api.ExploreApisPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

import scala.util.Random

class ExploreApisPage extends BasePage[ExploreApisPage](pageReadyTest) {

  private val random = new Random(System.currentTimeMillis())

  def selectRandomApi(): ApiDetailsPage = {
    val apiLinks = getApiDetailLinks
    val apiIndex = random.nextInt(apiLinks.size)
    val apiId = apiLinks(apiIndex).getAttribute(apiIdAttribute)
    apiLinks(apiIndex).click()
    ApiDetailsPage(apiId)
  }

  def selectApiByTitle(title: String): ApiDetailsPage = {
    getApiDetailLinks.find(_.getText.equals(title)) match {
      case Some(link) =>
        val apiId = link.getAttribute(apiIdAttribute)
        link.click()
        ApiDetailsPage(apiId)
      case _ =>
        throw new IllegalArgumentException(s"Cannot find an API with title $title")
    }
  }

  private def getApiDetailLinks: Seq[WebElement] = {
    findElements(apiLink).filter(_.isDisplayed)
  }

  def getApiHeader: Option[String] = {
    findElements(apiHeader).headOption.map(_.getText)
  }
}

object ExploreApisPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.allOf(
    PageReadyTests.apiHubPage.url("apis"),
    PageReadyTests.element.locator(apiResultsSize)
  )

  object elements {
    val apiIdAttribute = "data-api-id"
    val apiLink: By = By.cssSelector(s"[$apiIdAttribute]")
    val apiResultsSize = By.id("apiResultsSize")
    val apiHeader: By = By.cssSelector("h2.govuk-heading-l")
  }

  def apply(): ExploreApisPage = {
    new ExploreApisPage()
  }

}
