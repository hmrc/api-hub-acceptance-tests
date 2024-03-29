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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import faker.Faker
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.utilities.User

class TeamMemberSteps extends BaseStepDef {
  var expectedApplicationName: String               = _
  var addedTeamMembersCount: Int                    = 1
  var lastAddedTeamMember: String                   = s"${Faker.en_GB.firstName()}@digital.hmrc.gov.uk"
  var invalidDomainEmail: String                    = s"${Faker.en_GB.firstName()}@example.com"
  var updatedEmail: String                          = _
  val expectedApplicationDetailsHeadingText: String = "Application details"
  val expectedHeadingText: String                   = "Do you want to add team members?"
  val expectedNoTeamMembersText: String             = "No team members added"
  val updatedApplicationName: String                = application.name.reverse.toLowerCase
  var addMemberCount: Int                           = 1

  Then("""the new user starts the registration process""") { () =>
    YourApplicationPage.registerApplication()
    expectedApplicationName = application.name
    ApplicationNamePage.fillInApplicationName(expectedApplicationName)
  }

  When("""{string} additional team members are added""") { (string: String) =>
    addedTeamMembersCount += string.toInt
    TeamMembersPage.addTeamMember()

    while (addMemberCount < string.toInt) {
      val randomNameEmail = s"${Faker.en_GB.firstName()}.${Faker.en_GB.lastName()}@digital.hmrc.gov.uk"
      AddTeamMemberDetailsPage.fillInEmail(randomNameEmail)
      TeamMembersOverviewPage.addTeamMember()
      addMemberCount = addMemberCount + 1
    }
    AddTeamMemberDetailsPage.fillInEmail(lastAddedTeamMember)
  }

  Then("""{string} team members exist""") { (string: String) =>
    assert(TeamMembersOverviewPage.getNumberOfTeamMemberRows() == string.toInt, true)
  }

  Then("""the count of team members on the check your answers page is {string}""") { (string: String) =>
    assert(addedTeamMembersCount == string.toInt, true)
    assert(TeamMembersOverviewPage.getPageTitle().startsWith(s"$addedTeamMembersCount"))
  }

  When("""the user changes the team members email address""") { () =>
    updatedEmail = s"${Faker.en_GB.firstName()}@digital.hmrc.gov.uk".toLowerCase()
    TeamMembersOverviewPage.change()
    AddTeamMemberDetailsPage.clearEmail()
    AddTeamMemberDetailsPage.fillInEmail(updatedEmail)
  }

  Then("""the team members email is changed""") { () =>
    assert(TeamMembersOverviewPage.getLastAddedTeamMembersEmail().equals(updatedEmail))
  }

  Then("""the user removes the last added team member""") { () =>
    TeamMembersOverviewPage.remove()
  }

  Then("""the user attempts to add a new team member with no radio button option chosen""") { () =>
    TeamMembersPage.clickContinue()
  }

  When("""the user chooses to not add a new team member""") { () =>
    TeamMembersPage.addNoTeamMember()
  }

  Then("""the problem alert box displayed""") { () =>
    assert(TeamMembersPage.isAlertBoxDisplayed(), true)
  }

  Then("""the user attempts to add a new team member using an unaccepted domain""") { () =>
    TeamMembersPage.addTeamMember()
    AddTeamMemberDetailsPage.fillInEmail(invalidDomainEmail)
  }

  Then("""the email alert message is displayed""") { () =>
    assert(TeamMembersPage.isEmailAlertMessageDisplayed(), true)
  }

  Then("""the check your answers page displays the correct information for no team members added""") { () =>
    assert(CheckYourAnswersPage.getApplicationNameText == application.name)
    assert(CheckYourAnswersPage.getApplicationDetailsHeadingText == expectedApplicationDetailsHeadingText)
    assert(CheckYourAnswersPage.isChangeApplicationNameLinkDisplayed, true)
    assert(CheckYourAnswersPage.isTeamMembersChangeLinkDisplayed, true)
    assert(CheckYourAnswersPage.getNoTeamMembersText == expectedNoTeamMembersText, true)
    assert(CheckYourAnswersPage.getTeamOwnerEmailText.toLowerCase() == User.Email.toLowerCase(), true)
  }

  Then("""the user changes the application name""") { () =>
    CheckYourAnswersPage.clickChangeApplicationName()
    ApplicationNamePage.clearApplicationName()
    ApplicationNamePage.fillInApplicationName(updatedApplicationName)
  }

  Then("""the application name should be changed""") { () =>
    assert(CheckYourAnswersPage.getApplicationNameText.toLowerCase() == updatedApplicationName, true)
  }

  Then("""the user chooses to change the team member""") { () =>
    CheckYourAnswersPage.clickChangeTeamMember()
  }

  Then("""the user should be redirected to the team members overview page""") { () =>
    assert(AddTeamMembersPage.isContinueButtonDisplayed(), true)
    assert(AddTeamMembersPage.getHeadingText() == expectedHeadingText, true)
    assert(AddTeamMembersPage.isNoRadioButtonSelected(), true)
  }
}
