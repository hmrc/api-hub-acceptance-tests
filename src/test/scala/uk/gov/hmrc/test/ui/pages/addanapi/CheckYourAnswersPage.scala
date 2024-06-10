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

package uk.gov.hmrc.test.ui.pages.addanapi

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.addanapi.CheckYourAnswersPage._
import uk.gov.hmrc.test.ui.pages.addanapi.CheckYourAnswersPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, UrlPageReadyTest}

class CheckYourAnswersPage extends BasePage[CheckYourAnswersPage](pageReadyTest) {

  def continue(): AddAnApiSuccessPage = {
    click(continueButton)
    AddAnApiSuccessPage()
  }

}

object CheckYourAnswersPage {

  val pageReadyTest: PageReadyTest = UrlPageReadyTest("apis/add-an-api/check-your-answers")

  object elements {
    val continueButton: By = By.id("continueButton")
  }

  def apply(): CheckYourAnswersPage = {
    new CheckYourAnswersPage()
  }

}
