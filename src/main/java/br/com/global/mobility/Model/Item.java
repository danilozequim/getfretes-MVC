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
@Table(name="TblPRODUTO")
public class Item {

    @Id
    @Column(name="ID_PRODUTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="DS_CATEGORIA")
    private String categoryGroup;

    @Column(name="DS_SUBCATEGORIA")
    private String categorySubGroup;

    @Column(name="NM_PRODUTO")
    private String name;

    @Column(name="NM_UNIDADE_DE_MEDIDA")
    private String measurement;

    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="item", cascade = CascadeType.ALL)
    private List<RequestItem> requestItemList = new ArrayList<RequestItem>();

    public void addToList(RequestItem requestItem){
        requestItem.setItem(this);
        this.getRequestItemList().add(requestItem);
    }
    
    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }
    
}
