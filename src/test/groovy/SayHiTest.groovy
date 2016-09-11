import main.SayHiApplication
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.http.HttpMethod
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = SayHiApplication.class)
@WebAppConfiguration
@IntegrationTest
class SayHiTest extends Specification {

    @LocalServerPort
    int port

    def "should have the Spring Boot Actuator Endpoints available and a test method"() {
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
        "test"        || HttpMethod.GET
        "actuator"    || HttpMethod.GET
        "autoconfig"  || HttpMethod.GET
        "beans"       || HttpMethod.GET
        "configprops" || HttpMethod.GET
        "dump"        || HttpMethod.GET
        "env"         || HttpMethod.GET
        "flyway"      || HttpMethod.GET
        "health"      || HttpMethod.GET
        "info"        || HttpMethod.GET
        "liquibase"   || HttpMethod.GET
        "metrics"     || HttpMethod.GET
        "mappings"    || HttpMethod.GET
        "shutdown"    || HttpMethod.GET
        "trace"       || HttpMethod.GET
        "docs"        || HttpMethod.GET
        "heapdump"    || HttpMethod.GET
        "jolokia"     || HttpMethod.GET
        "logfile"     || HttpMethod.GET
    }
}
