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
import uk.gov.hmrc.test.ui.pages.api.ExploreApisPage

trait ApiHubMenu {
  self: BasePage[_] =>

  import ApiHubMenu.elements._

  def getUserType: Option[String] = {
    findElements(userType)
      .headOption
      .map(_.getAttribute(userTypeAttribute).trim)
  }

  def getUserEmail: Option[String] = {
    findElements(userEmail)
      .headOption
      .map(_.getAttribute(userEmailAttribute).trim)
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

  def exploreApis(): ExploreApisPage = {
    click(exploreApisLink)
    ExploreApisPage()
  }

  //noinspection AccessorLikeMethodIsEmptyParen
  def getSupport(): GetSupportPage = {
    click(getSupportLink)
    GetSupportPage()
  }

  def getHelpGuide(): GetHelpGuidePage = {
    click(helpDocsLink)
    GetHelpGuidePage()
  }

  def getHeaderLinkTexts: Seq[String] = {
    findElements(headerLinks)
      .map(_.getText)
  }

}

object ApiHubMenu {

  object elements {
    val userTypeAttribute = "data-user-type"
    val userEmailAttribute = "data-user-email"
    val userType: By = By.cssSelector(s"[$userTypeAttribute]")
    val userEmail: By = By.cssSelector(s"[$userEmailAttribute]")
    val headerLinks: By = By.cssSelector("[data-header-link-name]")
    val dashboardLink: By = By.id("dashboardLink")
    val apiHubAdminLink: By = By.id("apiHubAdminLink")
    val exploreApisLink: By = By.id("exploreApisLink")
    val getSupportLink: By = By.id("getSupportLink")
    val helpDocsLink: By = By.id("helpDocsLink")
  }

}
