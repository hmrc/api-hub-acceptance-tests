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

import uk.gov.hmrc.selenium.component.PageObject

abstract class BasePage[T](pageReadyTest: PageReadyTest) extends PageObject with Robot {
  self: T =>

  pageReadyTest.waitUntilReady()

  def foreach[U](f: T => U): this.type = {
    f.apply(this)
    self
  }

}
