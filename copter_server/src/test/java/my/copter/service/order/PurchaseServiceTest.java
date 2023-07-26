package my.copter.service.order;

import my.copter.exception.EmptyFieldException;
import my.copter.exception.EntityNotFoundException;
import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.order.Purchase;
import my.copter.persistence.sql.repository.order.CartRepository;
import my.copter.persistence.sql.repository.order.PurchaseRepository;
import my.copter.service.order.impl.PurchaseServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static my.copter.util.EntityUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Test
    public void shouldCreatePurchaseSuccessfully() {
        Cart cart = getFilledCart();
        Purchase purchase = getFilledPurchase();
        purchase.setCart(cart);

        when(cartRepository.save(cart)).thenReturn(cart);

        purchaseService.create(purchase);

        assertFalse(cart.getActive());
        verify(cartRepository).save(cart);
        verify(purchaseRepository).save(purchase);
    }

    @Test
    public void shouldThrowExceptionForInvalidPurchase() {
        Purchase invalidPurchase = new Purchase();

        assertThrows(EmptyFieldException.class, () -> purchaseService.create(invalidPurchase));
        verifyNoMoreInteractions(cartRepository);
        verifyNoMoreInteractions(purchaseRepository);
    }

    @Test
    public void shouldUpdatePurchaseWhenValidIdAndActiveAreProvided() {
        Purchase purchase = getFilledPurchase();

        when(purchaseRepository.findById(PURCHASE_ID)).thenReturn(Optional.of(purchase));
        when(purchaseRepository.save(any(Purchase.class))).thenReturn(purchase);

        purchaseService.update(PURCHASE_ID, true);

        assertTrue(purchase.getActual());
        verify(purchaseRepository, times(1)).findById(PURCHASE_ID);
        verify(purchaseRepository, times(1)).save(purchase);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenInvalidIdIsProvided() {
        Long invalidPurchaseId = 100L;

        when(purchaseRepository.findById(invalidPurchaseId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> purchaseService.update(invalidPurchaseId, true));
        verify(purchaseRepository, times(1)).findById(invalidPurchaseId);
        verify(purchaseRepository, never()).save(any(Purchase.class));
    }
}

