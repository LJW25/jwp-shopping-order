package cart.domain;

import java.util.Objects;

public class Point {
    private static final int MINIMUM_POINT = 0;

    private final int point;

    public Point(final int point) {

        this.point = point;
    }

    private void validatePoint(final int point) {
        if (point < MINIMUM_POINT) {
            throw new IllegalArgumentException("포인트는 음수일 수 없습니다");
        }
    }

    public Point add(final int other) {
        return new Point(point + other);
    }

    public Point subtract(final int other) {
        return new Point(point - other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point point1 = (Point) o;
        return point == point1.point;
    }

    public int getPoint() {
        return point;
    }
}