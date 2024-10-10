package utez.edu.mx.myApi.ejercicio1.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        Map<String, Object> body = new HashMap<>();

        body.put("message", "Operación exitosa");
        body.put("status", 200);
        body.put("data", billRepository.findAll());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> findByEmployeeId(long id) {
        Map<String, Object> body = new HashMap<>();
        Bill found = billRepository.findByEmployeeId(id);

        body.put("message", found == null ? "El empleado no tiene una nómina" : "Operación exitosa");
        body.put("status", "OK");
        body.put("code", 200);
        body.put("data", found);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> save(Bill b) {
        Map<String, Object> body = new HashMap<>();
        Bill saved = null;

        try {
            saved = billRepository.save(b);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        body.put("message", saved != null ? "Registro exitoso" : "Operación fallida");
        body.put("status", saved != null ? 201 : 400);
        return new ResponseEntity<>(body, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
