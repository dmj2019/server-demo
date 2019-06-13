package opg.dmj.server.repository;

import opg.dmj.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: 杜梦嘉
 * @Date: 2019-06-13-14:12
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
