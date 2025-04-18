/*
 * RELP Commit Latency Probe RLP-11
 * Copyright (C) 2024 Suomen Kanuuna Oy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *
 * Additional permission under GNU Affero General Public License version 3
 * section 7
 *
 * If you modify this Program, or any covered work, by linking or combining it
 * with other code, such other code is not for that reason alone subject to any
 * of the requirements of the GNU Affero GPL version 3 as long as this Program
 * is the same Program as licensed from Suomen Kanuuna Oy without any additional
 * modifications.
 *
 * Supplemented terms under GNU Affero General Public License version 3
 * section 7
 *
 * Origin of the software must be attributed to Suomen Kanuuna Oy. Any modified
 * versions must be marked as "Modified version of" The Program.
 *
 * Names of the licensors and authors may not be used for publicity purposes.
 *
 * No rights are granted for use of trade names, trademarks, or service marks
 * which are in The Program if any.
 *
 * Licensee must indemnify licensors and authors for any liability that these
 * contractual assumptions impose on licensors and authors.
 *
 * To the extent this program is licensed as part of the Commercial versions of
 * Teragrep, the applicable Commercial License may apply to this file if you as
 * a licensee so wish it.
 */
package com.teragrep.rlp_11.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MetricsConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsConfiguration.class);
    private final Map<String, String> config;

    public MetricsConfiguration(final Map<String, String> config) {
        this.config = config;
    }

    public int window() {
        final String windowString = config.get("metrics.window");
        if (windowString == null) {
            LOGGER.error("Configuration failure: <metrics.window> is null");
            throw new ConfigurationException("Invalid value for <metrics.window> received");
        }
        final int window;
        try {
            window = Integer.parseInt(windowString);
        }
        catch (NumberFormatException e) {
            LOGGER.error("Configuration failure: Invalid value for <metrics.window>: <{}>", e.getMessage());
            throw e;
        }
        if (window <= 0) {
            LOGGER.error("Configuration failure: <metrics.window> <[{}]> too small, expected to be >0", window);
            throw new ConfigurationException("Invalid value for <metrics.window> received");
        }
        return window;
    }

    public int interval() {
        final String intervalString = config.get("metrics.interval");
        if (intervalString == null) {
            LOGGER.error("Configuration failure: <metrics.interval> is null");
            throw new ConfigurationException("Invalid value for <metrics.interval> received");
        }
        final int interval;
        try {
            interval = Integer.parseInt(intervalString);
        }
        catch (NumberFormatException e) {
            LOGGER.error("Configuration failure: Invalid value for <metrics.interval>: <{}>", e.getMessage());
            throw e;
        }
        if (interval <= 0) {
            LOGGER.error("Configuration failure: <metrics.interval> <[{}]> too small, expected to be >0", interval);
            throw new ConfigurationException("Invalid value for <metrics.interval> received");
        }
        return interval;
    }
}
