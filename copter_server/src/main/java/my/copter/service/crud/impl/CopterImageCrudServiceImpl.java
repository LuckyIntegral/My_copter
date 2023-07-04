package my.copter.service.crud.impl;

import lombok.AllArgsConstructor;

import my.copter.exception.EmptyFieldException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistance.sql.entity.product.CopterImage;
import my.copter.persistance.sql.repository.product.CopterImageRepository;
import my.copter.service.crud.CopterImageCrudService;
import my.copter.util.ExceptionUtils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CopterImageCrudServiceImpl implements CopterImageCrudService {

    private final CopterImageRepository copterImageRepository;

    @Override
    @Transactional
    public void create(CopterImage entity) {
        validateFields(entity);
        copterImageRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(CopterImage entity) {
        validateFields(entity);
        checkExistById(entity.getId());
        copterImageRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        checkExistById(id);
        copterImageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CopterImage findById(Long id) {
        checkExistById(id);
        return copterImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND));
    }

    @Override
    @Transactional
    public Collection<CopterImage> findAll() {
        return copterImageRepository.findAll();
    }

    private void validateFields(CopterImage image) {
        if (ObjectUtils.isEmpty(image)) {
            throw new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND);
        }
        if (StringUtils.isEmpty(image.getImageUrl())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(image.getMainImage())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
    }

    private void checkExistById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND);
        }
        if (!copterImageRepository.existsById(id)) {
            throw new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND);
        }
    }
}
