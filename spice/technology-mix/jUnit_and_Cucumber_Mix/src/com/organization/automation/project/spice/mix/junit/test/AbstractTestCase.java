/*
 * Copyleft 2013
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.organization.automation.project.spice.mix.junit.test;

import org.apache.log4j.Logger;

import com.organization.automation.project.spice.mix.junit.extension.logging.TAFLoggerFactory;

public class AbstractTestCase implements ITestCase {
	protected static final Logger LOGGER = TAFLoggerFactory.getLogger();
	private static final String DEFAULT_ID = "jUnit Test Case";
	private static final boolean DEFAULT_RESULT = false;
	
	private String id;
	private boolean result;
	
	public boolean getResult() {
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AbstractTestCase() {
		id = DEFAULT_ID;
		result = DEFAULT_RESULT;
	}
	
	public AbstractTestCase(String id) {
		this.id = id;
		result = DEFAULT_RESULT;
	}
	
	@Override
	public void preconditions() throws Exception {
	}

	@Override
	public void init() throws Exception {
	}
	
	@Override
	public void setup() throws Exception {
	}
	
	@Override
	public void steps() throws Exception {
	}
	
	@Override
	public void poststeps() throws Exception {
	}
	
	@Override
	public void check() throws Exception {
		result = true;
	}
	
	@Override
	public void teardown() throws Exception {
		id = null;
	}

	@Override
	public void run() throws TCException {
		try {
			preconditions();
			init();
			setup();
			steps();
			poststeps();
			check();
		} catch (Exception e) {
			LOGGER.error("[TEST]       [EXCEPTION]");
			LOGGER.error("[TEST]        " + e.toString());
			throw new TCException(e);
		} catch (Error e) {
			LOGGER.error("[TEST]       [ERROR]");
			LOGGER.error("[TEST]        " + e.toString());
			throw new TCError(e);
		} finally {
			teardownSafely();
		}
	}
	
	public void teardownSafely() {
		try {
			teardown();
		} catch (Exception e) {
			LOGGER.error(e.toString());
		} catch (Error e) {
			LOGGER.error(e.toString());
		}
	}	
}
