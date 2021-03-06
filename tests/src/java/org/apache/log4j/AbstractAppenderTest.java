/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j;

import java.io.Writer;

import org.apache.log4j.net.SMTPAppenderTest;
import org.apache.log4j.spi.LoggingEvent;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * An abstract test case which can be subclassed to derived to check the
 * certain (limited) aspects of Appender implementations. 
 * 
 * @author <a href="http://www.qos.ch/log4j/">Ceki G&uuml;lc&uuml;</a>
 *
 */
abstract public class AbstractAppenderTest extends TestCase {
  
  abstract protected AppenderSkeleton getAppender();
  abstract protected AppenderSkeleton getConfiguredAppender();


  public class DummyLayout extends Layout {
    public String format(LoggingEvent event) { return ""; }
    public void activateOptions() {} 
  }

  public void testNewAppender() {
    // new appenders whould be inactive
    AppenderSkeleton appender = getAppender();
    assertFalse( appender.isActive());
    assertFalse(appender.isClosed());
    
    appender.close();
    assertTrue(appender.isClosed());
  }
  
  public void testConfiguredAppender() {
    AppenderSkeleton appender = getConfiguredAppender();
    appender.activateOptions();
    assertTrue(appender.isActive());
    assertFalse(appender.isClosed());
    
    appender.close();
    assertTrue(appender.isClosed());
  }
 
  
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(WriterAppenderTest.class);
    suite.addTestSuite(ConsoleAppenderTest.class);
    suite.addTestSuite(FileAppenderTest.class);
    suite.addTestSuite(SMTPAppenderTest.class);
    return suite;
  }
  
}
