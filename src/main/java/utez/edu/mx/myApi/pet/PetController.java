package utez.edu.mx.myApi.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
//@CrossOrigin(origins = {"*"})
public class PetController {
    @Autowired
    private PetService petService;

    // http://ip:port/api/pet
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return petService.findAll();
    }

    // http://ip:port/api/pet/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return petService.findById(id);
    }
}
