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

package uk.gov.hmrc.test.ui.pages.application

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.application.RequestProductionAccessSuccessPage._
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationSuccessPage.elements.{applicationIdAttribute, viewApplicationLink}
import uk.gov.hmrc.test.ui.pages.{ApiHubMenu, BasePage, PageReadyTest, PageReadyTests}

class RequestProductionAccessSuccessPage extends BasePage[RequestProductionAccessSuccessPage](pageReadyTest) with ApiHubMenu {

  def viewApplication(): ApplicationDetailsPage = {
    val applicationId = getApplicationId
    findElement(viewApplicationLink).click()
    ApplicationDetailsPage(applicationId)
  }

  def getApplicationId: String = {
    findElement(viewApplicationLink).getAttribute(applicationIdAttribute)
  }

}

object RequestProductionAccessSuccessPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("application/request-access/success")

  object elements {
    val viewApplicationLink: By = By.id("viewApplicationLink")
    val applicationIdAttribute: String = "data-application-id"
  }

  def apply(): RequestProductionAccessSuccessPage = {
    new RequestProductionAccessSuccessPage()
  }

}
