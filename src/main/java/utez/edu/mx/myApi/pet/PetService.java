package utez.edu.mx.myApi.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        List<Pet> list = petRepository.findAll();
        Map<String, Object> body = new HashMap<>();

        body.put("message", list.isEmpty() ? "Aún no hay registros" : "Operación realizada exitosamente");
        body.put("status", 200);
        body.put("data", list);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(long id) {
        Pet found = petRepository.findById(id);
        Map<String, Object> body = new HashMap<>();

        // if ternario: expresion ? caso verdadero : caso falso;
        body.put("message", found == null ? "Recurso no encontrado" : "Operación realizada exitosamente");
        body.put("status", found == null ? 404 : 200);
        body.put("data", found);

        return new ResponseEntity<>(body, found == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> save(Pet pet) {
        Map<String, Object> body = new HashMap<>();
        Pet saved = null;
        try {
            saved = petRepository.save(pet);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        body.put("message", saved != null ? "Registro realizado exitosamente" : "Error de registro");
        body.put("status", saved != null ? 201 : 400);
        return new ResponseEntity<>(body, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> update(Pet pet, long id) {
        Map<String, Object> body = new HashMap<>();
        Pet updated = null;

        if(petRepository.findById(id) != null) {
            pet.setId(id);
            try {
                 updated = petRepository.save(pet);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            body.put("message", updated != null ? "Actualización realizada correctamente" : "Error de actualización");
            body.put("status", updated != null ? 201 : 400);
            return new ResponseEntity<>(body, updated != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } else {
            body.put("message", "El registro no existe");
            body.put("status", 404);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> delete(long id) {
        Map<String, Object> body = new HashMap<>();

        if(petRepository.findById(id) != null) {
            try {
                petRepository.deleteById(id);

                body.put("message","Eliminación realizada correctamente");
                body.put("status", 200);
                return new ResponseEntity<>(body, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());

                body.put("message", "Error en la eliminacíon");
                body.put("status", 400);
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
            }
        } else {
            body.put("message", "El registro no existe");
            body.put("status", 404);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }
}
