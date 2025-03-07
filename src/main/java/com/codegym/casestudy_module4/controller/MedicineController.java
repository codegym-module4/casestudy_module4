package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.service.impl.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public ResponseEntity<Medicine> getAllMedicines(@RequestBody Medicine medicine) {
        return null;
    }

    @PostMapping("/medicines")
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        return null;
    }

    @PutMapping("/medicines")
    public ResponseEntity<Medicine> updateMedicine(@RequestBody Medicine medicine) {
        return null;
    }

    @DeleteMapping("/medicines")
    public ResponseEntity<Medicine> deleteMedicine(@RequestBody Medicine medicine) {
        return null;
    }
}



