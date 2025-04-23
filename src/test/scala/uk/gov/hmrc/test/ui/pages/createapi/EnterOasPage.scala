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

import org.openqa.selenium.{By, Keys}
import uk.gov.hmrc.test.ui.pages.createapi.EnterOasPage.elements.{continueButton, oasEditor}
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode}

class EnterOasPage(mode: Mode) extends BasePage[EnterOasPage](EnterOasPage.pageReadyTest(mode)) {

  def selectOasEditor(): EnterOasPage = {
    click(oasEditor)
    EnterOasPage()
  }

  def setOasTitle(title: String): EnterOasPage = {
    sendKeys(oasEditor, Keys.HOME.toString, false)
    sendKeys(oasEditor, times(2, Keys.ARROW_DOWN), false)
    sendKeys(oasEditor, times(10, Keys.ARROW_RIGHT), false)
    sendKeys(oasEditor, title, false)
    EnterOasPage()
  }

  def setOasVersion(version: String): EnterOasPage = {
    sendKeys(oasEditor, Keys.HOME.toString, false)
    sendKeys(oasEditor, times(11, Keys.ARROW_DOWN), false)
    sendKeys(oasEditor, times(9, Keys.ARROW_RIGHT), false)
    sendKeys(oasEditor, times(5, Keys.DELETE), false)
    sendKeys(oasEditor, version, false)
    EnterOasPage()
  }

  def continue(): ApiShortDescriptionPage = {
    click(continueButton)
    ApiShortDescriptionPage()
  }

  private def times(times: Int, key: Keys): String = {
    (Range.inclusive(1, times) map (_ => key.toString)).mkString
  }

}

object EnterOasPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("my-apis/produce/enter-oas", mode)
  }

  object elements {
    val continueButton: By = By.cssSelector("button[type = submit]")
    val oasEditor: By = By.className("ace_text-input")
  }

  def apply(mode: Mode = NormalMode): EnterOasPage = {
    new EnterOasPage(mode)
  }

}
