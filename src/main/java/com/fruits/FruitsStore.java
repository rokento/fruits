package com.fruits;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 水果店计算各类水果价格
 */
public class FruitsStore {
    private static final BigDecimal APPLE_PRICE = new BigDecimal("8.0");

    private static final BigDecimal STRAWBERRY_PRICE = new BigDecimal("13.0");

    private static final BigDecimal MANGO_PRICE = new BigDecimal("20.0");

    /**
     * 计算购买苹果的价格
     * @param weight 重量
     * @return 价格
     */
    public BigDecimal buyApple(double weight) {
        if (weight <= 0) {
            return new BigDecimal("0");
        }
        return APPLE_PRICE.multiply(new BigDecimal(weight)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算购买草莓的价格
     * @param weight 重量
     * @return 价格
     */
    public BigDecimal buyStrawberry(double weight) {
        if (weight <= 0) {
            return new BigDecimal("0");
        }
        return STRAWBERRY_PRICE.multiply(new BigDecimal(weight)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算购买草莓的折扣价
     * @param weight 重量
     * @param discount 折扣
     * @return 打折后的价格
     */
    public BigDecimal buyStrawberry(double weight, double discount) {
        return buyStrawberry(weight).multiply(new BigDecimal(discount)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算购买芒果的价格
     * @param weight 重量
     * @return 价格
     */
    public BigDecimal buyMango(double weight) {
        if (weight <= 0) {
            return new BigDecimal("0");
        }
        return MANGO_PRICE.multiply(new BigDecimal(weight)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 优惠活动，价格满100元减10元
     * @param price 价格
     * @return 优惠后的价格
     */
    public BigDecimal specialOffer(BigDecimal price) {
        if (price.compareTo(new BigDecimal("100.00")) >= 0) {
            return price.subtract(new BigDecimal("10.00")).setScale(2, RoundingMode.HALF_UP);
        }
        return price;
    }

    public static void main(String[] args) {
        FruitsStore fruitsStore = new FruitsStore();
        // 1.现在顾客 A 在超市购买了若干斤苹果和草莓，需要计算一共多少钱？
        BigDecimal price1 = fruitsStore.buyApple(5.0).add(fruitsStore.buyStrawberry(7.0));
        BigDecimal price2 = fruitsStore.buyApple(Double.MAX_VALUE).add(fruitsStore.buyStrawberry(Double.MAX_VALUE));
        System.out.println(price1);
        System.out.println(price2);

        // 2.现在顾客 B 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
        BigDecimal price3 = fruitsStore.buyApple(5.0)
                .add(fruitsStore.buyStrawberry(7.0))
                .add(fruitsStore.buyMango(1.0));
        BigDecimal price4 = fruitsStore.buyApple(5.0)
                .add(fruitsStore.buyStrawberry(7.0))
                .add(fruitsStore.buyMango(Double.MIN_VALUE));
        System.out.println(price3);
        System.out.println(price4);

        // 3.超市做促销活动，草莓限时打 8 折。现在顾客 C 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
        BigDecimal price5 = fruitsStore.buyApple(5.0)
                .add(fruitsStore.buyStrawberry(7.0, 0.8))
                .add(fruitsStore.buyMango(1.0));
        System.out.println(price5);

        // 4.促销活动效果明显，超市继续加大促销力度，购物满 100 减 10 块。
        // 现在顾客 D 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
        BigDecimal price6 = fruitsStore.buyApple(5.0)
                .add(fruitsStore.buyStrawberry(7.0, 0.8))
                .add(fruitsStore.buyMango(1.0));
        System.out.println(fruitsStore.specialOffer(price6));
    }
}
