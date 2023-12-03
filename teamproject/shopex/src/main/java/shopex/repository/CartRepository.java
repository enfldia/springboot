package shopex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopex.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
