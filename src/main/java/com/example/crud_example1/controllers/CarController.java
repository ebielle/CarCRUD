package com.example.crud_example1.controllers;

import com.example.crud_example1.entities.Car;
import com.example.crud_example1.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/create")
    public Car createCar(@RequestBody Car car) {
        Car carSaved = carRepository.saveAndFlush(car);
        return carSaved;
    }

    @GetMapping("/list")
    public List<Car> listCars() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        Car carById = null;
        if (!carRepository.existsById(id)) {
            return carById;
        } else {
            carById = carRepository.getById(id);
            return carById;
        }
    }

    @PutMapping("/{id}")
    public Car updateCarById(@PathVariable Long id, @RequestParam String type) {
        Car carById = null;
        if (carRepository.existsById(id)) {
            carById = carRepository.getById(id);
            carById.setType(type);
            return carById;
        } else {
            return carById;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            carRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/delete")
    public void deleteCars(){
        carRepository.deleteAll();
    }
}
