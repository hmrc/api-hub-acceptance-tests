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

package uk.gov.hmrc.test.ui.pages.team

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.pages.team.ManageTeamPage._
import uk.gov.hmrc.test.ui.pages.team.ManageTeamPage.elements._

class ManageTeamPage(id: String) extends BasePage[ManageTeamPage](pageReadyTest(id)) {

  def getTeamId: String = {
    id
  }

  def getTeamName: String = {
    findElement(h1).getText match {
      case titleRegex(team)  => team
      case _ => ""
    }
  }

  def getTeamMemberEmails: Seq[String] = {
    findElements(teamMemberEmails).map(_.getAttribute(teamMemberEmailAttribute).trim)
  }

}

object ManageTeamPage {

  val titleRegex = "Manage (.+) team".r

  def pageReadyTest(id: String): PageReadyTest = {
    PageReadyTests.apiHubPage.url(s"team/$id")
  }

  object elements {
    val teamMemberEmailAttribute: String = "data-team-member-email"
    val teamMemberEmails: By = By.cssSelector(s"[$teamMemberEmailAttribute]")
    val h1: By = By.id("h1")
  }

  def apply(id: String): ManageTeamPage = {
    new ManageTeamPage(id)
  }

}
