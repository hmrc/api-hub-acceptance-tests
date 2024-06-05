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

package uk.gov.hmrc.test.ui.pages2.addanapi

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.addanapi.SelectEndpointsPage._
import uk.gov.hmrc.test.ui.pages2.addanapi.SelectEndpointsPage.elements._
import uk.gov.hmrc.test.ui.pages2.{ApiHubBasePage, PageReadyTest, UrlPageReadyTest}

class SelectEndpointsPage extends ApiHubBasePage(pageReadyTest) {

  def selectAllEndpoints(): ReviewUsagePolicyPage = {
    findElements(By.cssSelector(".govuk-checkboxes__input"))
      .filter(!_.isSelected)
      .foreach(_.click())
    click(continueButton)
    ReviewUsagePolicyPage()
  }

}

object SelectEndpointsPage {

  val pageReadyTest: PageReadyTest = UrlPageReadyTest("apis/add-an-api/select-endpoints")

  object elements {
    val continueButton: By = By.id("continueButton")
  }

  def apply(): SelectEndpointsPage = {
    new SelectEndpointsPage()
  }

}
