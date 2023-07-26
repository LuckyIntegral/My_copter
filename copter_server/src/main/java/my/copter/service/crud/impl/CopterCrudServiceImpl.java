package my.copter.service.crud.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.exception.BadRequestException;
import my.copter.exception.EmptyFieldException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.product.Copter;
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
        checkExistById(entity.getId());
        copterRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        checkExistById(id);
        copterRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Copter findById(Long id) {
        checkExistById(id);
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
        if (ObjectUtils.isEmpty(entity.getBrand())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (!EnumUtils.isValidEnum(BrandType.class, entity.getBrand().toString())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isBlank(entity.getName())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isBlank(entity.getDescription())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isBlank(entity.getCameraResolution())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (!EnumUtils.isValidEnum(CategoryType.class, entity.getCategoryType().toString())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isBlank(entity.getBattery())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (StringUtils.isBlank(entity.getFlyTime())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(entity.getPrice())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (entity.getPrice() < 0) {
            throw new BadRequestException(ExceptionUtil.BAD_REQUEST_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(entity.getQuantity())) {
            throw new EmptyFieldException(ExceptionUtil.EMPTY_FIELD_EXCEPTION);
        }
        if (entity.getQuantity() < 0) {
            throw new BadRequestException(ExceptionUtil.BAD_REQUEST_EXCEPTION);
        }
    }

    private void checkExistById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND);
        }
        if (!copterRepository.existsById(id)) {
            throw new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND);
        }
    }
}
