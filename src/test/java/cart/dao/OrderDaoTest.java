package cart.dao;

import cart.Fixture;
import cart.entity.OrderEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@JdbcTest
@Sql("classpath:schema.sql")
class OrderDaoTest {

    OrderDao orderDao;


    private OrderDaoTest(@Autowired final JdbcTemplate jdbcTemplate) {
        this.orderDao = new OrderDao(jdbcTemplate);
    }

    @DisplayName("주문 저장")
    @Test
    void insert() {
        // when & then
        assertThat(orderDao.insert(Fixture.order1)).isPositive();
    }

    @DisplayName("ID로 주문 조회")
    @Test
    void findById() {
        // given
        final Long id = orderDao.insert(Fixture.order1);

        // when
        final OrderEntity orderEntity = orderDao.findById(id);

        // then
        assertAll(
                () -> assertThat(orderEntity.getId()).isEqualTo(id),
                () -> assertThat(orderEntity.getMemberId()).isEqualTo(Fixture.order1.getMemberId()),
                () -> assertThat(orderEntity.getTotalPrice()).isEqualTo(Fixture.order1.getTotalPrice())
        );
    }
}