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
import uk.gov.hmrc.test.ui.utilities.User

class SignInSteps extends BaseStepDef {
  Given("""a user is on the sign in page""") { () =>
    ServiceStartPage
      .loadPage()
      .startNow()

    assert(SignInPage.isLdapContinueButtonDisplayed, true)
  }

  Given("""the user decides to login via ldap""") { () =>
    SignInPage.clickLdapContinue()
  }

  When("""an approver with write privileges logs in""") { () =>
    CreateSignInPage.defaultLoginUser()
  }

  When("""a new user with approver resource type with write privileges logs in""") { () =>
    CreateSignInPage.loginWithUserEmail(User.Email)
  }

  Then("""the user should be authenticated""") { () =>
    assert(YourApplicationPage.yourApplicationsIsDisplayed(), true)
  }

  Then("""the application should be registered""") { () =>
    assert(ApplicationSuccessPage.isApplicationSuccessDisplayed(), true)
  }

  Then("""the api is added to the application""") { () =>
    assert(ApiAddedSuccessfullyPage.getApiName.startsWith(HipApisPage.getSelectedApiName))
    ApiAddedSuccessfullyPage.viewApplication()
    assert(ApplicationDetailsPage.isApiNameAddedToApplication(HipApisPage.getSelectedApiName))
  }

  Given("a user logs in with role {string}") { (role: String) =>
    CreateSignInPage.loginWithRoleAndEmailAddress(role, User.Email)
  }

  Then("your applications has the following header links {string} {string} {string}") {
    (linkOne: String, linkTwo: String, linkThree: String) =>
      assert(YourApplicationPage.getHeaderLinkTexts().contains(linkOne))
      assert(YourApplicationPage.getHeaderLinkTexts().contains(linkTwo))
      if (linkThree.nonEmpty) {
        assert(YourApplicationPage.getHeaderLinkTexts().contains(linkThree))
      }
  }
}
