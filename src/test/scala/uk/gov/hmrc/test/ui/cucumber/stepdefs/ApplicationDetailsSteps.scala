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
import uk.gov.hmrc.test.ui.pages.{ApiDetailsPage, ApplicationDeletePage, ApplicationDetailsPage, ApplicationNamePage, ApplicationSuccessPage, CheckYourAnswersPage, HipApisPage, ReviewPolicyPage, SelectApplicationPage, SelectEndpointsPage, TeamMembersPage, YourApplicationPage}
import uk.gov.hmrc.test.ui.utilities.{DateFormatterUtil, User}

class ApplicationDetailsSteps extends BaseStepDef {
  private val expectedApplicationApisText: String = "You have no APIs added"

  Then("""the new user registers an application""") { () =>
    YourApplicationPage.registerApplication()
    ApplicationNamePage.fillInApplicationName(application.name)
    TeamMembersPage.addNoTeamMember()
    CheckYourAnswersPage.registerApplication()
  }

  Then("""the application can be viewed""") { () =>
    ApplicationSuccessPage.viewRegisteredApplication()
    assert(ApplicationDetailsPage.getApplicationName == application.name)
  }

  When("""the attempts to continue without selecting an endpoint""") { () =>
    ApplicationDetailsPage.addApis()
    HipApisPage.selectRandomApi()
    ApiDetailsPage.addToAnApplication()
    SelectApplicationPage.selectApplicationRadioButton(application.name).continue()
    SelectEndpointsPage.continue()
  }

  Then("""the user attempts to add an api to the application""") { () =>
    ApplicationDetailsPage.addApis()
    HipApisPage.selectRandomApi()
    ApiDetailsPage.addToAnApplication()
    SelectApplicationPage
      .selectApplicationRadioButton(application.name)
      .continue()
    SelectEndpointsPage.selectAllEndpoints().continue()
    ReviewPolicyPage.confirmCheckbox()
    ReviewPolicyPage.acceptAndContinue()
    CheckYourAnswersPage.continue()
  }

  When("""the application details, application apis as well as the team members sections should be correct""") { () =>
    assert(ApplicationDetailsPage.getApplicationName == application.name, "Application name should be matching")
    assert(ApplicationDetailsPage.getCreatedDate() == DateFormatterUtil.getFormattedDate, true)
    application.id = ApplicationDetailsPage.getApplicationIdFromUrl()
    assert(ApplicationDetailsPage.getApplicationIdFromUrl() == ApplicationDetailsPage.getApplicationIdFromUi(), true)
    //team members count and email address for the team member
    assert(
      ApplicationDetailsPage.getApplicationApisText().toLowerCase.contains(expectedApplicationApisText.toLowerCase)
    )
    assert(ApplicationDetailsPage.getTeamOwner() == User.Email)
    assert(ApplicationDetailsPage.getCountOfTeamMembersFromHeading() == ApplicationDetailsPage.getTeamMembers().length)
  }

  Then("""the user is redirected to the {string} page""") { (string: String) =>
    assert(ApplicationDetailsPage.getPageTitle() == string)
    assert(ApplicationDetailsPage.getApplicationName == application.name)
  }

  Given("""the user adds a particular api to an application""") { () =>
    ApplicationDetailsPage.addApis()
    HipApisPage.chooseApiByText(AddressWeighting.Name)
    ApiDetailsPage.addToAnApplication()
    SelectApplicationPage.selectApplicationRadioButton(application.name).continue()
  }

  Then("""the previously registered application should no no longer be listed in all applications""") { () =>
    ApplicationDeletePage.returnToYourApplications()
    assert(!YourApplicationPage.getRegisteredApplicationNames.contains(application.name))
  }
}
