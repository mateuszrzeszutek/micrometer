/*
 * Copyright 2022 VMware, Inc.
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

package io.micrometer.core.instrument.dropwizard;

import io.micrometer.core.instrument.MockClock;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DropwizardClock}
 *
 * @author Oleksii Bondar
 */
class DropwizardClockTest {

    private MockClock clock = new MockClock();
    private DropwizardClock dropwizardClock = new DropwizardClock(clock);

    @Test
    void returnTick() {
        assertThat(dropwizardClock.getTick()).isEqualTo(clock.monotonicTime());
    }

    @Test
    void returnTime() {
        assertThat(dropwizardClock.getTime()).isEqualTo(clock.wallTime());
    }
}
