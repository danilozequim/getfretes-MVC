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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="TblMULTIPLICADOR")
public class FreightFactor {

    @Id
    @Column(name="ID_MULTIPLICADOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="VL_MULTIPLICADOR")
    private Float value;
    
    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    @ManyToOne
    @JoinColumn(name="ID_ESTADO_ORIGEM")
    private State stateOrigin = new State();

    @ManyToOne
    @JoinColumn(name="ID_ESTADO_DESTINO")
    private State stateDestination = new State();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="factor", cascade = CascadeType.ALL)
    private List<Request> requestList = new ArrayList<Request>();

    public void addStateOrigin(State state){
        state.addToListOrigin(this);
    }

    public void addStateDestination(State state){
        state.addToListDesteny(this);
    }

    public void addToList(Request request){
        request.setFactor(this);
        this.getRequestList().add(request);
    }

    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }
    
}
