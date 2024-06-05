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
import uk.gov.hmrc.test.ui.pages2.registerapplication.AddTeamMembersPage._
import uk.gov.hmrc.test.ui.pages2.registerapplication.AddTeamMembersPage.elements._
import uk.gov.hmrc.test.ui.pages2.{ApiHubBasePage, PageReadyTest, UrlPageReadyTest}

class AddTeamMembersPage extends ApiHubBasePage(pageReadyTest) {

  def addTeamMembers(): Unit = {
    click(yesRadio)
    click(continueButton)
  }

  def doNotAddTeamMembers(): CheckYourAnswersPage = {
    click(noRadio)
    click(continueButton)
    CheckYourAnswersPage()
  }

}

object AddTeamMembersPage {

  val pageReadyTest: PageReadyTest = UrlPageReadyTest("application/register/add-team-members")

  object elements  {
    val yesRadio: By = By.id("value")
    val noRadio: By = By.id("value-no")
    val continueButton: By = By.id("continueButton")
  }

  def apply(): AddTeamMembersPage = {
    new AddTeamMembersPage()
  }

}
