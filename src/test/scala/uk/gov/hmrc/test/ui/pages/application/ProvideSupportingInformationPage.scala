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
import uk.gov.hmrc.test.ui.pages.application.ProvideSupportingInformationPage._
import uk.gov.hmrc.test.ui.pages.application.ProvideSupportingInformationPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class ProvideSupportingInformationPage extends BasePage[ProvideSupportingInformationPage](pageReadyTest) {

  def setSupportingInformation(information: String): RequestProductionAccessCYAPage = {
    sendKeys(supportingInformation, information)
    click(continueButton)
    RequestProductionAccessCYAPage()
  }

}

object ProvideSupportingInformationPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.questionPage.url("application/request-production-access/supporting-information")

  object elements {
    val supportingInformation: By = By.id("value")
    val continueButton: By = By.id("continueButton")
  }

  def apply(): ProvideSupportingInformationPage = {
    new ProvideSupportingInformationPage()
  }

}
