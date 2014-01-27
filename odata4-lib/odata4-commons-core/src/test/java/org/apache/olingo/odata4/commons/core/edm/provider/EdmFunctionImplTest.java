/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.olingo.odata4.commons.core.edm.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.apache.olingo.odata4.commons.api.edm.EdmException;
import org.apache.olingo.odata4.commons.api.edm.EdmFunction;
import org.apache.olingo.odata4.commons.api.edm.EdmReturnType;
import org.apache.olingo.odata4.commons.api.edm.provider.FullQualifiedName;
import org.apache.olingo.odata4.commons.api.edm.provider.Function;
import org.apache.olingo.odata4.commons.api.edm.provider.ReturnType;
import org.junit.Before;
import org.junit.Test;

public class EdmFunctionImplTest {

  private EdmFunction functionImpl1;
  private EdmFunction functionImpl2;

  @Before
  public void setupFunctions() {
    EdmProviderImpl provider = mock(EdmProviderImpl.class);

    Function function1 = new Function().setReturnType(new ReturnType().setType(new FullQualifiedName("Edm", "String")));
    functionImpl1 = new EdmFunctionImpl(provider, new FullQualifiedName("namespace", "name"), function1);
    Function function2 = new Function().setComposable(true);
    functionImpl2 = new EdmFunctionImpl(provider, new FullQualifiedName("namespace", "name"), function2);
  }

  @Test
  public void isComposableDefaultFalse() {
    assertFalse(functionImpl1.isComposable());
  }

  @Test
  public void isComposableSetToTrue() {
    assertTrue(functionImpl2.isComposable());
  }

  @Test
  public void existingReturnTypeGetsReturned() {
    EdmReturnType returnType = functionImpl1.getReturnType();
    assertNotNull(returnType);
    assertEquals("String", returnType.getType().getName());
  }

  @Test(expected = EdmException.class)
  public void nonExistingReturnTypeResultsInException() {
    functionImpl2.getReturnType();
    fail();
  }

}
