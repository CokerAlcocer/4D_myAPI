package utez.edu.mx.myApi.pet;

import jakarta.persistence.*;
import utez.edu.mx.myApi.type.Type;

import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "weight", nullable = false)
    private double weight;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private Type type;

    public Pet() {
    }

    public Pet(String nickname, double height, double weight, Type type) {
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.type = type;
    }

    public Pet(long id, String nickname, double height, double weight, Type type) {
        this.id = id;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
