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
  var expectedApplicationName: String       = _
  var addedTeamMembersCount: Int            = 1
  var lastAddedTeamMember: String           = s"${Faker.en_GB.firstName()}@digital.hmrc.gov.uk"
  var invalidDomainEmail: String            = s"${Faker.en_GB.firstName()}@example.com"
  var updatedEmail: String                  = _
  val expectedApplicationDetailsHeadingText = "Application details"
  val expectedNoTeamMembersText             = "No team members added"
  val updatedApplicationName: String        = ApplicationName.randAppName.reverse.toLowerCase

  Then("""the new user starts the registration process""") { () =>
    YourApplicationPage.registerApplication()
    expectedApplicationName = ApplicationName.randAppName
    ApplicationName.fillInApplicationName(expectedApplicationName)
  }

  When("""{string} additional team members are added""") { (string: String) =>
    addedTeamMembersCount += string.toInt
    TeamMembers.addTeamMember()

    var addMemberCount = 1
    while (addMemberCount < string.toInt) {
      val randomNameEmail = s"${Faker.en_GB.firstName()}@digital.hmrc.gov.uk"
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
    TeamMembersOverviewPage.getPageTitle().startsWith(s"$addedTeamMembersCount")
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
    TeamMembers.clickContinue()
  }

  When("""the user chooses to not add a new team member""") { () =>
    TeamMembers.addNoTeamMember()
  }

  Then("""the problem alert box displayed""") { () =>
    assert(TeamMembers.isAlertBoxDisplayed(), true)
  }

  Then("""the user attempts to add a new team member using an unaccepted domain""") { () =>
    TeamMembers.addTeamMember()
    AddTeamMemberDetailsPage.fillInEmail(invalidDomainEmail)
  }

  Then("""the email alert message is displayed""") { () =>
    assert(TeamMembers.isEmailAlertMessageDisplayed(), true)
  }

  Then("""the check your answers page displays the correct information for no team members added""") { () =>
    assert(CheckYouAnswersPage.getApplicationNameText == ApplicationName.randAppName)
    assert(CheckYouAnswersPage.getApplicationDetailsHeadingText == expectedApplicationDetailsHeadingText)
    assert(CheckYouAnswersPage.isChangeApplicationNameLinkDisplayed, true)
    assert(CheckYouAnswersPage.isTeamMembersChangeLinkDisplayed, true)
    assert(CheckYouAnswersPage.getNoTeamMembersText == expectedNoTeamMembersText, true)
    assert(CheckYouAnswersPage.getTeamOwnerEmailText.toLowerCase() == User.Email.toLowerCase(), true)
  }

  Then("""the user changes the application name""") { () =>
    CheckYouAnswersPage.clickChangeApplicationName()
    ApplicationName.clearApplicationName()
    ApplicationName.fillInApplicationName(updatedApplicationName)
  }

  Then("""the application name should be changed""") { () =>
    assert(CheckYouAnswersPage.getApplicationNameText.toLowerCase() == updatedApplicationName, true)
  }

  Then("""the user chooses to change the team member""") { () =>
    CheckYouAnswersPage.clickChangeTeamMember()
  }

  Then("""the user should be redirected to the team members overview page""") { () =>
    assert(AddTeamMembers.isContinueButtonDisplayed(), true)
    assert(AddTeamMembers.getHeadingText() == "Do you want to add team members?", true)
    assert(AddTeamMembers.isNoRadioButtonSelected(), true)
  }
}
