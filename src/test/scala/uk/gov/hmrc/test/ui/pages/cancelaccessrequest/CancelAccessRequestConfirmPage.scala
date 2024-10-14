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

package uk.gov.hmrc.test.ui.pages.cancelaccessrequest

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.cancelaccessrequest.CancelAccessRequestConfirmPage.pageReadyTest
import uk.gov.hmrc.test.ui.pages.cancelaccessrequest.CancelAccessRequestConfirmPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class CancelAccessRequestConfirmPage extends BasePage[CancelAccessRequestConfirmPage](pageReadyTest) {

  def confirmAndContinue(): CancelAccessRequestSuccessPage = {
    click(yesRadio)
    click(continueButton)
    CancelAccessRequestSuccessPage()
  }

}

object CancelAccessRequestConfirmPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("application/cancel-access-request/confirm")

  object elements {
    val yesRadio: By = By.id("value")
    val continueButton: By = By.id("continueButton")
  }

  def apply(): CancelAccessRequestConfirmPage = {
    new CancelAccessRequestConfirmPage()
  }

}
