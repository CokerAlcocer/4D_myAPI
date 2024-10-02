package utez.edu.mx.myApi.ejercicio1.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAll();
    Bill save(Bill bill);
}
