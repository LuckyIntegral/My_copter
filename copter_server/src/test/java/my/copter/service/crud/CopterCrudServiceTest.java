package my.copter.service.crud;

import my.copter.data.datatable.DataTableRequest;
import my.copter.exception.EmptyFieldException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.repository.product.CopterRepository;
import my.copter.service.crud.impl.CopterCrudServiceImpl;
import my.copter.util.PersistenceUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static my.copter.util.EntityUtil.*;
import static my.copter.util.ExceptionUtil.EMPTY_FIELD_EXCEPTION;
import static my.copter.util.ExceptionUtil.ENTITY_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CopterCrudServiceTest {
    @InjectMocks
    private CopterCrudServiceImpl crudService;

    @Mock
    private CopterRepository copterRepository;

    @Test
    public void shouldBeCreatedCopterWhenAllFieldsAreEmpty() {
        Copter copter = new Copter();

        Exception exception = Assertions.assertThrows(EmptyFieldException.class, () -> crudService.create(copter));

        verify(copterRepository, never()).save(copter);
        assertThat(exception).isInstanceOf(EmptyFieldException.class);
        assertThat(exception.getMessage()).isEqualTo(EMPTY_FIELD_EXCEPTION);
    }

    @Test
    public void shouldBeCreatedCopterWhenCopterIsNull() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> crudService.create(null));

        verify(copterRepository, never()).save(any());
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldBeCreatedCopterWhenAllFieldsAreValid() {
        Copter copter = getFilledCopter();

        crudService.create(copter);

        verify(copterRepository, times(1)).save(copter);
    }

    @Test
    public void shouldBeCreatedCopterWhenCopterNameIsNull() {
        Copter copter = getFilledCopter();
        copter.setName(null);

        Exception exception = Assertions.assertThrows(EmptyFieldException.class, () -> crudService.create(copter));

        verify(copterRepository, never()).save(copter);
        assertThat(exception).isInstanceOf(EmptyFieldException.class);
        assertThat(exception.getMessage()).isEqualTo(EMPTY_FIELD_EXCEPTION);
    }

    @Test
    public void shouldBeCreatedCopterWhenCopterNameIsBlank() {
        Copter copter = getFilledCopter();
        copter.setName("        ");

        Exception exception = Assertions.assertThrows(EmptyFieldException.class, () -> crudService.create(copter));

        verify(copterRepository, never()).save(copter);
        assertThat(exception).isInstanceOf(EmptyFieldException.class);
        assertThat(exception.getMessage()).isEqualTo(EMPTY_FIELD_EXCEPTION);
    }

    @Test
    public void shouldUpdateCopterWhenAllFieldsAreValid() {
        Copter copter = getFilledCopter();
        copter.setId(COPTER_ID);

        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        crudService.update(copter);

        verify(copterRepository, times(1)).save(copter);
    }

    @Test
    public void shouldThrowEmptyFieldExceptionWhenCopterFieldsAreEmpty() {
        Copter copter = new Copter();

        Exception exception = Assertions.assertThrows(EmptyFieldException.class, () -> crudService.update(copter));

        verify(copterRepository, never()).save(any());
        assertThat(exception).isInstanceOf(EmptyFieldException.class);
        assertThat(exception.getMessage()).isEqualTo(EMPTY_FIELD_EXCEPTION);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCopterDoesNotExist() {
        Copter copter = getFilledCopter();
        copter.setId(INVALID_COPTER_ID);

        when(copterRepository.existsById(INVALID_COPTER_ID)).thenReturn(false);
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> crudService.update(copter));

        verify(copterRepository, never()).save(any());
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldDeleteExistingCopter() {
        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        crudService.delete(COPTER_ID);

        verify(copterRepository, times(1)).deleteById(COPTER_ID);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionDeleteByIdWhenCopterDoesNotExist() {
        when(copterRepository.existsById(INVALID_COPTER_ID)).thenReturn(false);
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> crudService.delete(INVALID_COPTER_ID));

        verify(copterRepository, never()).deleteById(any());
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldFindExistingCopterById() {
        Copter existingCopter = getFilledCopter();
        existingCopter.setId(COPTER_ID);

        when(copterRepository.existsById(COPTER_ID)).thenReturn(true);
        when(copterRepository.findById(COPTER_ID)).thenReturn(Optional.of(existingCopter));
        Copter foundCopter = crudService.findById(COPTER_ID);

        assertThat(foundCopter).isEqualTo(existingCopter);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionFindByIdWhenCopterDoesNotExist() {
        when(copterRepository.existsById(INVALID_COPTER_ID)).thenReturn(false);
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> crudService.findById(INVALID_COPTER_ID));

        verify(copterRepository, never()).findById(any());
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldFindExistingCopterByIdWhenIdIsNull() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> crudService.findById(null));

        verify(copterRepository, never()).findById(any());
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldFindAllCoptersWithCorrectDataTableRequest() {
        DataTableRequest request = getDefaultRequest();
        Pageable pageable = PersistenceUtil.generatePageableByDataTableRequest(request);

        when(copterRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));
        Page<Copter> resultPage = crudService.findAll(request);

        verify(copterRepository, times(1)).findAll(pageable);
        assertThat(resultPage).isEmpty();
    }
}
