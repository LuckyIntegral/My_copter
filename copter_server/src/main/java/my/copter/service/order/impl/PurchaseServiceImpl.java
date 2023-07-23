package my.copter.service.order.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.exception.EmptyFieldException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.order.Purchase;
import my.copter.persistence.sql.repository.order.CartRepository;
import my.copter.persistence.sql.repository.order.PurchaseRepository;
import my.copter.service.order.PurchaseService;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static my.copter.util.ExceptionUtil.EMPTY_FIELD_EXCEPTION;
import static my.copter.util.ExceptionUtil.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository repository;
    private final CartRepository cartRepository;

    @Override
    @Transactional
    public void create(Purchase purchase) {
        validateFields(purchase);

        Cart cart = purchase.getCart();
        cart.setActive(Boolean.FALSE);
        cartRepository.save(cart);

        repository.save(purchase);
    }

    @Override
    @Transactional
    public void update(Long id, Boolean active) {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        purchase.setActual(active);
        repository.save(purchase);
    }

    @Override
    @Transactional
    public Page<Purchase> findAll(DataTableRequest request) {
        return repository.findAll(PageRequest.of(request.getPage(), request.getSize(), Sort.by("actual").descending()));
    }

    private void validateFields(Purchase purchase) {
        if (ObjectUtils.isEmpty(purchase)) {
            throw new EmptyFieldException(EMPTY_FIELD_EXCEPTION);
        } else if (ObjectUtils.isEmpty(purchase.getCart())) {
            throw new EmptyFieldException(EMPTY_FIELD_EXCEPTION);
        } else if (StringUtils.isEmpty(purchase.getContact())) {
            throw new EmptyFieldException(EMPTY_FIELD_EXCEPTION);
        }
    }
}
