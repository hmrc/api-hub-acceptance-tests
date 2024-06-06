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

import uk.gov.hmrc.test.ui.domain.AddressWeighting
import uk.gov.hmrc.test.ui.pages2.Journeys
import uk.gov.hmrc.test.ui.pages2.addanapi.AddAnApiSuccessPage
import uk.gov.hmrc.test.ui.pages2.application.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.utilities.{DateFormatterUtil, User}

class ApplicationDetailsSteps extends BaseStepDef {
  private val expectedApplicationApisText: String = "You have no APIs added"
  private var applicationId = ""
  private var apiId = ""
  private var apiTitle = ""

  Then("""the new user registers an application""") { () =>
    Journeys
      .signedInUserRegistersAnApplication(application)
      .foreach(
        applicationDetailsPage =>
          applicationId = applicationDetailsPage.getApplicationId
      )
  }

  Then("""the application can be viewed""") { () =>
    ApplicationDetailsPage(applicationId)
      .foreach(
        applicationDetailsPage =>
          applicationDetailsPage.getApplicationName shouldBe application.name
      )
  }

  When("""the attempts to continue without selecting an endpoint""") { () =>
    ApplicationDetailsPage(applicationId)
      .addApis()
      .selectRandomApi()
      .addToAnApplication()
      .selectApplication(applicationId)
      .selectNoEndpoints()
  }

  Then("""the user attempts to add an api to the application""") { () =>
    ApplicationDetailsPage(applicationId)
      .addApis()
      .selectRandomApi()
      .foreach(
        apiDetailsPage => {
          apiId = apiDetailsPage.getApiId
          apiTitle = apiDetailsPage.getApiTitle
        }
      )
      .addToAnApplication()
      .selectApplication(applicationId)
      .selectAllEndpoints()
      .confirmUsagePolicy()
      .continue()
  }

  When("""the application details, application apis as well as the team members sections should be correct""") { () =>
    ApplicationDetailsPage(applicationId)
      .foreach(
        applicationDetailsPage => {
          applicationDetailsPage.getApplicationName shouldBe application.name
          applicationDetailsPage.getCreated shouldBe DateFormatterUtil.getFormattedDate
          applicationDetailsPage.getNoApisMessage.toLowerCase should contain(expectedApplicationApisText.toLowerCase)
          applicationDetailsPage.getTeamMembers shouldBe Seq(User.Email)
          applicationDetailsPage.getCountOfTeamMembersFromHeading shouldBe applicationDetailsPage.getTeamMembers.size
        }
      )
  }

  Then("""the user is redirected to the {string} page""") { (string: String) =>
    string match {
      case "Application details" =>
        ApplicationDetailsPage(applicationId)
          .foreach(
            applicationDetailsPage =>
              applicationDetailsPage.getApplicationName shouldBe application.name
          )
      case _ => throw new IllegalArgumentException(s"Don't know how to process value $string")
    }
  }

  Given("""the user adds a particular api to an application""") { () =>
    ApplicationDetailsPage(applicationId)
      .addApis()
      .selectApiByTitle(AddressWeighting.Name)
      .addToAnApplication()
      .selectApplication(applicationId)
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

}
