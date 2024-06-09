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
import io.cucumber.guice.ScenarioScoped
import uk.gov.hmrc.test.ui.domain.AddressWeighting
import uk.gov.hmrc.test.ui.pages2.addanapi.AddAnApiSuccessPage
import uk.gov.hmrc.test.ui.pages2.application.{ApplicationDetailsPage, EnvironmentAndCredentialsPage}
import uk.gov.hmrc.test.ui.pages2.{Journeys, Robot}
import uk.gov.hmrc.test.ui.utilities.{DateFormatterUtil, User}

@ScenarioScoped
class ApplicationDetailsSteps @Inject()(sharedState: SharedState) extends BaseStepDef with Robot {

  private val expectedApplicationApisText: String = "You have no APIs added"
  private var apiId = ""
  private var apiTitle = ""

  Then("""the new user registers an application""") { () =>
    Journeys
      .registerAnApplication(sharedState)
  }

  Then("""the application can be viewed""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .foreach(
        applicationDetailsPage =>
          applicationDetailsPage.getApplicationName shouldBe sharedState.application.name
      )
  }

  When("""the attempts to continue without selecting an endpoint""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .addApis()
      .selectRandomApi()
      .addToAnApplication()
      .selectApplication(sharedState.application.id)
      .selectNoEndpoints()
  }

  Then("""the user attempts to add an api to the application""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .addApis()
      .selectRandomApi()
      .foreach(
        apiDetailsPage => {
          apiId = apiDetailsPage.getApiId
          apiTitle = apiDetailsPage.getApiTitle
        }
      )
      .addToAnApplication()
      .selectApplication(sharedState.application.id)
      .selectAllEndpoints()
      .confirmUsagePolicy()
      .continue()
  }

  When("""the application details, application apis as well as the team members sections should be correct""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .foreach(
        applicationDetailsPage => {
          applicationDetailsPage.getApplicationName shouldBe sharedState.application.name
          applicationDetailsPage.getCreated shouldBe DateFormatterUtil.getFormattedDate
          applicationDetailsPage.getNoApisMessage.toLowerCase should include(expectedApplicationApisText.toLowerCase)
          applicationDetailsPage.getTeamMembers shouldBe Seq(User.Email)
          applicationDetailsPage.getCountOfTeamMembersFromHeading shouldBe applicationDetailsPage.getTeamMembers.size
        }
      )
  }

  Then("""the user is redirected to the {string} page""") { (string: String) =>
    string match {
      case "Application details" =>
        ApplicationDetailsPage(sharedState.application.id)
          .foreach(
            applicationDetailsPage =>
              applicationDetailsPage.getApplicationName shouldBe sharedState.application.name
          )
      case _ => throw new IllegalArgumentException(s"Don't know how to process value $string")
    }
  }

  Given("""the user adds a particular api to an application""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .addApis()
      .selectApiByTitle(AddressWeighting.Name)
      .addToAnApplication()
      .selectApplication(sharedState.application.id)
  }

  Then("""the api is added to the application""") { () =>
    AddAnApiSuccessPage()
      .foreach(
        addAnApiSuccessPage=>
          addAnApiSuccessPage.getSuccessSummary should startWith(apiTitle)
      )
      .viewApplication()
      .foreach(
        applicationDetailsPage =>
          applicationDetailsPage.hasApiAdded(apiId) shouldBe true
      )
  }

  When("the user navigates to an invalid application id {string}") { (string: String) =>
    navigateTo(s"application/details/$string")
  }

  Then("the client id should be added to the development environments credentials") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id)
      .foreach(
        page =>
          page.getSecondaryCredentialCount shouldBe 1
      )
  }

  Given("""the user chooses {string} from the application left hand nav menu""") { (string: String) =>
    string match {
      case "Application APIs" => ApplicationDetailsPage(sharedState.application.id).applicationApis()
      case "Environments and credentials" => ApplicationDetailsPage(sharedState.application.id).environmentsAndCredentials()
      case "Delete application" => ApplicationDetailsPage(sharedState.application.id).deleteApplication()
      case _ => throw new IllegalArgumentException(s"Unknown option: $string")
    }
  }

}
