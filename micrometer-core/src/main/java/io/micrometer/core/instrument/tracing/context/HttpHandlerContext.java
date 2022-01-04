/**
 * Copyright 2021 VMware, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument.tracing.context;

import io.micrometer.core.instrument.Timer;
import io.micrometer.core.lang.NonNull;
import io.micrometer.core.lang.Nullable;
import io.micrometer.core.instrument.transport.http.Request;
import io.micrometer.core.instrument.transport.http.Response;

/**
 * {@link io.micrometer.core.instrument.Timer.HandlerContext HandlerContext} for an HTTP exchange.
 *
 * @author Marcin Grzejszczak
 * @since 2.0.0
 * @param <REQ> request type
 * @param <RES> response type
 */
public abstract class HttpHandlerContext<REQ extends Request, RES extends Response> extends Timer.HandlerContext {

    /**
     * Returns the HTTP request.
     *
     * @return request
     */
    @NonNull
    abstract REQ getRequest();

    /**
     * Returns the HTTP response.
     *
     * @return response
     */
    @Nullable
    abstract RES getResponse();

    /**
     * Sets the given HTTP response for this context. Might be {@code null} when an
     * exception occurred and there is no response.
     *
     * @param response HTTP response
     * @return this
     */
    abstract HttpHandlerContext<REQ, RES> setResponse(RES response);

}