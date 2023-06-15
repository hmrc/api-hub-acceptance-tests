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
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object CreateSignIn extends BasePage {
  val principal         = "#principal"
  val email             = "#email"
  val redirectUrl       = "#redirectUrl"
  val resourceType      = "#permissions_0_resourceTypes"
  val resourceLocations = "#permissions_0_resourceLocations"
  val actions           = "#permissions_0_actions"
  val signIn            = "button[name='fake-sign-in-btn']"

  def defaultLoginUser(): Unit = {
    waitForElementPresent(driver.findElement(By.cssSelector(principal)))
    driver.findElement(By.cssSelector(principal)).sendKeys("auto-test")
    driver.findElement(By.cssSelector(email)).sendKeys("ade.oke@digital.hmrc.gov.uk")
    driver.findElement(By.cssSelector(redirectUrl)).sendKeys(TestConfiguration.url("api-hub"))
    driver.findElement(By.cssSelector(resourceType)).sendKeys("api-hub-frontend")
    driver.findElement(By.cssSelector(resourceLocations)).sendKeys("approvals")
    driver.findElement(By.cssSelector(actions)).sendKeys("WRITE")
    driver.findElement(By.cssSelector(signIn)).click()
  }
}
