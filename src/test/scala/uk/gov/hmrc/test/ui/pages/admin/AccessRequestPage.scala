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
import uk.gov.hmrc.test.ui.pages.admin.AccessRequestPage.pageReadyTest
import uk.gov.hmrc.test.ui.pages.admin.AccessRequestPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class AccessRequestPage(id: String) extends BasePage[AccessRequestPage](pageReadyTest(id)) {

  def getId: String = id

  def approveAccessRequest(): AccessRequestApprovedPage = {
    click(approveRadioButton)
    click(continueButton)
    AccessRequestApprovedPage(id)
  }

  def rejectAccessRequest(rejectedReason: String): AccessRequestRejectedPage = {
    click(rejectRadioButton)
    sendKeys(rejectedReasonText, rejectedReason)
    click(continueButton)
    AccessRequestRejectedPage(id)
  }

}

object AccessRequestPage {

  // We need a combined test as the URL is common to the view, approved, and rejected pages
  def pageReadyTest(id: String): PageReadyTest = PageReadyTests.allOf(
    PageReadyTests.apiHubPage.url(s"admin/access-requests/$id"),
    PageReadyTests.apiHubPage.title("Production API request")
  )

  object elements {
    val approveRadioButton: By = By.id("decision")
    val rejectRadioButton: By = By.id("decision-2")
    val rejectedReasonText: By = By.id("rejectedReason")
    val continueButton: By = By.id("continueButton")
  }

  def apply(id: String): AccessRequestPage = {
    new AccessRequestPage(id)
  }

}
