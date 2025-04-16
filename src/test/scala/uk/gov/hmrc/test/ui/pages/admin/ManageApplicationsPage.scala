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
import uk.gov.hmrc.test.ui.pages.admin.ManageApplicationsPage.elements.{appFilter, applicationLink}
import uk.gov.hmrc.test.ui.pages.{ApiHubMenu, BasePage, PageReadyTest, PageReadyTests}

class ManageApplicationsPage extends BasePage[ManageApplicationsPage](ManageApplicationsPage.pageReadyTest()) with ApiHubMenu {

  def setFilterText(text: String) = {
    sendKeys(appFilter, text)
  }

  def hasApi(id: String): Boolean = {
      exists(applicationLink(id))
    }
}

object ManageApplicationsPage {

  def pageReadyTest(): PageReadyTest = {
    PageReadyTests.apiHubPage.url(s"admin/applications")
  }

  object elements {
    val appFilter: By = By.id("appFilter")
    def applicationLink(id: String): By = By.cssSelector(s"[data-app-id='$id']")
  }

  def apply(): ManageApplicationsPage = {
    new ManageApplicationsPage()
  }

}
