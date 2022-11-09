package br.com.global.mobility.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TblESTADO")
public class State {

    @Id
    @Column(name="ID_ESTADO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NM_ESTADO")
    private String name;

    @Column(name="DS_SIGLA")
    private String initials;
    
    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="stateOrigin", cascade = CascadeType.ALL)
    private List<FreightFactor> factorOriginList = new ArrayList<FreightFactor>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="stateDestination", cascade = CascadeType.ALL)
    private List<FreightFactor> factorDestinationList = new ArrayList<FreightFactor>();
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="state", cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<Address>();

    public void addToListOrigin(FreightFactor freightFactor){
        freightFactor.setStateOrigin(this);
        this.getFactorOriginList().add(freightFactor);
    }

    public void addToListDesteny(FreightFactor freightFactor){
        freightFactor.setStateDestination(this);
        this.getFactorDestinationList().add(freightFactor);
    }

    public void addToList(Address address){
        address.setState(this);
        this.getAddressList().add(address);
    }

    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }
   
}
