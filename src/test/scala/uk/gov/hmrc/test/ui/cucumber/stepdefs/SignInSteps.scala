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

import faker.Faker
import uk.gov.hmrc.test.ui.pages.ApplicationName.{fillInApplicationName, randAppName}
import uk.gov.hmrc.test.ui.pages.ApplicationSuccessPage.isApplicationSuccessDisplayed
import uk.gov.hmrc.test.ui.pages.CreateSignIn.{defaultLoginUser, loginWithUserEmail}
import uk.gov.hmrc.test.ui.pages.SignInPage.clickLdapContinue
import uk.gov.hmrc.test.ui.pages.TeamMembers.addNoTeamMember
import uk.gov.hmrc.test.ui.pages.YourApplicationPage.{registerApplication, yourApplicationsIsDisplayed}
import uk.gov.hmrc.test.ui.pages.{ApplicationDetailsPage, ApplicationSuccessPage, CheckYouAnswersPage, SignInPage, YourApplicationPage}

class SignInSteps extends BaseStepDef {
  var expectedApplicationName: String = _
  val randomLocalEmail                = s"${Faker.en_GB.lastName()}@digital.hmrc.gov.uk"

  Given("""a user is on the sign in page""") { () =>
    SignInPage.loadPage()
  }

  Given("""the user decides to login via ldap""") { () =>
    clickLdapContinue()
  }

  When("""an approver with write privileges logs in""") { () =>
    defaultLoginUser()
  }

  When("""a new user with approver resource type with write privileges logs in""") { () =>
    loginWithUserEmail(randomLocalEmail)
  }

  Then("""the user should be authenticated""") { () =>
    assert(yourApplicationsIsDisplayed(), true)
  }

  Then("""the new user registers an application""") { () =>
    registerApplication()
    expectedApplicationName = randAppName
    fillInApplicationName(expectedApplicationName)
    addNoTeamMember()
    CheckYouAnswersPage.registerApplication()
  }

  Then("""the application should be registered""") { () =>
    assert(isApplicationSuccessDisplayed(), true)
  }

  Then("""the application can be viewed""") { () =>
    ApplicationSuccessPage.viewRegisteredApplication()
    assert(ApplicationDetailsPage.getApplicationName == randAppName)
  }
}