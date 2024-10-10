package utez.edu.mx.myApi.ejercicio1.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAll();
    Bill save(Bill bill);
    @Query(value = "SELECT * FROM bill WHERE id_employee = :id;", nativeQuery = true)
    Bill findByEmployeeId(@Param("id") long id);
}
