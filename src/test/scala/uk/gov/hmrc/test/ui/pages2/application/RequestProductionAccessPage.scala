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

package uk.gov.hmrc.test.ui.pages2.application

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.application.RequestProductionAccessPage._
import uk.gov.hmrc.test.ui.pages2.application.RequestProductionAccessPage.elements._
import uk.gov.hmrc.test.ui.pages2.{ApiHubBasePage, PageReadyTest, UrlPageReadyTest}

class RequestProductionAccessPage extends ApiHubBasePage(pageReadyTest) {

  def confirmUsagePolicies(): ProvideSupportingInformationPage = {
    click(confirmationCheckbox)
    click(continueButton)
    ProvideSupportingInformationPage()
  }

}

object RequestProductionAccessPage {

  val pageReadyTest: PageReadyTest = UrlPageReadyTest("application/request-production-access")

  object elements {
    val confirmationCheckbox: By = By.id("accept_0")
    val continueButton: By = By.id("continueButton")
  }

  def apply(): RequestProductionAccessPage = {
    new RequestProductionAccessPage()
  }

}