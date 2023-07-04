package my.copter.service.crud.impl;

import lombok.AllArgsConstructor;

import my.copter.exception.EmptyFieldException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistance.sql.entity.BaseEntity;
import my.copter.persistance.sql.entity.product.Copter;
import my.copter.persistance.sql.entity.product.CopterImage;
import my.copter.persistance.sql.repository.BaseRepository;
import my.copter.persistance.sql.repository.product.CopterImageRepository;
import my.copter.persistance.sql.repository.product.CopterRepository;
import my.copter.persistance.sql.type.BrandType;
import my.copter.persistance.sql.type.CategoryType;
import my.copter.service.crud.CopterCrudService;
import my.copter.util.ExceptionUtils;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CopterCrudServiceImpl implements CopterCrudService {

    private final CopterRepository copterRepository;
    private final CopterImageRepository copterImageRepository;

    @Override
    @Transactional
    public void create(Copter entity) {
        validateFields(entity);
        copterRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(Copter entity) {
        validateFields(entity);
        checkExistById(entity.getId(), copterRepository);
        copterRepository.save(entity);
    }

    @Override
    @Transactional
    public void attachImage(Long copterId, Long copterImageId) {
        checkExistById(copterId, copterRepository);
        checkExistById(copterImageId, copterImageRepository);
        Copter copter = copterRepository.findById(copterId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND));
        CopterImage image = copterImageRepository.findById(copterImageId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND));
        copter.getCopterImages().add(image);
        copterRepository.save(copter);
    }

    @Override
    @Transactional
    public void detachImage(Long copterId, Long copterImageId) {
        checkExistById(copterId, copterRepository);
        checkExistById(copterImageId, copterImageRepository);
        Copter copter = copterRepository.findById(copterId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND));
        CopterImage image = copterImageRepository.findById(copterImageId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND));
        copter.getCopterImages().remove(image);
        copterRepository.save(copter);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        checkExistById(id, copterRepository);
        copterRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Copter findById(Long id) {
        checkExistById(id, copterRepository);
        return copterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND));
    }

    @Override
    @Transactional
    public Collection<Copter> findAll() {
        return copterRepository.findAll();
    }

    private void validateFields(Copter entity) {
        if (ObjectUtils.isEmpty(entity)) {
            throw new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND);
        }
        if (!EnumUtils.isValidEnum(BrandType.class, entity.getBrand().getName())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getName())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getDescription())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getCameraResolution())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
        if (!EnumUtils.isValidEnum(CategoryType.class, entity.getCategoryType().getValue())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getBattery())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getFlyTime())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(entity.getPrice())) {
            throw new EmptyFieldException(ExceptionUtils.EMPTY_FIELD_EXCEPTION);
        }
    }

    private void checkExistById(Long id, BaseRepository<? extends BaseEntity> repository) {
        if (ObjectUtils.isEmpty(id)) {
            throw new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND);
        }
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(ExceptionUtils.ENTITY_NOT_FOUND);
        }
    }
}
