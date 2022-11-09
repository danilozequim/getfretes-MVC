package br.com.global.mobility.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TblSTATUS_PEDIDO")
public class RequestStatusRel {

    @Id
    @Column(name="ID_STATUS_PEDIDO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    @ManyToOne
    @JoinColumn(name="ID_PEDIDO")
    private Request request;

    @ManyToOne
    @JoinColumn(name="ID_STATUS")
    private Status status;

    public void addRequest(Request request){
        request.addToList(this);
    }

    public void addStatus(Status status){
        status.addToList(this);
    }
    
    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }
    
}
