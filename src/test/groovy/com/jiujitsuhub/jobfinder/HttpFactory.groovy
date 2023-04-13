package com.jiujitsuhub.jobfinder

import java.net.http.HttpRequest

class HttpFactory {


    static HttpRequest createGetRequest(String path, int port) {
        return HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:${port}/${path}"))
                .header("Content-Type", "application/json")
                .GET()
                .build()
    }

    static HttpRequest createpostRequest(String path, int port, String body) {
        return HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:${port}/${path}"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build()
    }

}
