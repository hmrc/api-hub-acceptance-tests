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
import uk.gov.hmrc.test.ui.pages.Journeys
import uk.gov.hmrc.test.ui.pages.createteam.{CheckYourAnswersPage, CreateTeamAddTeamMemberPage, CreateTeamApiProducerConsumerPage, CreateTeamMembersPage, CreateTeamNamePage, CreateTeamSuccessPage}
import uk.gov.hmrc.test.ui.pages.team.{ManageMyTeamsPage, ManageTeamPage}
import uk.gov.hmrc.test.ui.utilities.{NormalMode, SharedState}

@ScenarioScoped
class CreateTeamSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  Given("the new user starts the create team journey") { () =>
    Journeys
      .openStartPage()
      .dashboard()
      .createTeam()
  }

  When("the user enters a team name") { () =>
    CreateTeamNamePage(NormalMode).setTeamNameNormalMode(sharedState.team.name)
  }

  When("the user selects the team to be an api producer") { () =>
    CreateTeamApiProducerConsumerPage().setProducer()
  }

  When("selects to continue to the create team add team members") { () =>
    CreateTeamApiProducerConsumerPage().continue()
  }

  When("selects to enter an additional team member") { () =>
    CreateTeamMembersPage().addTeamMember()
  }

  When("enters the new team member's email address") { () =>
    val email = s"${Faker.en_GB.firstName()}@digital.hmrc.gov.uk".toLowerCase()
    CreateTeamAddTeamMemberPage().setEmailAddress(email)
  }

  When("selects to continue to the create team check your answers page") { () =>
    CreateTeamMembersPage().continue()
  }

  When("creates the team on the check your answers page") { () =>
    CheckYourAnswersPage()
      .createTeam()
      .foreach(
        createTeamSuccessPage =>
          sharedState.team.id = createTeamSuccessPage.getTeamId
      )
  }

  When("clicks to view the manage my teams page from the success page") { () =>
    CreateTeamSuccessPage().viewManageTeams()
  }

  When("clicks to view the new team") { () =>
    ManageMyTeamsPage().viewTeamWithId(sharedState.team.id)
  }

  Then("the new team's details are correct") { () =>
    ManageTeamPage(sharedState.team.id)
      .foreach(
        manageTeamPage => {
          manageTeamPage.getTeamName shouldBe sharedState.team.name
          manageTeamPage.getTeamMemberEmails.size shouldBe 2
        }
      )
  }

}
