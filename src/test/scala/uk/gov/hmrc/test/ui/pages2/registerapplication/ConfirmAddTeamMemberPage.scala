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
import uk.gov.hmrc.test.ui.pages2.registerapplication.ConfirmAddTeamMemberPage._
import uk.gov.hmrc.test.ui.pages2.registerapplication.ConfirmAddTeamMemberPage.elements._
import uk.gov.hmrc.test.ui.pages2.{BasePage, PageReadyTest, UrlPageReadyTest}
import uk.gov.hmrc.test.ui.utilities.{CheckMode, Mode}

class ConfirmAddTeamMemberPage(mode: Mode) extends BasePage[ConfirmAddTeamMemberPage](pageReadyTest(mode)) {

  def getTeamMembers: Seq[String] = {
    findElements(summaryRowEmail)
      .map(_.getText.trim)
  }

  def getTeamMembersHeading: String = {
    findElement(teamMembersHeading)
      .getText
      .trim
  }

  def addTeamMembers(): AddTeamMemberDetailsPage = {
    click(yesRadio)
    click(continueButton)
    AddTeamMemberDetailsPage(mode)
  }

  def doNotAddTeamMembers(): CheckYourAnswersPage = {
    click(noRadio)
    click(continueButton)
    CheckYourAnswersPage()
  }

  def changeTeamMember(email: String): AddTeamMemberDetailsPage = {
    findElementWithAttributeValue(changeEmailAttribute, email)
      .click()
    AddTeamMemberDetailsPage(CheckMode)
  }

  def changeLast(): AddTeamMemberDetailsPage = {
    changeTeamMember(getTeamMembers.last)
  }

  def removeTeamMember(email: String): ConfirmAddTeamMemberPage = {
    findElementWithAttributeValue(removeEmailAttribute, email)
      .click()
    ConfirmAddTeamMemberPage(mode)
  }

  def removeLast(): ConfirmAddTeamMemberPage = {
    removeTeamMember(getTeamMembers.last)
  }

}

object ConfirmAddTeamMemberPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    UrlPageReadyTest.withMode("application/register/team-members-overview", mode)
  }

  object elements  {
    val changeEmailAttribute = "data-change-email"
    val removeEmailAttribute = "data-remove-email"
    val teamMembersHeading: By = By.id("teamMembersHeading")
    val summaryRowEmail: By = By.cssSelector(".govuk-summary-list__key")
    val yesRadio: By = By.id("value")
    val noRadio: By = By.id("value-no")
    val continueButton: By = By.id("continueButton")
  }

  def apply(mode: Mode): ConfirmAddTeamMemberPage = {
    new ConfirmAddTeamMemberPage(mode)
  }

}
