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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCodigo", query = "SELECT u FROM Usuario u WHERE u.codigo = :codigo")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByTipoUsuario", query = "SELECT u FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario")
    , @NamedQuery(name = "Usuario.findByPermiso", query = "SELECT u FROM Usuario u WHERE u.permiso = :permiso")
    , @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.activo = :activo")})
public class Usuario implements Serializable {


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<ReconciliacionInternaCliente> reconciliacionInternaClienteCollection;

    @OneToMany(mappedBy = "usuario")
    private Collection<FacturaTemporal> facturaTemporalCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<RegistroHoraExtra> registroHoraExtraCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<ConciliacionBancaria> conciliacionBancariaCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<MovimientoBanco> movimientoBancoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<AvanceDeCliente> avanceDeClienteCollection;

    @OneToMany(mappedBy = "usuario")
    private Collection<ReciboIngreso> reciboIngresoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<AvanceASuplidor> avanceASuplidorCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Proyecto> proyectoCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<FacturaSuplidor> facturaSuplidorCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<ComprobanteDePago> comprobanteDePagoCollection;

    @OneToMany(mappedBy = "usuario")
    private Collection<Conduce> conduceCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<CotizacionDeVenta> cotizacionDeVentaCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<Factura> facturaCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<Pedido> pedidoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<EmpresaOGrupo_1> empresaOGrupo1Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<RegistroDeImei> registroDeImeiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<ContratoDeServicio> contratoDeServicioCollection;

    @OneToMany(mappedBy = "usuario")
    private Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegocioCollection;

