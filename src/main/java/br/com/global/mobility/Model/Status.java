package br.com.global.mobility.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.global.mobility.Enumerator.EN_Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TblSTATUS")
public class Status {

    @Id
    @Column(name="ID_STATUS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name="DS_STATUS")
    private EN_Status status;

    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="status", cascade = CascadeType.ALL)
    private List<RequestStatusRel> requestList = new ArrayList<RequestStatusRel>();

    public void addToList(RequestStatusRel requestStatusRel){
        requestStatusRel.setStatus(this);
        this.getRequestList().add(requestStatusRel);
    }

    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }
    
}
