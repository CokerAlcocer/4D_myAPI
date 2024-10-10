package utez.edu.mx.myApi.ejercicio1.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
@CrossOrigin(origins = {"*"})
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return billService.findAll();
    }

    // http://localhost:8080/api/bill/employee/id
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findByEmployeeId(@PathVariable("id") long id) {
        return billService.findByEmployeeId(id);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Bill bill) {
        return billService.save(bill);
    }
}
