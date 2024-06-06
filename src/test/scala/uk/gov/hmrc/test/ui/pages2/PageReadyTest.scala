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

package uk.gov.hmrc.test.ui.pages2

sealed trait PageReadyTest {

  def waitUntilReady(): Unit
}

case class UrlPageReadyTest(url: String) extends PageReadyTest with Robot {

  override def waitUntilReady(): Unit = {
    waitForUrl(url)
  }

}

case class TitlePageReadyTest(title: String) extends PageReadyTest with Robot {

  override def waitUntilReady(): Unit = {
    waitForTitle(title)
  }

}

object TitlePageReadyTest {

  def forApiHubTitle(title: String): TitlePageReadyTest = {
    TitlePageReadyTest(s"$title - The API Hub - GOV.UK")
  }

}
