/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "suplidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suplidor.findAll", query = "SELECT s FROM Suplidor s")
    , @NamedQuery(name = "Suplidor.findByCodigo", query = "SELECT s FROM Suplidor s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "Suplidor.findByRnc", query = "SELECT s FROM Suplidor s WHERE s.rnc = :rnc")
    , @NamedQuery(name = "Suplidor.findByTelefono", query = "SELECT s FROM Suplidor s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Suplidor.findByEmail", query = "SELECT s FROM Suplidor s WHERE s.email = :email")
    , @NamedQuery(name = "Suplidor.findByContacto", query = "SELECT s FROM Suplidor s WHERE s.contacto = :contacto")
    , @NamedQuery(name = "Suplidor.findByCelular", query = "SELECT s FROM Suplidor s WHERE s.celular = :celular")
    , @NamedQuery(name = "Suplidor.findByFax", query = "SELECT s FROM Suplidor s WHERE s.fax = :fax")
    , @NamedQuery(name = "Suplidor.findByLimiteCredito", query = "SELECT s FROM Suplidor s WHERE s.limiteCredito = :limiteCredito")
    , @NamedQuery(name = "Suplidor.findByCuentaContable", query = "SELECT s FROM Suplidor s WHERE s.cuentaContable = :cuentaContable")
    , @NamedQuery(name = "Suplidor.findByWeb", query = "SELECT s FROM Suplidor s WHERE s.web = :web")})
public class Suplidor implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "suplidor")
    private Collection<AvanceASuplidor> avanceASuplidorCollection;

    @Column(name = "telefono_de_contacto")
    private String telefonoDeContacto;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "rnc")
    private String rnc;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "contacto")
    private String contacto;
    @Column(name = "celular")
    private String celular;
    @Column(name = "fax")
    private String fax;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "limite_credito")
    private Double limiteCredito;
    @Column(name = "cuenta_contable")
    private String cuentaContable;
    @Column(name = "web")
    private String web;
    @OneToMany(mappedBy = "suplidor")
    private Collection<NotaCreditoSuplidor> notaCreditoSuplidorCollection;
    @OneToMany(mappedBy = "suplidor")
    private Collection<FacturaSuplidor> facturaSuplidorCollection;
    @OneToMany(mappedBy = "suplidor")
    private Collection<EntradaInventario> entradaInventarioCollection;
    @OneToMany(mappedBy = "suplidor")
    private Collection<Cxp> cxpCollection;
    @OneToMany(mappedBy = "suplidor")
    private Collection<RecepcionArticulo> recepcionArticuloCollection;
    @OneToMany(mappedBy = "suplidor")
    private Collection<ComprobanteDePago> comprobanteDePagoCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "plazo", referencedColumnName = "codigo")
    @ManyToOne
    private Plazo plazo;
    @JoinColumn(name = "tipo_suplidor", referencedColumnName = "codigo")
    @ManyToOne
    private TipoSuplidor tipoSuplidor;

    public Suplidor() {
    }

    public Suplidor(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @XmlTransient
    public Collection<NotaCreditoSuplidor> getNotaCreditoSuplidorCollection() {
        return notaCreditoSuplidorCollection;
    }

    public void setNotaCreditoSuplidorCollection(Collection<NotaCreditoSuplidor> notaCreditoSuplidorCollection) {
        this.notaCreditoSuplidorCollection = notaCreditoSuplidorCollection;
    }

    @XmlTransient
    public Collection<FacturaSuplidor> getFacturaSuplidorCollection() {
        return facturaSuplidorCollection;
    }

    public void setFacturaSuplidorCollection(Collection<FacturaSuplidor> facturaSuplidorCollection) {
        this.facturaSuplidorCollection = facturaSuplidorCollection;
    }

    @XmlTransient
    public Collection<EntradaInventario> getEntradaInventarioCollection() {
        return entradaInventarioCollection;
    }

    public void setEntradaInventarioCollection(Collection<EntradaInventario> entradaInventarioCollection) {
        this.entradaInventarioCollection = entradaInventarioCollection;
    }

    @XmlTransient
    public Collection<Cxp> getCxpCollection() {
        return cxpCollection;
    }

    public void setCxpCollection(Collection<Cxp> cxpCollection) {
        this.cxpCollection = cxpCollection;
    }

    @XmlTransient
    public Collection<RecepcionArticulo> getRecepcionArticuloCollection() {
        return recepcionArticuloCollection;
    }

    public void setRecepcionArticuloCollection(Collection<RecepcionArticulo> recepcionArticuloCollection) {
        this.recepcionArticuloCollection = recepcionArticuloCollection;
    }

    @XmlTransient
    public Collection<ComprobanteDePago> getComprobanteDePagoCollection() {
        return comprobanteDePagoCollection;
    }

    public void setComprobanteDePagoCollection(Collection<ComprobanteDePago> comprobanteDePagoCollection) {
        this.comprobanteDePagoCollection = comprobanteDePagoCollection;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Plazo getPlazo() {
        return plazo;
    }

    public void setPlazo(Plazo plazo) {
        this.plazo = plazo;
    }

    public TipoSuplidor getTipoSuplidor() {
        return tipoSuplidor;
    }

    public void setTipoSuplidor(TipoSuplidor tipoSuplidor) {
        this.tipoSuplidor = tipoSuplidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suplidor)) {
            return false;
        }
        Suplidor other = (Suplidor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Suplidor[ codigo=" + codigo + " ]";
    }

    public String getTelefonoDeContacto() {
        return telefonoDeContacto;
    }

    public void setTelefonoDeContacto(String telefonoDeContacto) {
        this.telefonoDeContacto = telefonoDeContacto;
    }

    @XmlTransient
    public Collection<AvanceASuplidor> getAvanceASuplidorCollection() {
        return avanceASuplidorCollection;
    }

    public void setAvanceASuplidorCollection(Collection<AvanceASuplidor> avanceASuplidorCollection) {
        this.avanceASuplidorCollection = avanceASuplidorCollection;
    }
    
}
