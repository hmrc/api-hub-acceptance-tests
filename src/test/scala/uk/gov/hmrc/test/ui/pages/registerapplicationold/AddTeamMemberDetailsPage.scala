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

package uk.gov.hmrc.test.ui.pages.registerapplicationold

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.registerapplicationold.AddTeamMemberDetailsPage._
import uk.gov.hmrc.test.ui.pages.registerapplicationold.AddTeamMemberDetailsPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, ErrorSummary, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.Mode

class AddTeamMemberDetailsPage(mode: Mode) extends BasePage[AddTeamMemberDetailsPage](pageReadyTest(mode)) with ErrorSummary {

  def setEmail(email: String): ConfirmAddTeamMemberPage = {
    sendKeys(emailInput, email)
    click(continueButton)
    ConfirmAddTeamMemberPage(mode)
  }

  def setInvalidEmail(email: String): AddTeamMemberDetailsPage = {
    sendKeys(emailInput, email)
    click(continueButton)
    AddTeamMemberDetailsPage(mode)
  }

}

object AddTeamMemberDetailsPage {

  // The URL in CheckMode ends in /i where i is the index in the user answers collection
  // Therefore use a URL containing test as the precise URL is difficult to determine
  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.urlContaining("application/register/add-team-member-details", mode)
  }

  object elements {
    val emailInput: By = By.id("email")
    val continueButton: By = By.id("continueButton")
  }

  def apply(mode: Mode): AddTeamMemberDetailsPage = {
    new AddTeamMemberDetailsPage(mode)
  }

}
