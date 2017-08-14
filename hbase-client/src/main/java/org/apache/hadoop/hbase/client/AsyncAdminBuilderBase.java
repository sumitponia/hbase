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
package org.apache.hadoop.hbase.client;

import java.util.concurrent.TimeUnit;

import org.apache.hadoop.hbase.classification.InterfaceAudience;

/**
 * Base class for all asynchronous admin builders.
 */
@InterfaceAudience.Private
abstract class AsyncAdminBuilderBase<T extends AsyncAdmin> implements AsyncAdminBuilder<T> {

  protected long rpcTimeoutNs;

  protected long operationTimeoutNs;

  protected long pauseNs;

  protected int maxAttempts;

  protected int startLogErrorsCnt;

  AsyncAdminBuilderBase(AsyncConnectionConfiguration connConf) {
    this.rpcTimeoutNs = connConf.getRpcTimeoutNs();
    this.operationTimeoutNs = connConf.getOperationTimeoutNs();
    this.pauseNs = connConf.getPauseNs();
    this.maxAttempts = connConf.getMaxRetries();
    this.startLogErrorsCnt = connConf.getStartLogErrorsCnt();
  }

  @Override
  public AsyncAdminBuilder<T> setOperationTimeout(long timeout, TimeUnit unit) {
    this.operationTimeoutNs = unit.toNanos(timeout);
    return this;
  }

  @Override
  public AsyncAdminBuilder<T> setRpcTimeout(long timeout, TimeUnit unit) {
    this.rpcTimeoutNs = unit.toNanos(timeout);
    return this;
  }

  @Override
  public AsyncAdminBuilder<T> setRetryPause(long timeout, TimeUnit unit) {
    this.pauseNs = unit.toNanos(timeout);
    return this;
  }

  @Override
  public AsyncAdminBuilder<T> setMaxAttempts(int maxAttempts) {
    this.maxAttempts = maxAttempts;
    return this;
  }

  @Override
  public AsyncAdminBuilder<T> setStartLogErrorsCnt(int startLogErrorsCnt) {
    this.startLogErrorsCnt = startLogErrorsCnt;
    return this;
  }
}