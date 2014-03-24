/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.commons.core.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.apache.olingo.commons.api.Constants;
import org.apache.olingo.commons.api.data.Entry;

public class JSONFeedSerializer extends AbstractJsonSerializer<JSONFeedImpl> {

  @Override
  protected void doSerialize(final JSONFeedImpl feed, final JsonGenerator jgen, final SerializerProvider provider)
          throws IOException, JsonProcessingException {

    jgen.writeStartObject();

    if (feed.getMetadata() != null) {
      jgen.writeStringField(Constants.JSON_METADATA, feed.getMetadata().toASCIIString());
    }
    if (feed.getId() != null) {
      jgen.writeStringField(Constants.JSON_ID, feed.getId());
    }
    if (feed.getCount() != null) {
      jgen.writeNumberField(Constants.JSON_COUNT, feed.getCount());
    }
    if (feed.getNext() != null) {
      jgen.writeStringField(Constants.JSON_NEXT_LINK, feed.getNext().toASCIIString());
    }

    jgen.writeArrayFieldStart(Constants.JSON_VALUE);
    for (Entry entry : feed.getEntries()) {
      jgen.writeObject(entry);
    }

    jgen.writeEndArray();
  }

}