/*
 * Copyright (C) 2013-2015 terasoluna.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.terasoluna.gfw.common.validator.constraintvalidators;

import static org.terasoluna.gfw.common.validator.constraintvalidators.ConstraintValidatorsUtils.isEmpty;
import static org.terasoluna.gfw.common.validator.constraintvalidators.ConstraintValidatorsUtils.reportFailedToInitialize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Internal validator class of {@link Calendar} type.
 * @see InternalValidator
 * @see AfterValidator
 * @see BeforeValidator
 */
class InternalValidatorForCalendar extends InternalValidator<Calendar> {

    /**
     * Default format.
     */
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isGenericType(Object value) {
        return value instanceof Calendar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isAfter(Calendar value, String date, String format) {
        return value.after(parse(date, format));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isBefore(Calendar value, String date, String format) {
        return value.before(parse(date, format));
    }

    /**
     * Parse string to {@link Calendar}.
     * @param date parse date
     * @param format parse format
     * @return parsed date
     * @throws IllegalArgumentException exception occurs during parse, By invalid date and format.
     */
    private Calendar parse(String date, String format) {
        try {

            DateFormat df = isEmpty(format) ? new SimpleDateFormat(DEFAULT_FORMAT)
                    : new SimpleDateFormat(format);
            df.setLenient(false);

            Calendar cal = Calendar.getInstance();
            cal.setTime(df.parse(date));
            return cal;

        } catch (Exception e) {
            throw reportFailedToInitialize(e);
        }
    }
}
