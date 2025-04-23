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
import uk.gov.hmrc.test.ui.pages.Journeys.clear
import uk.gov.hmrc.test.ui.pages.createapi.EnterOasPage.elements.{continueButton, oasEditor}
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode, OasUtilities}

class EnterOasPage(mode: Mode) extends BasePage[EnterOasPage](EnterOasPage.pageReadyTest(mode)) {

  def setOas(): ApiShortDescriptionPage = {
    clear(oasEditor)
    sendKeys(oasEditor, OasUtilities.defaultOasWith("mike", "9.9.9"))
    click(continueButton)
    ApiShortDescriptionPage()
  }

}

object EnterOasPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("my-apis/produce/enter-oas", mode)
  }

  object elements {
    val continueButton: By = By.cssSelector("govuk-button")
    val oasEditor: By = By.cssSelector("ace_content")
  }

  def apply(mode: Mode = NormalMode): EnterOasPage = {
    new EnterOasPage(mode)
  }

}
