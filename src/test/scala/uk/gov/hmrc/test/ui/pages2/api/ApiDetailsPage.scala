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

package uk.gov.hmrc.test.ui.pages2.api

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.addanapi.SelectApplicationPage
import uk.gov.hmrc.test.ui.pages2.api.ApiDetailsPage._
import uk.gov.hmrc.test.ui.pages2.api.ApiDetailsPage.elements._
import uk.gov.hmrc.test.ui.pages2.{ApiHubBasePage, PageReadyTest, UrlPageReadyTest}

class ApiDetailsPage(id: String) extends ApiHubBasePage(pageReadyTest(id)) {

  def addToAnApplication(): SelectApplicationPage = {
    click(addToAnApplicationButton)
    SelectApplicationPage()
  }

  def getApiId: String = {
    findElement(details).getAttribute("data-api-id")
  }

  def getApiTitle: String = {
    findElement(details).getAttribute("data-api-title")
  }

}

object ApiDetailsPage {

  def pageReadyTest(id: String): PageReadyTest = UrlPageReadyTest(s"apis/details/$id")

  object elements {
    val addToAnApplicationButton: By = By.id("addToAnApplicationButton")
    val details: By = By.id("details")
  }

  def apply(id: String): ApiDetailsPage = {
    new ApiDetailsPage(id)
  }

}