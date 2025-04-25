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

package uk.gov.hmrc.test.ui.utilities

import faker.Faker
import uk.gov.hmrc.test.ui.utilities.Api.{randomTitle, randomVersion}

class Api {
  val title: String = randomTitle
  var id: String = _
  var hod: String = _
  var domain: String = _
  var subDomain: String = _
  var version: String = randomVersion
}

object Api {
  def randomTitle = String.format("%s%s", Faker.ar.loremWord(), Faker.en_GB.loremWord().reverse)
  def randomVersion = Faker.en_GB.appVersion()
}
