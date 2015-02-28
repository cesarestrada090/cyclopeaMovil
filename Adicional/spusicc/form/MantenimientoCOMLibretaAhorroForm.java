package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOMLibretaAhorroForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
    
    private String descripcionPais;
 
    private String codigoLider; 
    
    private String codigoPlanilla;
    
    private String centroCosto;
    
    private String documentoIdentidad;
    
    private String numeroLibretaAhorro;
    
    private String periodoIngreso;
    
    private String paterno;
    
    private String materno;
    
    private String nombre;
    
    private String estado;
    
    private String ruc;
    
    private String razonSocial;
    
    private String codCcci;
    
    private String tipoRegimen;
    
    /* INI SA PER-SiCC-2012-0357 */
    private String prefijoCuentaDetraccion;

	private String cuentaDetraccion;
	/* FIN SA PER-SiCC-2012-0357 */

    /**
	 * @return Returns the tipoRegimen.
	 */
	public String getTipoRegimen() {
		return tipoRegimen;
	}

	/**
	 * @param tipoRegimen The tipoRegimen to set.
	 * @struts.validator type = "required"
	 */
	public void setTipoRegimen(String tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}

	public String getCodCcci() {
		return codCcci;
	}

	public void setCodCcci(String codCcci) {
		this.codCcci = codCcci;
	}

	public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getCodigoLider() {
        return codigoLider;
    }

    public void setCodigoLider(String codigoLider) {
        this.codigoLider = codigoLider;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoPlanilla() {
        return codigoPlanilla;
    }

    public void setCodigoPlanilla(String codigoPlanilla) {
        this.codigoPlanilla = codigoPlanilla;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNumeroLibretaAhorro() {
        return numeroLibretaAhorro;
    }
    /**
    *
    * @struts.validator type = "required"
    */
    public void setNumeroLibretaAhorro(String numeroLibretaAhorro) {
        this.numeroLibretaAhorro = numeroLibretaAhorro;
    }

    public String getPeriodoIngreso() {
        return periodoIngreso;
    }

   
    public void setPeriodoIngreso(String periodoIngreso) {
        this.periodoIngreso = periodoIngreso;
    }

    public String getDescripcionPais() {
        return descripcionPais;
    }

    public void setDescripcionPais(String descripcionPais) {
        this.descripcionPais = descripcionPais;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

	/**
	 * @return Returns the razonSocial.
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial The razonSocial to set.
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return Returns the ruc.
	 */
	public String getRuc() {
		return ruc;
	}

	/**
	 * @param ruc The ruc to set.
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
    
    /**
	 * @return the prefijoCuentaDetraccion
	 */
	public String getPrefijoCuentaDetraccion() {
		return prefijoCuentaDetraccion;
	}

	/**
	 * @param prefijoCuentaDetraccion the prefijoCuentaDetraccion to set
	 */
	public void setPrefijoCuentaDetraccion(String prefijoCuentaDetraccion) {
		this.prefijoCuentaDetraccion = prefijoCuentaDetraccion;
	}

	/**
	 * @return the cuentaDetraccion
	 */
	public String getCuentaDetraccion() {
		return cuentaDetraccion;
	}

	/**
	 * @param cuentaDetraccion the cuentaDetraccion to set
	 */
	public void setCuentaDetraccion(String cuentaDetraccion) {
		this.cuentaDetraccion = cuentaDetraccion;
	}
}
