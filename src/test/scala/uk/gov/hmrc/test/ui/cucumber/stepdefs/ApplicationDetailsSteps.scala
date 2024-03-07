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

import uk.gov.hmrc.test.ui.pages.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.utilities.{Application, DateFormatterUtil, User}

class ApplicationDetailsSteps extends BaseStepDef {
  private val expectedApplicationApisText = "You have no APIs added"

  When("""the application details, application apis as well as the team members sections should be correct""") { () =>
    assert(ApplicationDetailsPage.getApplicationName == Application.Name, true)
    assert(ApplicationDetailsPage.getCreatedDate() == DateFormatterUtil.getFormattedDate, true)
    Application.Id = ApplicationDetailsPage.getApplicationIdFromUrl()
    assert(ApplicationDetailsPage.getApplicationIdFromUrl() == ApplicationDetailsPage.getApplicationIdFromUi(), true)
    //team members count and email address for the team member
    assert(
      ApplicationDetailsPage.getApplicationApisText().toLowerCase.contains(expectedApplicationApisText.toLowerCase)
    )
    assert(ApplicationDetailsPage.getTeamOwner() == User.Email)
    assert(ApplicationDetailsPage.getCountOfTeamMembersFromHeading() == ApplicationDetailsPage.getTeamMembers().length)
  }
}
