package utez.edu.mx.myApi.ejercicio1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.myApi.ejercicio1.bill.Bill;
import utez.edu.mx.myApi.ejercicio1.department.Department;

import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "e_mail", nullable = false)
    private String eMail;

    @ManyToOne
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private Bill bill;

    @ManyToMany
    @JoinTable(
            name = "employee_has_roles",
            joinColumns = @JoinColumn(name = "id_employee"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<Rol> roles;

    public Employee() {
    }

    public Employee(String fullName, String eMail) {
        this.fullName = fullName;
        this.eMail = eMail;
    }

    public Employee(String fullName, String eMail, Department department, Bill bill, List<Rol> roles) {
        this.fullName = fullName;
        this.eMail = eMail;
        this.department = department;
        this.bill = bill;
        this.roles = roles;
    }

    public Employee(long id, String fullName, String eMail, Department department, Bill bill, List<Rol> roles) {
        this.id = id;
        this.fullName = fullName;
        this.eMail = eMail;
        this.department = department;
        this.bill = bill;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}
