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

package uk.gov.hmrc.test.ui.pages2.application

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.{BasePage, PageReadyTest, UrlPageReadyTest}
import uk.gov.hmrc.test.ui.pages2.api.HipApisPage
import uk.gov.hmrc.test.ui.pages2.application.ApplicationDetailsPage._
import uk.gov.hmrc.test.ui.pages2.application.ApplicationDetailsPage.elements._

class ApplicationDetailsPage(id: String) extends BasePage[ApplicationDetailsPage](pageReadyTest(id)) {

  def getApplicationId: String = {
    getText(applicationId)
  }

  def getApplicationName: String = {
    getText(applicationName)
  }

  def getCreated: String = {
    getText(applicationCreated)
  }

  def getNoApisMessage: String = {
    getText(noApisMessage)
  }

  def getTeamMembers: Seq[String] = {
    findElements(By.cssSelector("[data-team-member-email]"))
      .map(_.getAttribute("data-team-member-email"))
  }

  def getCountOfTeamMembersFromHeading: Int = {
    val pattern     = "^.*\\((\\d+)\\)$".r

    getText(teamMembersHeading) match {
      case pattern(count) => count.toInt
      case _ => -1
    }
  }

  def hasApiAdded(id: String): Boolean = {
    findElements(By.cssSelector(s"[data-api-id='$id']")).nonEmpty
  }

  def addApis(): HipApisPage = {
    click(hipApisLink)
    HipApisPage()
  }

  def applicationApis(): ApplicationApisPage = {
    val applicationId = getApplicationId
    findElementWithAttributeValue("data-nav-item-page", "ApisPage").click()
    ApplicationApisPage(applicationId)
  }

}

object ApplicationDetailsPage {

  def pageReadyTest(id: String): PageReadyTest = UrlPageReadyTest(s"application/details/$id")

  object elements {
    val applicationId: By = By.id("applicationId")
    val applicationName: By = By.id("applicationName")
    val applicationCreated: By = By.id("applicationCreated")
    val noApisMessage: By = By.id("noApisMessage")
    val hipApisLink: By = By.id("hipApisLink")
    val teamMembersHeading: By = By.id("teamMembersHeading")
  }

  def apply(id: String): ApplicationDetailsPage = {
    new ApplicationDetailsPage(id)
  }

}
