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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.apache.olingo.odata4.commons.api.edm.EdmException;
import org.apache.olingo.odata4.commons.api.edm.EdmNavigationProperty;
import org.apache.olingo.odata4.commons.api.edm.EdmType;
import org.apache.olingo.odata4.commons.api.edm.constants.EdmTypeKind;
import org.apache.olingo.odata4.commons.api.edm.provider.EdmProvider;
import org.apache.olingo.odata4.commons.api.edm.provider.EntityType;
import org.apache.olingo.odata4.commons.api.edm.provider.FullQualifiedName;
import org.apache.olingo.odata4.commons.api.edm.provider.NavigationProperty;
import org.apache.olingo.odata4.commons.api.edm.provider.PropertyRef;
import org.junit.Test;

public class EdmNavigationPropertyImplTest {

  @Test
  public void navigationProperty() throws Exception {
    EdmProvider provider = mock(EdmProvider.class);
    EdmProviderImpl edm = new EdmProviderImpl(provider);
    final FullQualifiedName entityTypeName = new FullQualifiedName("ns", "entity");
    EntityType entityTypeProvider = new EntityType();
    entityTypeProvider.setKey(Collections.<PropertyRef> emptyList());
    when(provider.getEntityType(entityTypeName)).thenReturn(entityTypeProvider);
    NavigationProperty propertyProvider = new NavigationProperty();
    propertyProvider.setType(entityTypeName);
    propertyProvider.setNullable(false);
    EdmNavigationProperty property = new EdmNavigationPropertyImpl(edm, propertyProvider);
    assertFalse(property.isCollection());
    assertFalse(property.isNullable());
    EdmType type = property.getType();
    assertEquals(EdmTypeKind.ENTITY, type.getKind());
    assertEquals("ns", type.getNamespace());
    assertEquals("entity", type.getName());

    //Test caching
    EdmType cachedType = property.getType();
    assertTrue(type == cachedType);
  }

  @Test(expected = EdmException.class)
  public void navigationPropertyWithNonExistentType() throws Exception {
    EdmProviderImpl edm = mock(EdmProviderImpl.class);
    NavigationProperty propertyProvider = new NavigationProperty();
    EdmNavigationProperty property = new EdmNavigationPropertyImpl(edm, propertyProvider);
    property.getType();
  }
}