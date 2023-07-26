package my.copter.service.crud;

import my.copter.exception.BadRequestException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.product.CopterImage;
import my.copter.persistence.sql.repository.product.CopterImageRepository;
import my.copter.persistence.sql.repository.product.CopterRepository;
import my.copter.service.crud.impl.ProcessCrudServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static my.copter.util.EntityUtil.*;
import static my.copter.util.ExceptionUtil.BAD_REQUEST_EXCEPTION;
import static my.copter.util.ExceptionUtil.ENTITY_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProcessCrudServiceTest {
    @InjectMocks
    private ProcessCrudServiceImpl crudService;

    @Mock
    private CopterRepository copterRepository;
    @Mock
    private CopterImageRepository copterImageRepository;

    @Test
    public void shouldAttachImageToCopterWhenCopterIdIsInvalid() {
        when(copterRepository.existsById(INVALID_COPTER_ID)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> crudService.attachImage(INVALID_COPTER_ID, COPTER_IMAGE_ID));
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);
        verify(copterImageRepository, never()).findById(anyLong());
        verify(copterRepository, never()).save(any());
    }

    @Test
    public void shouldAttachImageToCopterWhenCopterImageIdIsInvalid() {
        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        when(copterImageRepository.existsById(INVALID_COPTER_IMAGE_ID)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> crudService.attachImage(COPTER_ID, INVALID_COPTER_IMAGE_ID));
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);

        verify(copterRepository, times(1)).existsById(COPTER_ID);
        verify(copterImageRepository, times(1)).existsById(INVALID_COPTER_IMAGE_ID);
        verify(copterRepository, never()).save(any());
    }

    @Test
    public void shouldThrowBadRequestExceptionWhenImageIsAlreadyAttached() {
        Copter copter = getFilledCopter();
        CopterImage attachedImage = getFilledCopterImage();
        copter.getCopterImages().add(attachedImage);

        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        when(copterImageRepository.existsById(COPTER_IMAGE_ID)).thenReturn(true);
        when(copterRepository.findById(COPTER_ID)).thenReturn(Optional.of(copter));
        when(copterImageRepository.findById(COPTER_IMAGE_ID)).thenReturn(Optional.of(attachedImage));

        Exception exception = assertThrows(BadRequestException.class,
                () -> crudService.attachImage(COPTER_ID, COPTER_IMAGE_ID));
        assertThat(exception).isInstanceOf(BadRequestException.class);
        assertThat(exception.getMessage()).isEqualTo(BAD_REQUEST_EXCEPTION);

        verify(copterRepository, times(1)).existsById(COPTER_ID);
        verify(copterImageRepository, times(1)).existsById(COPTER_IMAGE_ID);
        verify(copterRepository, times(1)).findById(COPTER_ID);
        verify(copterImageRepository, times(1)).findById(COPTER_IMAGE_ID);
        verify(copterRepository, never()).save(any());
    }

    @Test
    public void shouldCreateARelationWhenEverythingIsCorrect() {
        Copter copter = getFilledCopter();
        CopterImage image = getFilledCopterImage();

        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        when(copterImageRepository.existsById(COPTER_IMAGE_ID)).thenReturn(true);
        when(copterRepository.findById(COPTER_ID)).thenReturn(Optional.of(copter));
        when(copterImageRepository.findById(COPTER_IMAGE_ID)).thenReturn(Optional.of(image));

        crudService.attachImage(COPTER_ID, COPTER_IMAGE_ID);

        verify(copterRepository, times(1)).existsById(COPTER_ID);
        verify(copterImageRepository, times(1)).existsById(COPTER_IMAGE_ID);
        verify(copterRepository, times(1)).findById(COPTER_ID);
        verify(copterImageRepository, times(1)).findById(COPTER_IMAGE_ID);
        verify(copterRepository, times(1)).save(copter);
        assertThat(copter.getCopterImages()).contains(image);
    }

    @Test
    public void shouldDetachImageFromCopterWhenCopterIdIsInvalid() {
        when(copterRepository.existsById(INVALID_COPTER_ID)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> crudService.detachImage(INVALID_COPTER_ID, COPTER_IMAGE_ID));
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);
        verify(copterImageRepository, never()).findById(anyLong());
        verify(copterRepository, never()).save(any());
    }

    @Test
    public void shouldDetachImageFromCopterWhenCopterImageIdIsInvalid() {
        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        when(copterImageRepository.existsById(INVALID_COPTER_IMAGE_ID)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> crudService.detachImage(COPTER_ID, INVALID_COPTER_IMAGE_ID));
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);

        verify(copterRepository, times(1)).existsById(COPTER_ID);
        verify(copterImageRepository, times(1)).existsById(INVALID_COPTER_IMAGE_ID);
        verify(copterRepository, never()).save(any());
    }

    @Test
    public void shouldThrowBadRequestExceptionWhenImageIsNotAttachedToCopter() {
        Copter copter = getFilledCopter();
        CopterImage attachedImage = getFilledCopterImage();

        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        when(copterImageRepository.existsById(COPTER_IMAGE_ID)).thenReturn(true);
        when(copterRepository.findById(COPTER_ID)).thenReturn(Optional.of(copter));
        when(copterImageRepository.findById(COPTER_IMAGE_ID)).thenReturn(Optional.of(attachedImage));

        Exception exception = assertThrows(BadRequestException.class,
                () -> crudService.detachImage(COPTER_ID, COPTER_IMAGE_ID));
        assertThat(exception).isInstanceOf(BadRequestException.class);
        assertThat(exception.getMessage()).isEqualTo(BAD_REQUEST_EXCEPTION);

        verify(copterRepository, times(1)).existsById(COPTER_ID);
        verify(copterImageRepository, times(1)).existsById(COPTER_IMAGE_ID);
        verify(copterRepository, times(1)).findById(COPTER_ID);
        verify(copterImageRepository, times(1)).findById(COPTER_IMAGE_ID);
        verify(copterRepository, never()).save(any());
    }

    @Test
    public void shouldDetachImageFromCopterWhenEverythingIsCorrect() {
        Copter copter = getFilledCopter();
        CopterImage attachedImage = getFilledCopterImage();
        copter.getCopterImages().add(attachedImage);

        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        when(copterImageRepository.existsById(COPTER_IMAGE_ID)).thenReturn(true);
        when(copterRepository.findById(COPTER_ID)).thenReturn(Optional.of(copter));
        when(copterImageRepository.findById(COPTER_IMAGE_ID)).thenReturn(Optional.of(attachedImage));

        crudService.detachImage(COPTER_ID, COPTER_IMAGE_ID);

        verify(copterRepository, times(1)).existsById(COPTER_ID);
        verify(copterImageRepository, times(1)).existsById(COPTER_IMAGE_ID);
        verify(copterRepository, times(1)).findById(COPTER_ID);
        verify(copterImageRepository, times(1)).findById(COPTER_IMAGE_ID);
        verify(copterRepository, times(1)).save(copter);
        assertThat(copter.getCopterImages()).doesNotContain(attachedImage);
    }
}
