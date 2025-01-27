/*
 * Copyright 2021 VMware, Inc.
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
package io.micrometer.core.instrument.transport.http.context;

import io.micrometer.core.instrument.observation.Observation;
import io.micrometer.core.instrument.transport.http.tags.HttpTagsProvider;
import io.micrometer.core.instrument.transport.http.HttpClientRequest;
import io.micrometer.core.instrument.transport.http.HttpClientResponse;
import io.micrometer.core.lang.NonNull;

/**
 * {@link Observation.Context Context} for an HTTP client request/response.
 *
 * @author Jonatan Ivanov
 * @author Marcin Grzejszczak
 * @since 2.0.0
 */
public class HttpClientContext extends HttpContext<HttpClientRequest, HttpClientResponse> {

    private HttpClientRequest request;

    private HttpClientResponse response;

    /**
     * Creates a new {@code HttpClientContext}.
     */
    public HttpClientContext() {

    }

    /**
     * Creates a new {@code HttpClientContext}.
     *
     * @param request http client request
     */
    public HttpClientContext(HttpClientRequest request) {
        this.request = request;
    }

    public HttpClientContext(HttpClientRequest request, HttpTagsProvider tagsProvider) {
        super(tagsProvider);
        this.request = request;
    }

    @NonNull
    @Override
    public HttpClientRequest getRequest() {
        return this.request;
    }

    @Override
    public HttpClientResponse getResponse() {
        return this.response;
    }

    @Override
    public HttpClientContext setRequest(HttpClientRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public HttpClientContext setResponse(HttpClientResponse response) {
        this.response = response;
        return this;
    }



}
