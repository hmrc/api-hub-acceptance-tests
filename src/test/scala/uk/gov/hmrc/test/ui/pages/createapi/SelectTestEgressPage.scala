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
import uk.gov.hmrc.test.ui.pages.createapi.SelectTestEgressPage.elements.noEgressButton
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode}

class SelectTestEgressPage(mode: Mode) extends BasePage[SelectTestEgressPage](SelectTestEgressPage.pageReadyTest(mode)) {

  def selectNoTestEgress(): CreateApiCheckYourAnswersPage = {
    click(noEgressButton)
    CreateApiCheckYourAnswersPage()
  }

}

object SelectTestEgressPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("my-apis/produce/change-team-egress", mode)
  }

  object elements {
    val noEgressButton: By = By.cssSelector("button[name=noegress][value=true]")
  }

  def apply(mode: Mode = NormalMode): SelectTestEgressPage = {
    new SelectTestEgressPage(mode)
  }

}
