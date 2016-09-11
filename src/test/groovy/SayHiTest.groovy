import main.SayHiApplication
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = SayHiApplication)
@WebAppConfiguration
@IntegrationTest
class SayHiTest extends Specification {

    @LocalServerPort
    int port

    def "should say hi on / call"() {
        given:
        def restTemplate = new RestTemplate()

        when:
        def response = restTemplate.exchange("http://localhost:$port/", HttpMethod.GET, null, String.class)

        then:
        response.getStatusCode().is2xxSuccessful()
        response.getBody() == 'hi'
    }

    def "should have the Spring Boot Actuator Endpoints available"() {
        given:
        def restTemplate = new RestTemplate()
        def url = "http://localhost:$port/$endpoint"

        when:
        def response = null
        try {
            response = restTemplate.exchange(url, method, null, String.class)
        } catch (Exception e) {
            println("Missing $url")
        }

        then:
        response.getStatusCode().is2xxSuccessful()

        where:
        endpoint      || method
        "actuator"    || HttpMethod.GET
        "autoconfig"  || HttpMethod.GET
        "beans"       || HttpMethod.GET
        "configprops" || HttpMethod.GET
        "dump"        || HttpMethod.GET
        "env"         || HttpMethod.GET
        "health"      || HttpMethod.GET
        "info"        || HttpMethod.GET
        "metrics"     || HttpMethod.GET
        "mappings"    || HttpMethod.GET
        "trace"       || HttpMethod.GET
        "heapdump"    || HttpMethod.GET
    }
}
