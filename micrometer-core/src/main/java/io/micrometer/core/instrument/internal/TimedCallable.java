/*
 * Copyright 2019 VMware, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument.internal;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.util.concurrent.Callable;

/**
 * A wrapper for a {@link Callable} with idle and execution timings.
 */
class TimedCallable<V> implements Callable<V> {
    private final MeterRegistry registry;
    private final Timer executionTimer;
    private final Timer idleTimer;
    private final Callable<V> callable;
    private final Timer.Sample idleSample;

    TimedCallable(MeterRegistry registry, Timer executionTimer, Timer idleTimer, Callable<V> callable) {
        this.registry = registry;
        this.executionTimer = executionTimer;
        this.idleTimer = idleTimer;
        this.callable = callable;
        this.idleSample = Timer.start(registry);
    }

    @Override
    public V call() throws Exception {
        idleSample.stop(idleTimer);
        Timer.Sample executionSample = Timer.start(registry);
        try {
            return callable.call();
        } finally {
            executionSample.stop(executionTimer);
        }
    }
}
