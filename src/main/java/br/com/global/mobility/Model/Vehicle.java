package br.com.global.mobility.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.com.global.mobility.Enumerator.EN_Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TblVEICULO")
public class Vehicle {

    @Id
    @Column(name="ID_VEICULO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name="TP_VEICULO")
    private EN_Vehicle type;

    @Column(name="NM_MARCA")
    private String brand;

    @Column(name="NM_MODELO")
    private String model;

    @Column(name="DS_PLACA")
    private String plate;

    @Column(name="NR_CAPACIDADE")
    private Integer capacity;

    @Column(name="DT_ANO")
    private Integer modelYear;

    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO")
    private User user;

    public void addUser(User user){
        user.addToList(this);
    }
    
    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }

}
