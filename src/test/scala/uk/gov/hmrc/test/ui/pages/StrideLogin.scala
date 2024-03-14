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

object StrideLogin extends BasePage {
  private val strideLoginPageTitle = "Stride IdP Login"
  private val pid                  = "pid"
  private val givenName            = "usersGivenName"
  private val surName              = "usersSurname"
  private val emailAddress         = "emailAddress"
  private val roles                = "roles"
  private val submitButton         = "continue-button"

  def fillInLoginDetails(): Homepage.type = {
    driver.findElement(By.id(pid)).sendKeys(Faker.ar.buildingNumber)
    driver.findElement(By.id(continueButton)).click()
    Homepage
  }

  def isPidFieldDisplayed: Boolean =
    driver.findElement(By.id(pid)).isDisplayed

  def fillInAllCredentials(): Unit = {
    driver.findElement(By.id(pid)).sendKeys("7297091")
    driver.findElement(By.id(givenName)).sendKeys("sarita")
    driver.findElement(By.id(surName)).sendKeys("parigi")
    driver.findElement(By.id(emailAddress)).sendKeys("sarita.reddy.parigi@digital.hmrc.gov.uk")
    driver.findElement(By.id(roles)).sendKeys("api_hub_approver")
    driver.findElement(By.id(submitButton)).click()
  }

  def fillInAllExceptRole(): Unit = {
    driver.findElement(By.id(pid)).sendKeys("7297091")
    driver.findElement(By.id(givenName)).sendKeys("sarita")
    driver.findElement(By.id(surName)).sendKeys("parigi")
    driver.findElement(By.id(emailAddress)).sendKeys("sarita.reddy.parigi@digital.hmrc.gov.uk")
    driver.findElement(By.id(submitButton)).click()
  }

}
