import com.jiujitsuhub.jobfinder.HttpFactory
import com.jiujitsuhub.jobfinder.JobfinderApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

@SpringBootTest(classes = [JobfinderApplication], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobFinderSpec extends Specification {
    @LocalServerPort
    private int port


    def "when health point is called status returned is up"() {
        when:
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:${port}/actuator/health"))
                .timeout(Duration.ofSeconds(2))
                .header("Content-Type", "application/json")
                .GET()
                .build()
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        then:
        response.body().contains("UP")
    }


    def "public endpoints are available"() {
        when:
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client
                .send(HttpFactory.createGetRequest("/api/jobs", port),
                        HttpResponse.BodyHandlers.ofString());
        then:
        response.statusCode() == 200
        response.body() == "[]"
    }

    def "private endpoints are forbidden"() {
        when:
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client
                .send(HttpFactory.createpostRequest("/api/jobs", port, "{}"),
                        HttpResponse.BodyHandlers.ofString());
        then:
        response.statusCode() == 401
    }

}