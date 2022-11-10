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
@Table(name="TblENDERECO")
public class Address {

    @Id
    @Column(name="ID_ENDERECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NR_CEP")
    private String zipCode;
    
    @Column(name="NM_RUA")
    private String street;

    @Column(name="NR_RUA")
    private Integer streetNumber;

    @Column(name="DS_COMPLEMENTO")
    private String complement;

    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    @ManyToOne
    @JoinColumn(name="ID_ESTADO")
    private State state = new State();

    @ManyToOne
    @JoinColumn(name="ID_USUARIO")
    private User user = new User();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="address", cascade = CascadeType.ALL)
    private List<Request> requestList = new ArrayList<Request>();
    
    public void addState(State state){
        state.addToList(this);
    }

    public void addUser(User user){
        user.addToList(this);
    }

    public void addToList(Request request){
        request.setAddress(this);
        this.getRequestList().add(request);
    }
    
    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }
    
}
