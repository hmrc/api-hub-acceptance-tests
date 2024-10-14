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
import uk.gov.hmrc.test.ui.pages.Journeys
import uk.gov.hmrc.test.ui.utilities.{Roles, SharedState}

@ScenarioScoped
class JourneySteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  Given("a user has signed-in and registers an application") { () =>
    Journeys.signInAndRegisterAnApplication(sharedState)
  }

  Given("a signed-in user registers an application") { () =>
    Journeys.registerAnApplication(sharedState)
  }

  Given("a signed in user registers {int} applications") { (count: Int) =>
    (1 to count).foreach(
      _ =>
        Journeys.registerAnApplication(sharedState)
    )
  }

  Given("a user has signed-in") { () =>
    Journeys.signIn()
  }

  Given("a user has signed in with role {string}") { (role: String) =>
    Journeys.signIn(Roles.forName(role))
  }

  Given("a user has signed-in with LDAP") { () =>
    Journeys.signInViaLdap()
  }

  Given("a user has signed in with LDAP and role {string}") { (role: String) =>
    Journeys.signInViaLdap(Roles.forName(role))
  }

  Given("a user has signed in with Stride") { () =>
    Journeys.signInViaStride()
  }

  Given("a user has signed in with Stride and role {string}") { (role: String) =>
    Journeys.signInViaStride(Roles.forName(role))
  }

  Given("the user is in a team") { () =>
    Journeys.checkUserHasATeam(sharedState)
  }

  Given("the user adds an API to the application") { () =>
    Journeys.addAnApi(sharedState)
  }

  Given("the user requests production access") { () =>
    Journeys.requestProductionAccess(sharedState)
  }

}
