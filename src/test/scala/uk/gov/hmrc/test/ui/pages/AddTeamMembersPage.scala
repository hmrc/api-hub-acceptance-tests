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

object AddTeamMembersPage extends BasePage {
  private val teamMembersHeading: String = ".govuk-fieldset__heading"
  private val noRadioBtn: String         = "value-no"
  private val continueBtn: String        = ".govuk-button"

  def isNoRadioButtonSelected(): Boolean = {
    waitForElementPresent(driver.findElement(By.id(noRadioBtn)))
    driver.findElement(By.id(noRadioBtn)).isSelected
  }

  def isContinueButtonDisplayed(): Boolean =
    driver.findElement(By.cssSelector(continueBtn)).isDisplayed

  def getHeadingText(): String =
    driver.findElement(By.cssSelector(teamMembersHeading)).getText.trim
}
