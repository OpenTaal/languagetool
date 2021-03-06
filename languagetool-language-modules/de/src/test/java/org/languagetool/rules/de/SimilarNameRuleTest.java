/* LanguageTool, a natural language style checker 
 * Copyright (C) 2015 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.de;

import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.TestTools;
import org.languagetool.language.German;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimilarNameRuleTest {

  @Test
  public void testRule() throws IOException {
    SimilarNameRule rule = new SimilarNameRule(TestTools.getEnglishMessages());
    JLanguageTool lt = new JLanguageTool(new German());
    assertErrors("Hier steht Angela Müller. Im nächsten Satz dann Miller.", 1, rule, lt);
    assertErrors("Hier steht Angela Müller. Im nächsten Satz dann Müllers Ehemann.", 0, rule, lt);
    assertErrors("Hier steht Angela Müller. Dann Mulla, nicht ähnlich genug.", 0, rule, lt);
  }

  private void assertErrors(String input, int expectedMatches, SimilarNameRule rule, JLanguageTool lt) throws IOException {
    assertThat(rule.match(lt.getAnalyzedSentence(input)).length, is(expectedMatches));
    rule.reset();
  }

}
