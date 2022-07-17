package Model.Model;

import java.util.HashMap;

public enum UserLevel
{
    ADMIN(0),
    SALES_MANAGER(1),
    PRODUCT_MANAGER(2),
    REGULAR_USER(3),
    VIP_USER(4);

    public static final HashMap<Integer, UserLevel> map = new HashMap<>();

    static
    {
        for (UserLevel userLevel : UserLevel.values()) { map.put(userLevel.value, userLevel); }
    }

    private final int value;

    UserLevel(int value) { this.value = value; }

    public static UserLevel valueOf(int v) { return map.get(v); }

    public int getValue() { return value; }
}
