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

import org.openqa.selenium.By

object ApplicationSuccessPage extends BasePage {
  private val successPanel: String              = ".govuk-panel--confirmation"
  private val registeredApplicationLink: String = ".govuk-body .govuk-link--no-visited-state"

  def isApplicationSuccessDisplayed(): Boolean =
    driver.findElement(By.cssSelector(successPanel)).isDisplayed

  def viewRegisteredApplication(): ApplicationDetailsPage.type = {
    driver.findElement(By.cssSelector(registeredApplicationLink)).click()
    ApplicationDetailsPage
  }
}
