package br.com.global.mobility.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.global.mobility.Enumerator.EN_User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TblUSUARIO")
public class User implements UserDetails{

    @Id
    @Column(name="ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    @Column(name="FL_TIPO_USUARIO")
    private EN_User type;

    @Column(name="DS_EMAIL", unique = true)
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name="DS_SENHA")
    private String password;
    
    @Column(name="NR_TELEFONE")
    private String telephone;
    
    @Column(name="NM_USUARIO")
    private String name;

    @Column(name="NR_DOCUMENTO_LEGAL")
    private String legalDocument;
    
    @Column(name="DT_NASCIMENTO")
    private LocalDate bornDate;

    @Column(name="DT_INCLUSAO")
    private LocalDateTime inclusionDate;

    //=========================================================================
    //------------------------------ REALTIONS --------------------------------
    //=========================================================================

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<Address>();
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="userClient", cascade = CascadeType.ALL)
    private List<Request> requestClientList = new ArrayList<Request>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="userTransporter", cascade = CascadeType.ALL)
    private List<Request> requestTransporterList = new ArrayList<Request>();

    //=========================================================================
    //------------------------------ REALTIONS METHODS ------------------------
    //=========================================================================

    public void addToList(Address address){
        address.setUser(this);
        this.getAddressList().add(address);
    }

    public void addToList(Vehicle vehicle){
        vehicle.setUser(this);
        this.getVehicleList().add(vehicle);
    }

    public void addToList(Request request){
        if(this.getType() != EN_User.PRESTADOR){
            request.setUserClient(this);
            this.getRequestClientList().add(request);
        }else{
            request.setUserTransporter(this);
            this.getRequestTransporterList().add(request);
        }
    }

    //=========================================================================
    //------------------------------ INCLUSION DATE ---------------------------
    //=========================================================================


    public void setInclusionDate() {
        if (this.inclusionDate == null)
            this.inclusionDate = LocalDateTime.now();
    }

    //=========================================================================
    //------------------------------ USER DETAILS -----------------------------
    //=========================================================================

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();

    public User withRole(Role role){
        Assert.notNull(role, "role is required");
        this.roles.add(role);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
