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

import java.text.SimpleDateFormat
import java.util.Calendar

object DateFormatterUtil {
  private var dateFormat: String = _
  def getFormattedDate: String = {
    val calendarInstance = Calendar.getInstance()

    if (calendarInstance.get(Calendar.DATE) >= 10) {
      dateFormat = "dd MMMM yyyy"
    } else {
      dateFormat = "d MMMM yyyy"
    }
    val today         = calendarInstance.getTime
    val dateFormatter = new SimpleDateFormat(dateFormat)
    dateFormatter.format(today)
  }
}
