package Model.Model;

import java.util.HashMap;

public enum UserLevel
{
    ADMIN(0),
    SALES_MANAGER(1),
    PRODUCT_MANAGER(2),
    REGULAR_USER(3),
    VIP_USER(4);

    private final int value;
    public static final HashMap<Integer, UserLevel> map = new HashMap<>();

    UserLevel(int value) { this.value = value; }

    static
    {
        for (UserLevel userLevel : UserLevel.values()) { map.put(userLevel.value, userLevel); }
    }

    public static UserLevel valueOf(int v) { return map.get(v); }

    public int getValue() { return value; }
}
