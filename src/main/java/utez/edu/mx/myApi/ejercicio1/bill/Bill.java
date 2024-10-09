package utez.edu.mx.myApi.ejercicio1.bill;

import jakarta.persistence.*;
import utez.edu.mx.myApi.ejercicio1.employee.Employee;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "NIP", nullable = false)
    private String NIP;

    @OneToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id", unique = true, nullable = false)
    private Employee employee;

    public Bill() {
    }

    public Bill(String accountNumber, String cardNumber, String NIP) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.NIP = NIP;
    }

    public Bill(long id, String accountNumber, String cardNumber, String NIP) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.NIP = NIP;
    }

    public Bill(long id, String accountNumber, String cardNumber, String NIP, Employee employee) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.NIP = NIP;
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
