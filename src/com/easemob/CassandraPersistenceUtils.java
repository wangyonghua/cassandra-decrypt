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
package com.easemob;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

/**
 * @author edanuff
 */
public class CassandraPersistenceUtils {
    public static final char KEY_DELIM = ':';

    /**
     * @return a composite key
     */
    public static Object key(Object... objects) {
        if (objects.length == 1) {
            Object obj = objects[0];
            if ((obj instanceof UUID) || (obj instanceof ByteBuffer)) {
                return obj;
            }
        }
        StringBuilder s = new StringBuilder();
        for (Object obj : objects) {
            if (obj instanceof String) {
                s.append(((String) obj).toLowerCase());
            } else if (obj instanceof List<?>) {
                s.append(key(((List<?>) obj).toArray()));
            } else if (obj instanceof Object[]) {
                s.append(key((Object[]) obj));
            } else if (obj != null) {
                s.append(obj);
            } else {
                s.append("*");
            }

            s.append(KEY_DELIM);
        }

        s.deleteCharAt(s.length() - 1);

        return s.toString();
    }
}
