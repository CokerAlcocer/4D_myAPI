package utez.edu.mx.myApi.ejercicio1.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("")
    private ResponseEntity<?> findAll() {
        return billService.findAll();
    }

    @PostMapping("")
    private ResponseEntity<?> save(@RequestBody Bill bill) {
        return billService.save(bill);
    }
}
