package opg.dmj.server.service;

import opg.dmj.server.model.Occupation;
import opg.dmj.server.repository.OccupationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-21:44
 */
@Service
public class OccupationService {
    @Autowired
    private OccupationRepository occupationRepository;

    Occupation save(Occupation occupation) {
        return occupationRepository.save(occupation);
    }

    List<Occupation> list() {
        return occupationRepository.findAll();
    }
}
