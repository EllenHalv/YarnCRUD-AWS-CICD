package org.example.controller;

import org.example.model.Yarn;
import org.example.service.YarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("localhost:8080")
@CrossOrigin(origins = "*")
public class YarnController {

    @Autowired
    private YarnService yarnService;

    @GetMapping("/yarns")
    public List<Yarn> getAllYarns() {
        return yarnService.getAllYarns();
    }

    @GetMapping("/yarn")
    public ResponseEntity<Yarn> getYarnById(@RequestParam(name = "id") Long id) {
        Yarn yarn = yarnService.getYarnById(id);
        return new ResponseEntity<>(yarn, HttpStatus.OK);
    }

    @PostMapping("/yarn")
    public ResponseEntity<Yarn> addYarn(@RequestBody Yarn yarn) {
        Yarn newYarn = yarnService.addYarn(yarn);
        return new ResponseEntity<>(newYarn, HttpStatus.CREATED);
    }

    @PatchMapping("/yarn")
    public ResponseEntity<Yarn> updateYarn(@RequestParam(name = "id") Long id, @RequestBody Yarn yarn) {
        Yarn updatedYarn = yarnService.updateYarn(id, yarn);
        return new ResponseEntity<>(updatedYarn, HttpStatus.OK);
    }

    @DeleteMapping("/yarn")
    public ResponseEntity<Yarn> deleteYarn(Long id) {
        Yarn deletedYarn = yarnService.deleteYarn(id);
        return new ResponseEntity<>(deletedYarn, HttpStatus.OK);
    }
}
