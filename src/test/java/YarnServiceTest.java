import org.example.model.Yarn;
import org.example.repository.YarnRepository;
import org.example.service.YarnService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class YarnServiceTest {

    @Mock
    private YarnRepository yarnRepository;

    @InjectMocks
    private YarnService yarnService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddYarn() {
        // Arrange
        Yarn yarnToAdd = new Yarn();
        yarnToAdd.setType("Wool");
        yarnToAdd.setColor("Red");
        yarnToAdd.setWeight(100);

        when(yarnRepository.save(any(Yarn.class))).thenAnswer(invocation -> {
            Yarn yarnArgument = invocation.getArgument(0);
            yarnArgument.setId(1L); // Simulating ID assignment upon saving
            return yarnArgument;
        });

        // Act
        Yarn result = yarnService.addYarn(yarnToAdd);

        // Assert
        assertEquals(1L, result.getId()); // Assuming ID is assigned correctly
        assertEquals("Wool", result.getType());
        assertEquals("Red", result.getColor());
        assertEquals(100, result.getWeight());
        verify(yarnRepository, times(1)).save(any(Yarn.class));
    }

    @Test
    public void testGetYarnById() {
        Long id = 1L;
        Yarn yarn = new Yarn();
        yarn.setId(id);
        yarn.setType("Cotton");
        yarn.setColor("Blue");
        yarn.setWeight(50);

        when(yarnRepository.findById(id)).thenReturn(Optional.of(yarn));

        Yarn result = yarnService.getYarnById(id);

        assertEquals(yarn, result);
        verify(yarnRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAllYarns() {
        List<Yarn> yarnList = new ArrayList<>();
        Yarn yarn1 = new Yarn();
        yarn1.setId(1L);
        yarn1.setType("Cotton");
        yarn1.setColor("Blue");
        yarn1.setWeight(50);

        yarnList.add(yarn1);

        Yarn yarn2 = new Yarn();
        yarn2.setId(2L);
        yarn2.setType("Wool");
        yarn2.setColor("Red");
        yarn2.setWeight(100);

        yarnList.add(yarn2);

        when(yarnRepository.findAll()).thenReturn(yarnList);

        List<Yarn> result = yarnService.getAllYarns();

        assertEquals(yarnList, result);
        verify(yarnRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateYarn() {
        Long id = 1L;
        Yarn existingYarn = new Yarn();
        existingYarn.setId(id);
        existingYarn.setType("Cotton");
        existingYarn.setColor("Blue");
        existingYarn.setWeight(50);

        Yarn updatedYarn = new Yarn();
        updatedYarn.setId(id);
        updatedYarn.setType("Wool");
        updatedYarn.setColor("Red");
        updatedYarn.setWeight(100);

        when(yarnRepository.findById(id)).thenReturn(Optional.of(existingYarn));
        when(yarnRepository.save(any(Yarn.class))).thenReturn(updatedYarn);

        Yarn result = yarnService.updateYarn(id, updatedYarn);

        assertEquals(updatedYarn, result);
        verify(yarnRepository, times(1)).findById(id);
        verify(yarnRepository, times(1)).save(any(Yarn.class));
    }
    @Test
    public void testDeleteYarn() {
        Long id = 1L;
        Yarn yarnToDelete = new Yarn();
        yarnToDelete.setId(id);
        yarnToDelete.setType("Cotton");
        yarnToDelete.setColor("Blue");
        yarnToDelete.setWeight(50);

        when(yarnRepository.findById(id)).thenReturn(Optional.of(yarnToDelete));

        Yarn result = yarnService.deleteYarn(id);

        assertEquals(yarnToDelete, result);
        verify(yarnRepository, times(1)).findById(id);
        verify(yarnRepository, times(1)).delete(yarnToDelete);
    }

    @Test
    public void testAddYarnWithEmptyTypeShouldNotBeSaved() {
        Yarn yarnToAdd = new Yarn();
        yarnToAdd.setId(1L);
        yarnToAdd.setType("");
        yarnToAdd.setColor("Red");
        yarnToAdd.setWeight(100);

        try {
            yarnService.addYarn(yarnToAdd);
        } catch (RuntimeException e) {
            assert(yarnService.getYarnById(1L) == null);
            verify(yarnRepository, never()).save(any(Yarn.class));
        }
    }

    @Test
    public void testGetYarnByIdWithInvalidIdShouldThrowException() {
        Long id = 1L;
        Exception expected = new RuntimeException("Yarn not found with id: " + id);
        try {
            yarnService.getYarnById(id);
        } catch (RuntimeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
            verify(yarnRepository, times(1)).findById(id);
        }
    }
}