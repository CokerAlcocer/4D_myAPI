package utez.edu.mx.myApi.pet;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public ResponseEntity<?> findAll() {
        List<Pet> list = petRepository.findAll();
        Map<String, Object> body = new HashMap<>();

        body.put("message", list.isEmpty() ? "Aún no hay registros" : "Operación realizada exitosamente");
        body.put("status", 200);
        body.put("data", list);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<?> findById(long id) {
        Pet found = petRepository.findById(id);
        Map<String, Object> body = new HashMap<>();

        // if ternario: expresion ? caso verdadero : caso falso;
        body.put("message", found == null ? "Recurso no encontrado" : "Operación realizada exitosamente");
        body.put("status", found == null ? 404 : 200);
        body.put("data", found);

        return new ResponseEntity<>(body, found == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
