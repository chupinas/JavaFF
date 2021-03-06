/*
 * Copyright 2016 Mohamed Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.format.exception.FormatException;
import io.github.rhkiswani.javaff.lang.utils.StringUtils;
import io.github.rhkiswani.javaff.log.Log;
import io.github.rhkiswani.javaff.log.LogFactory;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.format.DefaultFormatter
 * @see io.github.rhkiswani.javaff.format.Formatter
 *
 */
class DateFormatter extends DefaultFormatter<Date, String> {

    private final Log LOGGER = LogFactory.getLogger(this.getClass());

    @Override
    protected String formatVal(Date date, Object... params) {
        if (params.length > 0 && params[0] != null){
            try {
                if (StringUtils.isEmpty(params[0] + "")){
                    throw new FormatException(SmartException.TYPE_ERROR, "Date format", date);
                }
                SimpleDateFormat format = new SimpleDateFormat(params[0].toString());
                return format.format(date);
            }catch (Throwable t){
                LOGGER.error("Failed to format {0} due to {1} ", date, t.getMessage());
                throw new FormatException(SmartException.TYPE_ERROR, "Date format", date);
            }
        } else {
            return MessageFormat.format("{0}", date);
        }
    }

    public String format(Date date, String pattern){
        return format(date , new Object[]{pattern});
    }
}
