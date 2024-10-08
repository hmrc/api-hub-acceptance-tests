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
import uk.gov.hmrc.test.ui.pages.addanapi.AddAnApiSuccessPage
import uk.gov.hmrc.test.ui.pages.application.{ApplicationDetailsPage, EnvironmentAndCredentialsPage}
import uk.gov.hmrc.test.ui.pages.{Journeys, Robot}
import uk.gov.hmrc.test.ui.utilities.SharedState

import java.time.LocalDate

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
          applicationDetailsPage.getCreated shouldBe LocalDate.now()
          applicationDetailsPage.getNoApisMessage.toLowerCase should include(expectedApplicationApisText.toLowerCase)
          applicationDetailsPage.getOwningTeamName shouldBe sharedState.team.name
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

  Then("""the client id should be added to the test environments credentials with count {int}""") { (expectedCount: Int) =>
    EnvironmentAndCredentialsPage(sharedState.application.id)
      .foreach { page =>
        val credentialCount = page.getSecondaryCredentialCount // Extract the credential count once
        credentialCount shouldBe expectedCount // Use the dynamic expected count
      }
  }

  Then("""the client id should be added to the Production environments credentials with count {int}""") { (expectedCount: Int) =>
    EnvironmentAndCredentialsPage(sharedState.application.id).selectAddProdCredentialsLink()
    EnvironmentAndCredentialsPage(sharedState.application.id)
      .foreach { page =>
        val credentialCount = page.getSecondaryCredentialCount // Extract the credential count once
        credentialCount shouldBe expectedCount // Use the dynamic expected count
      }
  }

  When("""the user adds Test credentials""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id).selectAddTestCredentials()
  }

  When("""the user adds Prod credentials""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id).selectAddProdCredentialsLink()
    EnvironmentAndCredentialsPage(sharedState.application.id).selectAddProdCredentials()
  }

  Given("""the user chooses {string} from the application left hand nav menu""") { (string: String) =>
    string match {
      case "Application APIs" => ApplicationDetailsPage(sharedState.application.id).applicationApis()
      case "Environments and credentials" => ApplicationDetailsPage(sharedState.application.id).environmentsAndCredentials()
      case "Delete application" => ApplicationDetailsPage(sharedState.application.id).deleteApplication()
//      case "HIP Production" => ApplicationDetailsPage(sharedState.application.id).HIPProduction()
      case _ => throw new IllegalArgumentException(s"Unknown option: $string")
    }
  }

}
