package my.copter.util;

import my.copter.data.datatable.DataTableRequest;
import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.order.CartEntry;
import my.copter.persistence.sql.entity.order.Purchase;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.product.CopterImage;
import my.copter.persistence.sql.entity.user.Admin;
import my.copter.persistence.sql.entity.user.Customer;
import my.copter.persistence.sql.entity.user.Manager;
import my.copter.persistence.sql.entity.user.User;
import my.copter.persistence.sql.type.BrandType;
import my.copter.persistence.sql.type.CategoryType;
import my.copter.persistence.sql.type.RoleType;

import java.util.Date;
import java.util.Random;

public class EntityUtil {

    public static final Long COPTER_ID = 1L;
    public static final Long INVALID_COPTER_ID = 100L;
    public static final Long COPTER_IMAGE_ID = 2L;
    public static final Long INVALID_COPTER_IMAGE_ID = 200L;
    public static final Long CART_ID = 3L;
    public static final Long USER_ID = 4L;
    public static final Long INVALID_USER_ID = 400L;
    public static final Long CART_ENTRY_ID = 5L;
    public static final Long PURCHASE_ID = 6L;


    private static final String DEFAULT_NAME = "Drone";
    private static final String DEFAULT_DESCRIPTION = "This is a default copter description.";
    private static final String DEFAULT_CAMERA_RES = "1080p";
    private static final Boolean DEFAULT_FPV_CAMERA = false;
    private static final String DEFAULT_BATTERY = "LiPo 3S";
    private static final String DEFAULT_FLY_TIME = "15 minutes";
    private static final Long DEFAULT_PRICE = 10000L;
    private static final Integer DEFAULT_QUANTITY = 10;
    private static final String DEFAULT_ORDER = "id";
    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer DEFAULT_SIZE = 10;
    private static final Boolean DEFAULT_MAIN_IMAGE = false;
    private static final Boolean DEFAULT_ACTIVE = true;
    private static final Date DEFAULT_CREATED = new Date();
    private static final String DEFAULT_USERNAME = "user";
    private static final String DEFAULT_FIRST_NAME = "John";
    private static final String DEFAULT_LAST_NAME = "Doe";
    private static final String DEFAULT_PASSWORD = "jon@123";
    private static final Boolean DEFAULT_ENABLED = true;
    private static final Integer DEFAULT_CART_ENTRY_QUANTITY = 2;
    private static final String PURCHASE_CONTACT = "john.doe@example.com";
    private static final String PURCHASE_ADDRESS = "123 Main Street";

    private static final Random random = new Random();

    public static CartEntry getFilledCartEntry() {
        CartEntry entry = new CartEntry();
        entry.setCart(getFilledCart());
        entry.setCopter(getFilledCopter());
        entry.setQuantity(DEFAULT_CART_ENTRY_QUANTITY);
        entry.setId(CART_ENTRY_ID);
        return entry;
    }

    public static Purchase getFilledPurchase() {
        Purchase purchase = new Purchase();
        purchase.setContact(PURCHASE_CONTACT);
        purchase.setAddress(PURCHASE_ADDRESS);
        purchase.setActual(true);
        purchase.setId(PURCHASE_ID);
        return purchase;
    }


    public static User getFilledUser(RoleType roleType) {
        User user = switch (roleType) {
            case MANAGER -> new Manager();
            case ADMIN -> new Admin();
            default -> new Customer();
        };

        user.setUsername(DEFAULT_USERNAME + random.nextInt(100));
        user.setFirstName(DEFAULT_FIRST_NAME);
        user.setLastName(DEFAULT_LAST_NAME);
        user.setPassword(DEFAULT_PASSWORD);
        user.setEnabled(DEFAULT_ENABLED);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setId(USER_ID);

        return user;
    }


    public static Cart getFilledCart() {
        Cart cart = new Cart();
        cart.setOwner((Customer) getFilledUser(RoleType.CUSTOMER));
        cart.setActive(DEFAULT_ACTIVE);
        cart.setCreated(DEFAULT_CREATED);
        cart.setId(CART_ID);
        return cart;
    }

    public static DataTableRequest getDefaultRequest() {
        DataTableRequest request = new DataTableRequest();
        request.setOrder(DEFAULT_ORDER);
        request.setPage(DEFAULT_PAGE);
        request.setSize(DEFAULT_SIZE);
        return request;
    }

    public static Copter getFilledCopter() {
        int brandsLen = BrandType.values().length;
        int categoryLen = CategoryType.values().length;

        Copter copter = new Copter();
        copter.setBrand(BrandType.values()[random.nextInt(brandsLen)]);
        copter.setName(DEFAULT_NAME + " " + random.nextInt(100));
        copter.setDescription(DEFAULT_DESCRIPTION + " " + random.nextInt(100));
        copter.setCameraResolution(DEFAULT_CAMERA_RES + " " + random.nextInt(100));
        copter.setFpvCamera(DEFAULT_FPV_CAMERA);
        copter.setCategoryType(CategoryType.values()[random.nextInt(categoryLen)]);
        copter.setBattery(DEFAULT_BATTERY + " " + random.nextInt(100));
        copter.setFlyTime(DEFAULT_FLY_TIME + " " + random.nextInt(100));
        copter.setPrice(DEFAULT_PRICE);
        copter.setQuantity(DEFAULT_QUANTITY);
        return copter;
    }

    public static CopterImage getFilledCopterImage() {
        int imageUrlSuffix = random.nextInt(100);

        CopterImage image = new CopterImage();
        image.setImageUrl("https://example.com/image" + imageUrlSuffix + ".png");
        image.setMainImage(DEFAULT_MAIN_IMAGE);
        return image;
    }
}
