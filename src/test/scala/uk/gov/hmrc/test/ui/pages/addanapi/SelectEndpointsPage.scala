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

package uk.gov.hmrc.test.ui.pages.addanapi

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.addanapi.SelectEndpointsPage._
import uk.gov.hmrc.test.ui.pages.addanapi.SelectEndpointsPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, ErrorSummary, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode}

class SelectEndpointsPage(mode: Mode) extends BasePage[SelectEndpointsPage](pageReadyTest(mode)) with ErrorSummary {

  def selectAllEndpoints(): ReviewUsagePolicyPage = {
    findElements(selectEndpointCheckbox)
      .filter(!_.isSelected)
      .foreach(_.click())
    click(continueButton)
    ReviewUsagePolicyPage()
  }

  def selectNoEndpoints(): SelectEndpointsPage = {
    click(continueButton)
    SelectEndpointsPage()
  }

  def getScopes: Seq[String] = {
    findElements(scopes)
      .map(_.getAttribute(scopeAttribute).trim)
  }

}

object SelectEndpointsPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("apis/add-an-api/select-endpoints", mode)
  }

  object elements {
    val scopeAttribute = "data-scope"
    val selectEndpointCheckbox: By = By.cssSelector(".govuk-checkboxes__input")
    val scopes: By = By.cssSelector(s"[$scopeAttribute]")
    val continueButton: By = By.id("continueButton")
  }

  def apply(mode: Mode = NormalMode): SelectEndpointsPage = {
    new SelectEndpointsPage(mode)
  }

}
