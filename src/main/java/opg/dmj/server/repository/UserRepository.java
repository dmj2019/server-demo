package opg.dmj.server.repository;

import opg.dmj.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:12
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
