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
import uk.gov.hmrc.test.ui.pages.createapi.ReviewApiNamePage.elements.{confirmCheckBox, continueButton}
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode}

class ReviewApiNamePage(mode: Mode) extends BasePage[ReviewApiNamePage](ReviewApiNamePage.pageReadyTest(mode)) {

  def confirmApiName(): ConfigurePrefixesPage = {
    click(confirmCheckBox)
    click(continueButton)
    ConfigurePrefixesPage()
  }

}

object ReviewApiNamePage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("my-apis/produce/review-name-description", mode)
  }

  object elements {
    val confirmCheckBox: By = By.id("value_0")
    val continueButton: By = By.cssSelector("button[type=submit]")
  }

  def apply(mode: Mode = NormalMode): ReviewApiNamePage = {
    new ReviewApiNamePage(mode)
  }

}
