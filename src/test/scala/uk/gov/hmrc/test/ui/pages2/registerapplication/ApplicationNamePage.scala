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

package uk.gov.hmrc.test.ui.pages2.registerapplication

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.registerapplication.ApplicationNamePage._
import uk.gov.hmrc.test.ui.pages2.registerapplication.ApplicationNamePage.elements._
import uk.gov.hmrc.test.ui.pages2.{BasePage, PageReadyTest, UrlPageReadyTest}

class ApplicationNamePage extends BasePage[ApplicationNamePage](pageReadyTest) {

  def setApplicationName(applicationName: String): AddTeamMembersPage = {
    sendKeys(applicationNameInput, applicationName)
    click(continueButton)
    AddTeamMembersPage()
  }

}

object ApplicationNamePage {

  val pageReadyTest: PageReadyTest = UrlPageReadyTest("application/register/application-name")

  object elements {
    val applicationNameInput: By = By.id("value")
    val continueButton: By = By.id("continueButton")
  }

  def apply(): ApplicationNamePage = {
    new ApplicationNamePage()
  }

}
