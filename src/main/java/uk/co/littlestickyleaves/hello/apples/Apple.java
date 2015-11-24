package uk.co.littlestickyleaves.hello.apples;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * A class for calculations
 */
public class Apple {

    public static final Double PI = 3.14159;

    private double circumference;

    private double weight;

    private Type type;

    public enum Type {
        GOLDEN_DELICIOUS, JAZZ, BRAEBURN, BRAMLEY, CRAB
    }

    public Apple(double circumference, double weight, Type type) {
        this.circumference = circumference;
        this.weight = weight;
        this.type = type;
    }

    public double getCircumference() {
        return circumference;
    }

    public double getWeight() {
        return weight;
    }

    public double getRadius() {
        return getCircumference() / (Apple.PI * 2);
    }

    public double getVolume() {
        double radius = getRadius();
        return 4 * Apple.PI * radius * radius * radius / 3;
    }

    public Type getType() {
        return type;
    }

    public static List<Apple> appleSample() {
        List<Apple> apples = Lists.newArrayList();
        apples.add(new Apple(15.7, 2.3, Type.BRAEBURN));
        apples.add(new Apple(27.8, 5.1, Type.BRAMLEY));
        apples.add(new Apple(18.4, 4.1, Type.JAZZ));
        apples.add(new Apple(17.5, 3.3, Type.GOLDEN_DELICIOUS));
        apples.add(new Apple(14.9, 2.4, Type.CRAB));
        apples.add(new Apple(18.9, 4.2, Type.JAZZ));
        apples.add(new Apple(20.2, 4.7, Type.JAZZ));
        apples.add(new Apple(16.3, 2.7, Type.BRAEBURN));
        return apples;
    }
}
