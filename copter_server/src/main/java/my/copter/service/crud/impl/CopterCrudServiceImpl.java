package my.copter.service.crud.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.exception.EmptyFieldException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.BaseEntity;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.product.CopterImage;
import my.copter.persistence.sql.repository.BaseRepository;
import my.copter.persistence.sql.repository.product.CopterImageRepository;
import my.copter.persistence.sql.repository.product.CopterRepository;
import my.copter.persistence.sql.type.BrandType;
import my.copter.persistence.sql.type.CategoryType;
import my.copter.service.crud.CopterCrudService;
import my.copter.util.ExceptionUtil;
import my.copter.util.PersistenceUtil;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        CopterImage image = copterImageRepository.findById(copterImageId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        copter.getCopterImages().add(image);
        copterRepository.save(copter);
    }

    @Override
    @Transactional
    public void detachImage(Long copterId, Long copterImageId) {
        checkExistById(copterId, copterRepository);
        checkExistById(copterImageId, copterImageRepository);
        Copter copter = copterRepository.findById(copterId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        CopterImage image = copterImageRepository.findById(copterImageId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
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
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
    }

    @Override
    @Transactional
    public Page<Copter> findAll(DataTableRequest request) {
        return copterRepository.findAll(PersistenceUtil.generatePageableByDataTableRequest(request));
    }

    @Override
    public Page<Copter> findAllByBrand(String brand, DataTableRequest request) {
        return copterRepository.findAllByBrand(
                BrandType.valueOf(brand),
                PersistenceUtil.generatePageableByDataTableRequest(request)
        );
    }

    @Override
    public Page<Copter> findAllByCategory(String category, DataTableRequest request) {
        return copterRepository.findAllByCategoryType(
                CategoryType.valueOf(category),
                PersistenceUtil.generatePageableByDataTableRequest(request)
        );
    }

    private void validateFields(Copter entity) {
        if (ObjectUtils.isEmpty(entity)) {
            throw new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND);
        }
        if (!EnumUtils.isValidEnum(BrandType.class, entity.getBrand().toString())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getName())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getDescription())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getCameraResolution())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (!EnumUtils.isValidEnum(CategoryType.class, entity.getCategoryType().toString())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getBattery())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isEmpty(entity.getFlyTime())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(entity.getPrice())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(entity.getQuantity())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
    }

    private void checkExistById(Long id, BaseRepository<? extends BaseEntity> repository) {
        if (ObjectUtils.isEmpty(id)) {
            throw new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND);
        }
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND);
        }
    }
}
