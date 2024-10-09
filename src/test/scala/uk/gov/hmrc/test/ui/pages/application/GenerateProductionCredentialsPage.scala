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
import uk.gov.hmrc.test.ui.pages.application.GenerateProductionCredentialsPage._
import uk.gov.hmrc.test.ui.pages.application.GenerateProductionCredentialsPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

//import uk.gov.hmrc.test.ui.pages.application.GenerateProductionCredentialsPage.elements.{confirmandcontinue, confirmcheckbox}

class GenerateProductionCredentialsPage(id: String) extends BasePage[GenerateProductionCredentialsPage](pageReadyTest(id)) {


  def selectconfirmcheckbox(): GenerateProductionCredentialsPage = {
    click(confirmcheckbox)
     new GenerateProductionCredentialsPage(id)
  }

  def clickconfirmandcontinue(): GenerateProductionCredentialsPage = {
    click(confirmandcontinue)
    new GenerateProductionCredentialsPage(id)
  }


}

object GenerateProductionCredentialsPage {

  def pageReadyTest(id: String): PageReadyTest = {
    PageReadyTests.apiHubPage.url(s"application/add-credential-checklist/$id")

  }

  object elements {
//    val confirmcheckbox: By = By.id("value_0")
//    val confirmandcontinue: By = By.id("")
    val confirmcheckbox: By = By.id("confirmcheckbox")
    val confirmandcontinue: By = By.id("confirmandcontinue")
  }

  def apply(id: String): GenerateProductionCredentialsPage = {
    new GenerateProductionCredentialsPage(id)
  }

}
