package com.codegym.casestudy_module4.controller.api;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.service.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineRestController {

    @Autowired
    private IMedicineService medicineService;

    @GetMapping("/all")
    public ResponseEntity<List<Medicine>> getMedicines() {
        List<Medicine> medicines = medicineService.getAll();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }
}
