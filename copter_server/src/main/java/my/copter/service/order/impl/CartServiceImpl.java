package my.copter.service.order.impl;

import lombok.AllArgsConstructor;

import my.copter.config.security.service.AuthenticationService;
import my.copter.exception.BadRequestException;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.user.Customer;
import my.copter.persistence.sql.repository.order.CartEntryRepository;
import my.copter.persistence.sql.repository.order.CartRepository;
import my.copter.persistence.sql.entity.order.CartEntry;
import my.copter.persistence.sql.entity.order.Cart;
import my.copter.service.crud.CopterCrudService;
import my.copter.service.order.CartService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static my.copter.util.ExceptionUtil.BAD_REQUEST_EXCEPTION;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartEntryRepository entryRepository;
    private final AuthenticationService authenticationService;
    private final CopterCrudService copterCrudService;

    @Override
    @Transactional
    public void addProduct(CartEntry cartEntry) {
        Cart cart = getCart();

        Copter copter = cartEntry.getCopter();
        int quantity = copter.getQuantity();
        if (cartEntry.getQuantity() > quantity) throw new BadRequestException(BAD_REQUEST_EXCEPTION);
        copter.setQuantity(quantity - cartEntry.getQuantity());
        copterCrudService.update(copter);

        Optional<CartEntry> optionalCartEntry = entryRepository.findCartEntryByCartAndCopter(cart, copter);
        if (optionalCartEntry.isEmpty()) {
            cartEntry.setCart(cart);
            entryRepository.save(cartEntry);
        } else {
            CartEntry oldEntry = optionalCartEntry.get();
            oldEntry.setQuantity(oldEntry.getQuantity() + cartEntry.getQuantity());
            entryRepository.save(oldEntry);
        }
    }

    @Override
    @Transactional
    public void removeProduct(CartEntry cartEntry) {
        Cart cart = getCart();

        Copter copter = cartEntry.getCopter();
        copter.setQuantity(copter.getQuantity() + cartEntry.getQuantity());

        Optional<CartEntry> optionalCartEntry = entryRepository.findCartEntryByCartAndCopter(cart, copter);
        if (optionalCartEntry.isEmpty()) {
            cartEntry.setCart(cart);
            entryRepository.save(cartEntry);
        } else {
            CartEntry oldEntry = optionalCartEntry.get();
            if (oldEntry.getQuantity() - cartEntry.getQuantity() < 0)
                throw new BadRequestException(BAD_REQUEST_EXCEPTION);
            else if (oldEntry.getQuantity() - cartEntry.getQuantity() == 0) {
                entryRepository.delete(oldEntry);
            } else {
                oldEntry.setQuantity(oldEntry.getQuantity() - cartEntry.getQuantity());
                entryRepository.save(oldEntry);
            }
        }
        copterCrudService.update(copter);
    }

    private Cart getCart() {
        Customer customer = authenticationService.findCustomer();
        Optional<Cart> optionalCart = cartRepository.findByOwnerAndActiveTrue(customer);
        Cart cart;
        if (optionalCart.isEmpty()) {
            cart = new Cart();
            cart.setOwner(customer);
            cart = cartRepository.save(cart);
        } else {
            cart = optionalCart.get();
        }
        return cart;
    }

    @Override
    @Transactional
    public Cart getActive() {
        return getCart();
    }

    @Override
    @Transactional
    public List<CartEntry> findAllByCart(Cart cart) {
        return entryRepository.findAllByCart(cart);
    }
}
