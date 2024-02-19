import org.example.model.Yarn;
import org.example.repository.YarnRepository;
import org.example.service.YarnService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class YarnServiceTest {

    @Mock
    private YarnRepository yarnRepository;

    @InjectMocks
    private YarnService yarnService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // In-memory database initialization (replace with your chosen library)
        EmbeddedDatabase embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
        embeddedDatabase.create();

        // Inject the in-memory database into your repository
        // (Adapt this based on your specific repository implementation)
        yarnRepository.setEntityManagerFactory(embeddedDatabase.getEntityManagerFactory());
    }

    @After
    public void tearDown() {
        // Clean up the in-memory database
        embeddedDatabase.shutdown();
    }

    @Test
    public void addYarn_savesYarnAndReturnsIt() {
        Yarn yarn = new Yarn();
        yarn.setType("Cotton");
        yarn.setColor("White");
        yarn.setWeight(100);

        Yarn savedYarn = yarnService.addYarn(yarn);

        assertThat(savedYarn).isNotNull();
        assertThat(savedYarn.getType()).isEqualTo(yarn.getType());
        assertThat(savedYarn.getColor()).isEqualTo(yarn.getColor());
        assertThat(savedYarn.getWeight()).isEqualTo(yarn.getWeight());

        // Verify repository interactions using Mockito
        verify(yarnRepository).save(yarn);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addYarn_withNullYarn_throwsException() {
        yarnService.addYarn(null);
    }

    // ... more test methods for other service functionalities ...

}
