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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TblPRODUTO_PEDIDO")
public class RequestItem {

    @Id
    @Column(name="ID_PRODUTO_PEDIDO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="FL_VALIDO")
    private Integer valid = 1;

    @Column(name="VL_MENSURADO")
    private Float value;

    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ID_PEDIDO")
    private Request request = new Request();

    @ManyToOne
    @JoinColumn(name="ID_PRODUTO")
    private Item item = new Item();

    public void addRequest(Request request){
        item.addToList(this);
    }

    public void addItem(Item item){
        item.addToList(this);
    }
    
    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }
    
}
