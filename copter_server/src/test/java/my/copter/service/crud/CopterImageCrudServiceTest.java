package my.copter.service.crud;

import my.copter.data.datatable.DataTableRequest;
import my.copter.exception.EmptyFieldException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.product.CopterImage;
import my.copter.persistence.sql.repository.product.CopterImageRepository;
import my.copter.service.crud.impl.CopterImageCrudServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static my.copter.util.EntityUtil.*;
import static my.copter.util.ExceptionUtil.ENTITY_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CopterImageCrudServiceTest {

    @InjectMocks
    private CopterImageCrudServiceImpl crudService;

    @Mock
    private CopterImageRepository copterImageRepository;

    @Test
    public void shouldCreateCopterImageWhenFieldsAreValid() {
        CopterImage copterImage = getFilledCopterImage();

        crudService.create(copterImage);

        verify(copterImageRepository, times(1)).save(copterImage);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCopterImageIsNull() {
        assertThrows(EntityNotFoundException.class, () -> crudService.create(null));

        verify(copterImageRepository, never()).save(any());
    }

    @Test
    public void shouldThrowEmptyFieldExceptionWhenCopterImageUrlIsBlank() {
        CopterImage copterImage = getFilledCopterImage();
        copterImage.setImageUrl("      ");

        assertThrows(EmptyFieldException.class, () -> crudService.create(copterImage));

        verify(copterImageRepository, never()).save(any());
    }

    @Test
    public void shouldThrowEmptyFieldExceptionWhenCopterImageUrlIsNull() {
        CopterImage copterImage = getFilledCopterImage();
        copterImage.setImageUrl(null);

        assertThrows(EmptyFieldException.class, () -> crudService.create(copterImage));

        verify(copterImageRepository, never()).save(any());
    }

    @Test
    public void shouldUpdateCopterImageWhenFieldsAreValid() {
        CopterImage existingCopterImage = getFilledCopterImage();
        existingCopterImage.setId(COPTER_IMAGE_ID);
        CopterImage updatedCopterImage = getFilledCopterImage();
        updatedCopterImage.setId(COPTER_IMAGE_ID);
        updatedCopterImage.setImageUrl("https://example.com/updated.jpg");

        when(copterImageRepository.existsById(existingCopterImage.getId())).thenReturn(true);
        when(copterImageRepository.save(updatedCopterImage)).thenReturn(updatedCopterImage);

        crudService.update(updatedCopterImage);

        verify(copterImageRepository, times(1)).save(updatedCopterImage);
    }

    @Test
    public void shouldUpdateMethodThrowEntityNotFoundExceptionWhenCopterImageIsNull() {
        assertThrows(EntityNotFoundException.class, () -> crudService.update(null));

        verify(copterImageRepository, never()).save(any());
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCopterImageIdDoesNotExist() {
        CopterImage copterImage = getFilledCopterImage();

        when(copterImageRepository.existsById(copterImage.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> crudService.update(copterImage));

        verify(copterImageRepository, never()).save(any());
    }

    @Test
    public void shouldThrowEmptyFieldExceptionWhenCopterImageUrlIsEmpty() {
        CopterImage copterImage = getFilledCopterImage();
        copterImage.setImageUrl("");

        when(copterImageRepository.existsById(copterImage.getId())).thenReturn(true);
        assertThrows(EmptyFieldException.class, () -> crudService.update(copterImage));

        verify(copterImageRepository, never()).save(any());
    }

    @Test
    public void shouldDeleteExistingCopterImage() {
        when(copterImageRepository.existsById(COPTER_IMAGE_ID)).thenReturn(true);

        assertDoesNotThrow(() -> crudService.delete(COPTER_IMAGE_ID));
        verify(copterImageRepository, times(1)).deleteById(COPTER_IMAGE_ID);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenDeletingNonExistingCopterImage() {
        when(copterImageRepository.existsById(INVALID_COPTER_IMAGE_ID)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> crudService.delete(INVALID_COPTER_IMAGE_ID));
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);

        verify(copterImageRepository, never()).deleteById(INVALID_COPTER_IMAGE_ID);
    }

    @Test
    public void shouldReturnExistingCopterImageById() {
        CopterImage existingCopterImage = getFilledCopterImage();
        existingCopterImage.setId(COPTER_ID);

        when(copterImageRepository.existsById(COPTER_ID)).thenReturn(true);
        when(copterImageRepository.findById(COPTER_ID)).thenReturn(Optional.of(existingCopterImage));

        CopterImage result = crudService.findById(COPTER_ID);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(COPTER_ID);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenFindingNonExistingCopterImage() {
        when(copterImageRepository.existsById(INVALID_COPTER_ID)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> crudService.findById(INVALID_COPTER_ID));
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);

        verify(copterImageRepository, never()).findById(INVALID_COPTER_ID);
    }

    @Test
    public void shouldReturnAllCopterImagesAsPage() {
        DataTableRequest request = getDefaultRequest();
        List<CopterImage> copterImages = List.of(getFilledCopterImage(), getFilledCopterImage());
        Page<CopterImage> expectedPage = new PageImpl<>(copterImages);

        when(copterImageRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

        Page<CopterImage> result = crudService.findAll(request);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo(copterImages);
    }
}

