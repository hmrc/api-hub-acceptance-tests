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

import faker.Faker
import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.utilities.Application

object ApplicationName extends BasePage {
  val appNameLcr          = "value"
  val continue            = ".govuk-button"
  val randAppName: String = Application.Name

  def clearApplicationName(): Unit =
    driver.findElement(By.id(appNameLcr)).clear()

  def fillInApplicationName(input: String): TeamMembers.type = {
    driver.findElement(By.id(appNameLcr)).sendKeys(input)
    driver.findElement(By.cssSelector(continue)).click()
    TeamMembers
  }
}
