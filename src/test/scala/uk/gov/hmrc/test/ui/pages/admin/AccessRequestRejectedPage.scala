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
import uk.gov.hmrc.test.ui.pages.admin.AccessRequestRejectedPage.pageReadyTest
import uk.gov.hmrc.test.ui.pages.admin.AccessRequestRejectedPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class AccessRequestRejectedPage(id: String) extends BasePage[AccessRequestRejectedPage](pageReadyTest(id)){

  def returnToAccessRequests(): AccessRequestsPage = {
    click(accessRequestsLink)
    AccessRequestsPage()
  }

}

object AccessRequestRejectedPage {

  // We need a combined test as the URL is common to the view, approved, and rejected pages
  def pageReadyTest(id: String): PageReadyTest = PageReadyTests.allOf(
    PageReadyTests.apiHubPage.url(s"admin/access-requests/$id"),
    PageReadyTests.apiHubPage.title("API access request successfully rejected")
  )

  object elements {
    val accessRequestsLink: By = By.id("accessRequestsLink")
  }

  def apply(id: String): AccessRequestRejectedPage = {
    new AccessRequestRejectedPage(id)
  }

}
