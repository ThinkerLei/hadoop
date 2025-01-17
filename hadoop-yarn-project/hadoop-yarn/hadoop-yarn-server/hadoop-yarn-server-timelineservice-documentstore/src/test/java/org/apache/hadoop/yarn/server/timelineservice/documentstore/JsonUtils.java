/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.server.timelineservice.documentstore;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.util.JacksonUtil;

/**
 * A simple util class for Json SerDe.
 */
public final class JsonUtils {

  private JsonUtils(){}

  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.createBasicObjectMapper();

  static {
    OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * Deserialize the Json String to JAVA Object.
   * @param jsonStr
   *               json string that has to be deserialized
   * @param type
   *              of JAVA Object
   * @return JAVA Object after deserialization
   * @throws IOException if Json String is not valid or error
   *                     while deserialization
   */
  public static <T> T fromJson(final String jsonStr, final TypeReference<T> type)
      throws IOException {
    return OBJECT_MAPPER.readValue(jsonStr, type);
  }

  public static ObjectMapper getObjectMapper() {
    return OBJECT_MAPPER;
  }
}