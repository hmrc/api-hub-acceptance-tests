/*
 * Copyright 2023 HM Revenue & Customs
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

import uk.gov.hmrc.test.ui.pages._

class ProductionAccessSteps extends BaseStepDef {
  Given("""an api is added to an application""") { () =>
    ServiceStartPage
      .loadPage()
      .startNow()

    SignInPage.clickLdapContinue()
    CreateSignIn.defaultLoginUser()

    YourApplicationPage.registerApplication()
    ApplicationName.fillInApplicationName(ApplicationName.randAppName)
    TeamMembers.addNoTeamMember()
    CheckYourAnswersPage.registerApplication()
    ApplicationSuccessPage.viewRegisteredApplication()
    assert(ApplicationDetailsPage.getApplicationName == ApplicationName.randAppName)

    ApplicationDetailsPage.addApis()
    HipApisPage.selectRandomApi()
    ApiDetailsPage.addToAnApplication()
    SelectApplicationPage.selectApplicationRadioButton(ApplicationName.randAppName).continue()
    SelectEndpointsPage.selectAllEndpoints().continue()
    ReviewPolicyPage.confirmCheckbox()
    ReviewPolicyPage.acceptAndContinue()
    CheckYourAnswersPage.continue()

    assert(ApiAddedSuccessfullyPage.getApiName.startsWith(HipApisPage.getSelectedApiName))
    ApiAddedSuccessfullyPage.viewApplication()
    assert(ApplicationDetailsPage.isApiNameAddedToApplication(HipApisPage.getSelectedApiName))
  }

  //from the left hand menu the user chooses 'Application APIs'"
  When("from the left hand menu the user chooses {string}") { (lhnmOption: String) =>
    ApplicationDetailsPage.chooseLhnmOption(lhnmOption)
  }

  When("the user requests prod access") { () =>
    ApplicationDetailsPage.requestProductionAccess()
    RequestProductionAccessPage.confirm().continue()
  }

  And("the user supports the request with a reason") { () =>
    ProvideSupportingInformationPage.randomlyFillInTextBoxReason().continue()
  }

  Then("the pending request is logged") { () =>
    assert(RequestProductionAccessSuccessPage.isProductionAccessBannerRequestMessageDisplayed, true)
  }
}
