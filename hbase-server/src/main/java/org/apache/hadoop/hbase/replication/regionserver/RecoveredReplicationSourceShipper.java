/*
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
package org.apache.hadoop.hbase.replication.regionserver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.replication.ReplicationQueueStorage;
import org.apache.yetus.audience.InterfaceAudience;

/**
 * Used by a {@link RecoveredReplicationSource}.
 */
@InterfaceAudience.Private
public class RecoveredReplicationSourceShipper extends ReplicationSourceShipper {

  private final Runnable tryFinish;

  public RecoveredReplicationSourceShipper(Configuration conf, String walGroupId,
    ReplicationSourceLogQueue logQueue, RecoveredReplicationSource source,
    ReplicationQueueStorage queueStorage, Runnable tryFinish) {
    super(conf, walGroupId, logQueue, source);
    this.tryFinish = tryFinish;
  }

  @Override
  protected void postFinish() {
    tryFinish.run();
  }
}
