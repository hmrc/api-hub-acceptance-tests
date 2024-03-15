/*
 * Copyright 2023 HM Revenue & Customs
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

import org.openqa.selenium.{By, WebElement}

object SignInPage extends BasePage {
  val path: String                   = "/sign-in"
  private val ldapContinue: String   = ".hipp-card:first-of-type a.govuk-button"
  private val strideContinue: String = ".hipp-card:nth-of-type(2) .govuk-button"

  def ldapContinueButton(): WebElement =
    driver.findElement(By.cssSelector(ldapContinue))

  def strideContinueButton(): WebElement =
    driver.findElement(By.cssSelector(strideContinue))

  def isLdapContinueButtonDisplayed: Boolean = {
    waitForElementPresent(ldapContinueButton())
    ldapContinueButton().isDisplayed
  }

  def clickLdapContinue(): Unit =
    ldapContinueButton().click()

  def clickStrideContinue(): Unit =
    strideContinueButton().click()
}
