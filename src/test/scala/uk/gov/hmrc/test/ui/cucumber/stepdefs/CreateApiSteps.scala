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
import uk.gov.hmrc.test.ui.pages.createapi.{ApiShortDescriptionPage, ConfigurePrefixesPage, CreateApiCheckYourAnswersPage, CreateApiPage, CreateApiSuccessPage, EnterOasPage, HowToCreateApiPage, ReviewApiNamePage, SelectDomainsPage, SelectHodPage, SelectOwningTeamPage, SelectTestEgressPage, SetApiStatusPage, TeamHasNoEgressesPage}
import uk.gov.hmrc.test.ui.pages.{DashboardPage, Journeys}
import uk.gov.hmrc.test.ui.pages.createteam._
import uk.gov.hmrc.test.ui.pages.team.{ManageMyTeamsPage, ManageTeamPage}
import uk.gov.hmrc.test.ui.utilities.{NormalMode, SharedState}

@ScenarioScoped
class CreateApiSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  When("the user elects to create a new api"){ () =>
    DashboardPage().createApi()
  }

  When("the user clicks Get Started button"){ () =>
    CreateApiPage().getStarted()
  }

  When("the user selects their team"){ () =>
    SelectOwningTeamPage().selectMyTeam(sharedState.team.id)
  }

  When("the user selects Continue"){ (teamId: String) =>
    TeamHasNoEgressesPage().continue()
  }

  When("the user acknowledges they have no egresses available") { () =>
    TeamHasNoEgressesPage().continue()
  }

  When("the user selects to use visual editor") { () =>
    HowToCreateApiPage().selectUseVisualOasEditor()
  }

  When("the user clicks in the editor window") { () =>
    EnterOasPage().selectOasEditor()
  }
  When("the user sets the oas title to be something starting with {string}") { (title: String) =>
    EnterOasPage().setOasTitle(title + System.currentTimeMillis())
  }

  When("the user selects continue on the oas editor page") { () =>
    EnterOasPage().continue()
  }

  When("the user sets the oas version to be {string}") { (version: String) =>
    EnterOasPage().setOasVersion(version)
  }

  When("the user enters a short description") { () =>
    ApiShortDescriptionPage().setShortDescription()
  }

  When("the user confirms api name") { () =>
    ReviewApiNamePage().confirmApiName()
  }

  When("the user chooses to configure no prefixes") { () =>
    ConfigurePrefixesPage().noPrefixes()
  }

  When("the user selects a hod") { () =>
    SelectHodPage().chooseFirstHod()
  }

  When("the user selects domains") { () =>
    SelectDomainsPage().selectDomains()
  }

  When("the user sets the api status") { () =>
    SetApiStatusPage().setAlphaStatus()
  }

  When("the user checks their answers the first time") { () =>
    CreateApiCheckYourAnswersPage().confirmAnswers()
  }

  When("the user selects no test egress") { () =>
    SelectTestEgressPage().selectNoTestEgress()
  }

  When("the user checks their answers the second time") { () =>
    CreateApiCheckYourAnswersPage().completeJourney()
  }

  When("the create api journey is completed") { () =>
    CreateApiSuccessPage()
  }

}
