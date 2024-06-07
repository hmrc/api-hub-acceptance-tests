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
import uk.gov.hmrc.test.ui.pages2.application.EnvironmentAndCredentialsPage._
import uk.gov.hmrc.test.ui.pages2.application.EnvironmentAndCredentialsPage.elements._
import uk.gov.hmrc.test.ui.pages2.{BasePage, PageReadyTest, UrlPageReadyTest}

class EnvironmentAndCredentialsPage(id: String) extends BasePage[EnvironmentAndCredentialsPage](pageReadyTest(id)) {

  def getSecondaryCredentialCount: Int = {
    findElements(secondaryCredentialClientId).size
  }

}

object EnvironmentAndCredentialsPage {

  def pageReadyTest(id: String): PageReadyTest = UrlPageReadyTest(s"application/environment-and-credentials/$id")

  object elements {
    val secondaryCredentialClientId: By = By.cssSelector("[data-secondary-credential-client-id]")
  }

  def apply(id: String): EnvironmentAndCredentialsPage = {
    new EnvironmentAndCredentialsPage(id)
  }

}
