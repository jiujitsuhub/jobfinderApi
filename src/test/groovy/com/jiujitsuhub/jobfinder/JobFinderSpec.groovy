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


    def "when context is loaded then all expected beans are created"() {
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
}