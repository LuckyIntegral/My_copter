package my.copter.service.order;

import my.copter.config.security.service.AuthenticationService;
import my.copter.exception.BadRequestException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.order.CartEntry;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.user.Customer;
import my.copter.persistence.sql.repository.order.CartEntryRepository;
import my.copter.persistence.sql.repository.order.CartRepository;
import my.copter.persistence.sql.type.RoleType;
import my.copter.service.crud.CopterCrudService;
import my.copter.service.order.impl.CartServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static my.copter.util.EntityUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartServiceTest {
    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartEntryRepository cartEntryRepository;
    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private CopterCrudService copterCrudService;

    @Test
    public void shouldAddProductToCartWhenQuantityIsAvailable() {
        Copter copter = getFilledCopter();
        CartEntry cartEntry = getFilledCartEntry();
        cartEntry.setCopter(copter);
        Cart cart = getFilledCart();

        when(authenticationService.findCustomer()).thenReturn(cart.getOwner());
        when(cartRepository.findByOwnerAndActiveTrue(any())).thenReturn(Optional.of(cart));
        doNothing().when(copterCrudService).update(copter);
        when(cartEntryRepository.findCartEntryByCartAndCopter(cart, copter)).thenReturn(Optional.empty());

        cartService.addProduct(cartEntry);

        assertEquals(8, copter.getQuantity());
        verify(cartEntryRepository, times(1)).save(any(CartEntry.class));
    }

    @Test
    public void shouldUpdateQuantityOfExistingProductInCart() {
        Copter copter = getFilledCopter();
        CartEntry cartEntry = getFilledCartEntry();
        cartEntry.setCopter(copter);
        Cart cart = getFilledCart();

        CartEntry existingEntry = getFilledCartEntry();
        existingEntry.setCart(cart);
        existingEntry.setCopter(copter);
        existingEntry.setQuantity(3);

        when(authenticationService.findCustomer()).thenReturn(new Customer());
        when(cartRepository.findByOwnerAndActiveTrue(any())).thenReturn(Optional.of(cart));
        doNothing().when(copterCrudService).update(any());
        when(cartEntryRepository.findCartEntryByCartAndCopter(cart, copter)).thenReturn(Optional.of(existingEntry));

        cartService.addProduct(cartEntry);

        assertEquals(8, copter.getQuantity());
        assertEquals(5, existingEntry.getQuantity());
        verify(cartEntryRepository, times(1)).save(existingEntry);
    }

    @Test
    public void shouldThrowBadRequestExceptionWhenQuantityIsNotAvailable() {
        Copter copter = getFilledCopter();

        CartEntry cartEntry = new CartEntry();
        cartEntry.setCopter(copter);
        cartEntry.setQuantity(20);

        Cart cart = getFilledCart();

        when(authenticationService.findCustomer()).thenReturn(new Customer());
        when(cartRepository.findByOwnerAndActiveTrue(any())).thenReturn(Optional.of(cart));

        assertThrows(BadRequestException.class, () -> cartService.addProduct(cartEntry));
    }

    @Test
    public void shouldRemoveProductFromCartWithZeroQuantity() {
        Customer customer = (Customer) getFilledUser(RoleType.CUSTOMER);
        Cart cart = getFilledCart();
        cart.setOwner(customer);
        Copter copter = getFilledCopter();
        CartEntry cartEntry = getFilledCartEntry();
        cartEntry.setCart(cart);
        cartEntry.setCopter(copter);
        cartEntry.setQuantity(copter.getQuantity()); // Setting quantity equal to copter quantity to test deletion

        when(authenticationService.findCustomer()).thenReturn(customer);
        when(cartRepository.findByOwnerAndActiveTrue(customer)).thenReturn(Optional.of(cart));
        when(cartEntryRepository.findCartEntryByCartAndCopter(cart, copter)).thenReturn(Optional.of(cartEntry));

        cartService.removeProduct(cartEntry);

        assertEquals(20, copter.getQuantity());
        verify(copterCrudService, times(1)).update(copter);
        verify(cartEntryRepository, times(1)).delete(cartEntry);
        verify(cartEntryRepository, never()).save(any());
    }

    @Test
    public void shouldAddProductToCartIfNotExists() {
        Customer customer = (Customer) getFilledUser(RoleType.CUSTOMER);
        Cart cart = getFilledCart();
        cart.setOwner(customer);
        Copter copter = getFilledCopter();
        CartEntry cartEntry = getFilledCartEntry(); // Default quantity is 2
        cartEntry.setCart(cart);
        cartEntry.setCopter(copter);

        when(authenticationService.findCustomer()).thenReturn(customer);
        when(cartRepository.findByOwnerAndActiveTrue(customer)).thenReturn(Optional.of(cart));
        when(cartEntryRepository.findCartEntryByCartAndCopter(cart, copter)).thenReturn(Optional.empty());

        cartService.removeProduct(cartEntry);

        assertEquals(12, copter.getQuantity());
        verify(copterCrudService, times(1)).update(copter);
        verify(cartEntryRepository, never()).delete(any());
        verify(cartEntryRepository, times(1)).save(cartEntry);
    }

    @Test
    public void shouldThrowBadRequestExceptionIfQuantityToRemoveExceedsCartQuantity() {
        Customer customer = (Customer) getFilledUser(RoleType.CUSTOMER);
        Cart cart = getFilledCart();
        cart.setOwner(customer);
        Copter copter = getFilledCopter(); // Default quantity is 10
        CartEntry cartEntry = getFilledCartEntry();
        cartEntry.setCart(cart);
        cartEntry.setCopter(copter);
        cartEntry.setQuantity(20);

        when(authenticationService.findCustomer()).thenReturn(customer);
        when(cartRepository.findByOwnerAndActiveTrue(customer)).thenReturn(Optional.of(cart));
        when(cartEntryRepository.findCartEntryByCartAndCopter(any(), any())).thenReturn(Optional.of(getFilledCartEntry()));

        assertThrows(BadRequestException.class, () -> cartService.removeProduct(cartEntry));

        assertEquals(30, copter.getQuantity());
        verify(copterCrudService, never()).update(copter);
        verify(cartEntryRepository, never()).delete(any());
        verify(cartEntryRepository, never()).save(any());
    }

    @Test
    public void shouldFindCartByIdWhenCartExists() {
        Cart expectedCart = getFilledCart();
        when(cartRepository.findById(CART_ID)).thenReturn(Optional.of(expectedCart));

        Cart actualCart = cartService.findCartById(CART_ID);

        assertEquals(expectedCart, actualCart);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCartDoesNotExist() {
        when(cartRepository.findById(CART_ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> cartService.findCartById(CART_ID));
    }

    @Test
    public void shouldGetActiveCart() {
        Customer customer = (Customer) getFilledUser(RoleType.CUSTOMER);
        Cart expectedCart = getFilledCart();
        expectedCart.setOwner(customer);

        when(authenticationService.findCustomer()).thenReturn(customer);
        when(cartRepository.findByOwnerAndActiveTrue(customer)).thenReturn(Optional.of(expectedCart));

        Cart actualCart = cartService.getActive();

        assertEquals(expectedCart, actualCart);
    }

    @Test
    public void shouldReturnNullWhenActiveCartNotAvailable() {
        Customer customer = (Customer) getFilledUser(RoleType.CUSTOMER);
        when(authenticationService.findCustomer()).thenReturn(customer);
        when(cartRepository.findByOwnerAndActiveTrue(customer)).thenReturn(Optional.empty());

        Cart actualCart = cartService.getActive();

        assertNull(actualCart);
    }
}