    @OneToMany(mappedBy = "usuario")
    private Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegociuoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    @Column(name = "permiso")
    private Integer permiso;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "activo")
    private Boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<EmpresaOGrupo> empresaOGrupoCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "rol", referencedColumnName = "codigo")
    @ManyToOne
    private Rol rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<UnidadDeNegocio> unidadDeNegocioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Rol> rolCollection;

    public Usuario() {
    }

    public Usuario(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getPermiso() {
        return permiso;
    }

    public void setPermiso(Integer permiso) {
        this.permiso = permiso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<EmpresaOGrupo> getEmpresaOGrupoCollection() {
        return empresaOGrupoCollection;
    }

    public void setEmpresaOGrupoCollection(Collection<EmpresaOGrupo> empresaOGrupoCollection) {
        this.empresaOGrupoCollection = empresaOGrupoCollection;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @XmlTransient
    public Collection<UnidadDeNegocio> getUnidadDeNegocioCollection() {
        return unidadDeNegocioCollection;
    }

    public void setUnidadDeNegocioCollection(Collection<UnidadDeNegocio> unidadDeNegocioCollection) {
        this.unidadDeNegocioCollection = unidadDeNegocioCollection;
    }

    @XmlTransient
    public Collection<Rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<Rol> rolCollection) {
        this.rolCollection = rolCollection;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Usuario[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<ReporteUnidadDeNegocio> getReporteUnidadDeNegociuoCollection() {
        return reporteUnidadDeNegociuoCollection;
    }

    public void setReporteUnidadDeNegociuoCollection(Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegociuoCollection) {
        this.reporteUnidadDeNegociuoCollection = reporteUnidadDeNegociuoCollection;
    }

    @XmlTransient
    public Collection<ReporteUnidadDeNegocio> getReporteUnidadDeNegocioCollection() {
        return reporteUnidadDeNegocioCollection;
    }

    public void setReporteUnidadDeNegocioCollection(Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegocioCollection) {
        this.reporteUnidadDeNegocioCollection = reporteUnidadDeNegocioCollection;
    }

    @XmlTransient
    public Collection<EmpresaOGrupo_1> getEmpresaOGrupo1Collection() {
        return empresaOGrupo1Collection;
    }

    public void setEmpresaOGrupo1Collection(Collection<EmpresaOGrupo_1> empresaOGrupo1Collection) {
        this.empresaOGrupo1Collection = empresaOGrupo1Collection;
    }

    @XmlTransient
    public Collection<RegistroDeImei> getRegistroDeImeiCollection() {
        return registroDeImeiCollection;
    }

    public void setRegistroDeImeiCollection(Collection<RegistroDeImei> registroDeImeiCollection) {
        this.registroDeImeiCollection = registroDeImeiCollection;
    }

    @XmlTransient
    public Collection<ContratoDeServicio> getContratoDeServicioCollection() {
        return contratoDeServicioCollection;
    }

    public void setContratoDeServicioCollection(Collection<ContratoDeServicio> contratoDeServicioCollection) {
        this.contratoDeServicioCollection = contratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<Conduce> getConduceCollection() {
        return conduceCollection;
    }

    public void setConduceCollection(Collection<Conduce> conduceCollection) {
        this.conduceCollection = conduceCollection;
    }

    @XmlTransient
    public Collection<CotizacionDeVenta> getCotizacionDeVentaCollection() {
        return cotizacionDeVentaCollection;
    }

    public void setCotizacionDeVentaCollection(Collection<CotizacionDeVenta> cotizacionDeVentaCollection) {
        this.cotizacionDeVentaCollection = cotizacionDeVentaCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    @XmlTransient
    public Collection<FacturaSuplidor> getFacturaSuplidorCollection() {
        return facturaSuplidorCollection;
    }

    public void setFacturaSuplidorCollection(Collection<FacturaSuplidor> facturaSuplidorCollection) {
        this.facturaSuplidorCollection = facturaSuplidorCollection;
    }

    @XmlTransient
    public Collection<ComprobanteDePago> getComprobanteDePagoCollection() {
        return comprobanteDePagoCollection;
    }

    public void setComprobanteDePagoCollection(Collection<ComprobanteDePago> comprobanteDePagoCollection) {
        this.comprobanteDePagoCollection = comprobanteDePagoCollection;
    }

    @XmlTransient
    public Collection<AvanceASuplidor> getAvanceASuplidorCollection() {
        return avanceASuplidorCollection;
    }

    public void setAvanceASuplidorCollection(Collection<AvanceASuplidor> avanceASuplidorCollection) {
        this.avanceASuplidorCollection = avanceASuplidorCollection;
    }

    @XmlTransient
    public Collection<ReciboIngreso> getReciboIngresoCollection() {
        return reciboIngresoCollection;
    }

    public void setReciboIngresoCollection(Collection<ReciboIngreso> reciboIngresoCollection) {
        this.reciboIngresoCollection = reciboIngresoCollection;
    }

    @XmlTransient
    public Collection<AvanceDeCliente> getAvanceDeClienteCollection() {
        return avanceDeClienteCollection;
    }

    public void setAvanceDeClienteCollection(Collection<AvanceDeCliente> avanceDeClienteCollection) {
        this.avanceDeClienteCollection = avanceDeClienteCollection;
    }

    @XmlTransient
    public Collection<MovimientoBanco> getMovimientoBancoCollection() {
        return movimientoBancoCollection;
    }

    public void setMovimientoBancoCollection(Collection<MovimientoBanco> movimientoBancoCollection) {
        this.movimientoBancoCollection = movimientoBancoCollection;
    }

    @XmlTransient
    public Collection<ConciliacionBancaria> getConciliacionBancariaCollection() {
        return conciliacionBancariaCollection;
    }

    public void setConciliacionBancariaCollection(Collection<ConciliacionBancaria> conciliacionBancariaCollection) {
        this.conciliacionBancariaCollection = conciliacionBancariaCollection;
    }

    @XmlTransient
    public Collection<RegistroHoraExtra> getRegistroHoraExtraCollection() {
        return registroHoraExtraCollection;
    }

    public void setRegistroHoraExtraCollection(Collection<RegistroHoraExtra> registroHoraExtraCollection) {
        this.registroHoraExtraCollection = registroHoraExtraCollection;
    }

    @XmlTransient
    public Collection<FacturaTemporal> getFacturaTemporalCollection() {
        return facturaTemporalCollection;
    }

    public void setFacturaTemporalCollection(Collection<FacturaTemporal> facturaTemporalCollection) {
        this.facturaTemporalCollection = facturaTemporalCollection;
    }

    @XmlTransient
    public Collection<ReconciliacionInternaCliente> getReconciliacionInternaClienteCollection() {
        return reconciliacionInternaClienteCollection;
    }

    public void setReconciliacionInternaClienteCollection(Collection<ReconciliacionInternaCliente> reconciliacionInternaClienteCollection) {
        this.reconciliacionInternaClienteCollection = reconciliacionInternaClienteCollection;
    }

  
}
