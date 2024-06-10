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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.admin.AccessRequestsPage
import uk.gov.hmrc.test.ui.pages.api.HipApisPage

trait ApiHubMenu {
  self: BasePage[_] =>

  import ApiHubMenu.elements._

  def getUserType: Option[String] = {
    findElementsWithAttribute(userTypeAttribute)
      .headOption
      .map(_.getAttribute(userTypeAttribute).trim)
  }

  def getUserEmail: Option[String] = {
    findElementsWithAttribute(userTypeAttribute)
      .headOption
      .map(_.getAttribute(userTypeAttribute).trim)
  }

  def isSignedIn: Boolean = {
    getUserEmail.isDefined
  }

  def isSignedInWithLdap: Boolean = {
    getUserType.map(_.toUpperCase).contains("LDAP")
  }

  def isSignedInWithStride: Boolean = {
    getUserType.map(_.toUpperCase).contains("STRIDE")
  }

  def dashboard(): DashboardPage = {
    click(dashboardLink)
    DashboardPage()
  }

  def apiHubAdmin(): AccessRequestsPage = {
    click(apiHubAdminLink)
    AccessRequestsPage()
  }

  def hipApis(): HipApisPage = {
    click(hipApisLink)
    HipApisPage()
  }

  //noinspection AccessorLikeMethodIsEmptyParen
  def getSupport(): GetSupportPage = {
    click(getSupportLink)
    GetSupportPage()
  }

  def getHeaderLinkTexts: Seq[String] = {
    findElementsWithAttribute(headerLinkAttribute)
      .map(_.getText)
  }

}

object ApiHubMenu {

  object elements {
    val userTypeAttribute = "data-user-type"
    val userEmailAttribute = "data-user-email"
    val headerLinkAttribute = "data-header-link-name"
    val dashboardLink: By = By.id("dashboardLink")
    val apiHubAdminLink: By = By.id("apiHubAdminLink")
    val hipApisLink: By = By.id("hipApisLink")
    val getSupportLink: By = By.id("getSupportLink")
  }

}
