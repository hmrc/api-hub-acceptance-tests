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

import com.google.inject.Inject
import faker.Faker
import io.cucumber.guice.ScenarioScoped
import uk.gov.hmrc.test.ui.utilities.{CheckMode, NormalMode, SharedState, User}
import uk.gov.hmrc.test.ui.pages.DashboardPage
import uk.gov.hmrc.test.ui.pages.registerapplication.{AddTeamMemberDetailsPage, AddTeamMembersPage, CheckYourAnswersPage, ConfirmAddTeamMemberPage}

@ScenarioScoped
class TeamMemberSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  val invalidDomainEmail: String                    = s"${Faker.en_GB.firstName()}@example.com"
  val updatedEmail: String                          = s"${Faker.en_GB.firstName()}@digital.hmrc.gov.uk".toLowerCase()
  val expectedNoTeamMembersText: String             = "No team members added"
  val updatedApplicationName: String                = sharedState.application.name.reverse.toLowerCase

  Then("""the new user starts the registration process""") { () =>
    DashboardPage()
      .registerAnApplication()
      .setApplicationNameNormalMode(sharedState.application.name)
  }

  When("""{int} additional team members are added""") { (int: Int) =>
    if (int > 0) {
      AddTeamMembersPage(NormalMode).addTeamMembers()

      (1 to int).foreach(
        i => {
          val email = s"${Faker.en_GB.firstName()}.${Faker.en_GB.lastName()}@digital.hmrc.gov.uk"

          AddTeamMemberDetailsPage(NormalMode).setEmail(email)

          if (i < int) {
            ConfirmAddTeamMemberPage(NormalMode).addTeamMembers()
          }
        }
      )
    }
  }

  Then("""{int} team members exist""") { (int: Int) =>
    ConfirmAddTeamMemberPage(NormalMode)
      .foreach(
        confirmAddTeamMemberPage => {
          confirmAddTeamMemberPage.getTeamMembers.size shouldBe int
          confirmAddTeamMemberPage.getTeamMembersHeading should startWith(int.toString)
        }
      )
  }

  Then("""the count of team members on the check your answers page is {int}""") { (int: Int) =>
    ConfirmAddTeamMemberPage(NormalMode)
      .doNotAddTeamMembers()
      .foreach(
        checkYourAnswersPage => {
          checkYourAnswersPage.getStatedNumberOfTeamMembers shouldBe int.toString
        }

      )
  }

  When("""the user changes the team members email address""") { () =>
    ConfirmAddTeamMemberPage(NormalMode)
      .changeLast()
      .setEmail(updatedEmail)
  }

  Then("""the team members email is changed""") { () =>
    ConfirmAddTeamMemberPage(CheckMode)
      .foreach(
        confirmAddTeamMemberPage =>
          confirmAddTeamMemberPage.getTeamMembers.last shouldBe updatedEmail
      )
  }

  Then("""the user removes the last added team member""") { () =>
    ConfirmAddTeamMemberPage(NormalMode)
      .removeLast()
  }

  Then("""the user attempts to add a new team member with no radio button option chosen""") { () =>
    AddTeamMembersPage(NormalMode)
      .continueWithoutSelection()
  }

  When("""the user chooses to not add a new team member""") { () =>
    AddTeamMembersPage(NormalMode)
      .doNotAddTeamMembers()
  }

  Then("the add team members page displays an error summary") { () =>
    AddTeamMembersPage(NormalMode)
      .foreach(
        addTeamMembersPage =>
          addTeamMembersPage.hasErrorSummary shouldBe true
      )
  }

  Then("the add team member details page displays an error summary") { () =>
    AddTeamMemberDetailsPage(NormalMode)
      .foreach(
        addTeamMemberDetailsPage =>
          addTeamMemberDetailsPage.hasErrorSummary shouldBe true
      )
  }

  Then("""the user attempts to add a new team member using an unaccepted domain""") { () =>
    AddTeamMembersPage(NormalMode)
      .addTeamMembers()
      .setInvalidEmail(invalidDomainEmail)
  }

  Then("""the email alert message is displayed""") { () =>
    AddTeamMemberDetailsPage(NormalMode)
      .foreach(
        addTeamMemberDetailsPage =>
          addTeamMemberDetailsPage.hasErrorSummary shouldBe true
      )
  }

  Then("""the check your answers page displays the correct information for no team members added""") { () =>
    CheckYourAnswersPage()
      .foreach(
        checkYourAnswersPage => {
          checkYourAnswersPage.getApplicationName shouldBe sharedState.application.name
          checkYourAnswersPage.getStatedNumberOfTeamMembers shouldBe expectedNoTeamMembersText
          checkYourAnswersPage.getTeamMembers should contain theSameElementsAs Seq(User.Email)
        }
      )
  }

  Then("""the user changes the application name""") { () =>
    CheckYourAnswersPage()
      .changeApplicationName()
      .setApplicationNameCheckMode(updatedApplicationName)
  }

  Then("""the application name should be changed""") { () =>
    CheckYourAnswersPage()
      .foreach(
        checkYourAnswersPage =>
          checkYourAnswersPage.getApplicationName shouldBe updatedApplicationName
      )
  }

  Then("""the user chooses to change the team member""") { () =>
    CheckYourAnswersPage()
      .addTeamMember()
  }

  Then("""the user should be redirected to the team members overview page""") { () =>
    AddTeamMembersPage(CheckMode)
  }

}
