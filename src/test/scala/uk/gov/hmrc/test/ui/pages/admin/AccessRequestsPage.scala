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

package uk.gov.hmrc.test.ui.pages.admin

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.admin.AccessRequestsPage._
import uk.gov.hmrc.test.ui.pages.admin.AccessRequestsPage.elements._
import uk.gov.hmrc.test.ui.pages.{ApiHubMenu, BasePage, PageReadyTest, PageReadyTests}

class AccessRequestsPage extends BasePage[AccessRequestsPage](pageReadyTest) {

  def openFirstAccessRequest(): AccessRequestPage = {
    val link = findElement(firstAccessRequestLink)
    val accessRequestId = link.getAttribute(accessRequestIdAttribute)
    link.click()
    AccessRequestPage(accessRequestId)
  }

  def navigateToManageApplicationsPage() : ManageApplicationsPage = {
    click(manageApplicationsLink)
    ManageApplicationsPage()
  }

}

object AccessRequestsPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("admin/access-requests")

  object elements {
    val accessRequestIdAttribute: String = "data-access-request-id"
    val firstAccessRequestLink: By = By.cssSelector(s"a[$accessRequestIdAttribute]:first-child")
    val manageApplicationsLink: By = By.cssSelector(s"[data-nav-item-page='ManageApplicationsPage']")
  }

  def apply(): AccessRequestsPage = {
    new AccessRequestsPage()
  }

}
