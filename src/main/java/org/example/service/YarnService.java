package org.example.service;

import org.example.repository.YarnRepository;
import org.example.model.Yarn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YarnService {

    @Autowired
    private YarnRepository yarnRepository;

    public Yarn addYarn(Yarn yarn) {
        return yarnRepository.save(yarn);
    }

    public Yarn getYarnById(Long id) {
        return yarnRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Yarn not found with id: " + id));
    }

    public List<Yarn> getAllYarns() {
        return yarnRepository.findAll();
    }

    public Yarn updateYarn(Long id, Yarn yarn) {
        Yarn existingYarn = yarnRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Yarn not found with id: " + id)
        );
        existingYarn.setType(yarn.getType());
        existingYarn.setColor(yarn.getColor());
        existingYarn.setWeight(yarn.getWeight());

        return yarnRepository.save(existingYarn);
    }

    public Yarn deleteYarn(Long id) {
        Yarn existingYarn = yarnRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Yarn not found with id: " + id)
        );
        yarnRepository.delete(existingYarn);
        return existingYarn;
    }
}
