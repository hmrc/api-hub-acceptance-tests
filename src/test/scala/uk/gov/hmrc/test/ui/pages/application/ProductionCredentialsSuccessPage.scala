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
//import uk.gov.hmrc.test.ui.pages.application.GenerateProductionCredentialsPage.elements.{confirmandcontinue, confirmcheckbox}
//import uk.gov.hmrc.test.ui.pages.application.GenerateProductionCredentialsPage.pageReadyTest
import uk.gov.hmrc.test.ui.pages.application.ProductionCredentialsSuccessPage.pageReadyTest
import uk.gov.hmrc.test.ui.pages.application.ProductionCredentialsSuccessPage._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
class ProductionCredentialsSuccessPage(id: String) extends BasePage[ProductionCredentialsSuccessPage](pageReadyTest(id)) {

  }
object ProductionCredentialsSuccessPage {

//  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url(s"application/add-credential-success/$id")
  def pageReadyTest(id: String): PageReadyTest = {
    PageReadyTests.apiHubPage.url(s"application/add-credential-success/$id")

  }
//  object elements {
//    //    val confirmcheckbox: By = By.id("value_0")
//    //    val confirmandcontinue: By = By.id("")
//    val confirmcheckbox: By = By.id("value_0")
//    val confirmandcontinue: By = By.id("confirmAndContinueButton")
//    val viewApplicationLink: By = By.id("XXXXXXXXX")
//    //    val addProductionCredentialButton: By = By.id("addProductionCredentialButton")
//  }
  def apply(id: String): ProductionCredentialsSuccessPage = {
    new ProductionCredentialsSuccessPage(id)
  }

}

