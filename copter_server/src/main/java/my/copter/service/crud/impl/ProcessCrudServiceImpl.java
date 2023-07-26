package my.copter.service.crud.impl;

import lombok.AllArgsConstructor;

import my.copter.exception.BadRequestException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.BaseEntity;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.product.CopterImage;
import my.copter.persistence.sql.repository.BaseRepository;
import my.copter.persistence.sql.repository.product.CopterImageRepository;
import my.copter.persistence.sql.repository.product.CopterRepository;
import my.copter.service.crud.ProcessCrudService;
import my.copter.util.ExceptionUtil;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static my.copter.util.ExceptionUtil.BAD_REQUEST_EXCEPTION;

@Service
@AllArgsConstructor
public class ProcessCrudServiceImpl implements ProcessCrudService {

    private final CopterRepository copterRepository;
    private final CopterImageRepository copterImageRepository;

    @Override
    @Transactional
    public void attachImage(Long copterId, Long copterImageId) {
        checkExistById(copterId, copterRepository);
        checkExistById(copterImageId, copterImageRepository);
        Copter copter = copterRepository.findById(copterId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        CopterImage image = copterImageRepository.findById(copterImageId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        if (!copter.getCopterImages().contains(image)) {
            copter.getCopterImages().add(image);
        } else {
            throw new BadRequestException(BAD_REQUEST_EXCEPTION);
        }
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
        if (copter.getCopterImages().contains(image)) {
            copter.getCopterImages().remove(image);
        } else {
            throw new BadRequestException(BAD_REQUEST_EXCEPTION);
        }
        copterRepository.save(copter);
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
