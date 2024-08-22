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

package uk.gov.hmrc.test.ui.pages.registerapplication

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationNamePage._
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationNamePage.elements._
import uk.gov.hmrc.test.ui.utilities.Mode

class RegisterApplicationNamePage(mode: Mode) extends BasePage[RegisterApplicationNamePage](pageReadyTest(mode)) {

  def setApplicationNameNormalMode(applicationName: String): RegisterApplicationTeamPage = {
    sendKeys(applicationNameInput, applicationName)
    click(continueButton)
    RegisterApplicationTeamPage(mode)
  }

  def setApplicationNameCheckMode(applicationName: String): CheckYourAnswersPage = {
    sendKeys(applicationNameInput, applicationName)
    click(continueButton)
    CheckYourAnswersPage()
  }

}

object RegisterApplicationNamePage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("application/register/name", mode)
  }

  object elements {
    val applicationNameInput: By = By.id("value")
    val continueButton: By = By.id("continueButton")
  }

  def apply(mode: Mode): RegisterApplicationNamePage = {
    new RegisterApplicationNamePage(mode)
  }

}
