package org.example.service;

import org.example.dao.YarnDAO;
import org.example.model.Yarn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YarnService {

    @Autowired
    private YarnDAO yarnDAO;

    public Yarn addYarn(Yarn yarn) {
        return yarnDAO.save(yarn);
    }

    public Yarn getYarnById(Long id) {
        return yarnDAO.findById(id).orElseThrow(() ->
                new RuntimeException("Yarn not found with id: " + id));
    }

    public List<Yarn> getAllYarns() {
        return yarnDAO.findAll();
    }

    public Yarn updateYarn(Long id, Yarn yarn) {
        Yarn existingYarn = yarnDAO.findById(id).orElseThrow(() ->
                new RuntimeException("Yarn not found with id: " + id)
        );
        existingYarn.setType(yarn.getType());
        existingYarn.setColor(yarn.getColor());
        existingYarn.setWeight(yarn.getWeight());

        return yarnDAO.save(existingYarn);
    }

    public Yarn deleteYarn(Long id) {
        Yarn existingYarn = yarnDAO.findById(id).orElseThrow(() ->
                new RuntimeException("Yarn not found with id: " + id)
        );
        yarnDAO.delete(existingYarn);
        return existingYarn;
    }
}
