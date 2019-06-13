package opg.dmj.server.service;

import opg.dmj.server.model.User;
import opg.dmj.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    User save(User user) {
        return userRepository.save(user);
    }

    List<User> list() {
        Iterable<User> iterable = userRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    User get(Long id) {
        return userRepository.findById(id).get();
    }

    void delete(Long id) {
        userRepository.deleteById(id);
    }
}
