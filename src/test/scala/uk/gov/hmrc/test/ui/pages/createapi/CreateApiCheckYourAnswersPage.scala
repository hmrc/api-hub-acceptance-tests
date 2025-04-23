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

package uk.gov.hmrc.test.ui.pages.createapi

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.addanapi.AddAnApiSuccessPage
import uk.gov.hmrc.test.ui.pages.createapi.CreateApiCheckYourAnswersPage.elements.{confirmCheckBox, continueButton}
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode}

class CreateApiCheckYourAnswersPage(mode: Mode) extends BasePage[CreateApiCheckYourAnswersPage](CreateApiCheckYourAnswersPage.pageReadyTest(mode)) {

  def confirmAnswers(): SelectTestEgressPage = {
    click(confirmCheckBox)
    click(continueButton)
    SelectTestEgressPage()
  }

  def completeJourney(): AddAnApiSuccessPage = {
    click(confirmCheckBox)
    click(continueButton)
    AddAnApiSuccessPage()
  }

}

object CreateApiCheckYourAnswersPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("my-apis/produce/check-your-answers", mode)
  }

  object elements {
    val confirmCheckBox: By = By.id("value")
    val continueButton: By = By.cssSelector("govuk-button")
  }

  def apply(mode: Mode = NormalMode): CreateApiCheckYourAnswersPage = {
    new CreateApiCheckYourAnswersPage(mode)
  }

}
