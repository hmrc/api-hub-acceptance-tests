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
import uk.gov.hmrc.test.ui.pages.createapi.CreateApiPage.elements.getStartedButton
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode, SharedState}

class CreateApiPage(sharedState: SharedState, mode: Mode) extends BasePage[CreateApiPage](CreateApiPage.pageReadyTest(mode)) {

  def getStarted(): SelectOwningTeamPage = {
    click(getStartedButton)
    SelectOwningTeamPage(sharedState, mode)
  }
}

object CreateApiPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("my-apis/produce/before-you-start", mode)
  }

  object elements {
    val getStartedButton: By = By.id("getStartedButton")
  }

  def apply(sharedState: SharedState, mode: Mode = NormalMode): CreateApiPage = {
    new CreateApiPage(sharedState, mode)
  }

}
