package biz.belcorp.ssicc.web.spusicc.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoCOMLibretaAhorroForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoCOMLibretaAhorroSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoCOMLibretaAhorroSearchAction extends BaseMantenimientoSearchAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4158544042249997087L;
	
	private String flag;
	private List siccTipoRegimenList;

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoCOMLibretaAhorroList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoCOMLibretaAhorroForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoCOMLibretaAhorroSearchForm searchForm = new MantenimientoCOMLibretaAhorroSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search - LibretaAhorroSearchAction' method");
		}

		MantenimientoCOMLibretaAhorroSearchForm searchForm = (MantenimientoCOMLibretaAhorroSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		searchForm.setCodigoPais(pais.getCodigo());

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);
		// La busqueda solo la realizaremos en los libretaAhorros activos
		criteria.put("estado", Constants.ESTADO_ACTIVO);

		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(searchForm.getCodigoLider())) {
			criteria.put("codigoLider", searchForm.getCodigoLider().trim() + "%");
		}

		if (log.isDebugEnabled()) {
			log.debug(criteria.toString());
		}

		ClienteUAGenerarService service = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		if (flag != null)
			if (flag.equals("s")) {
				log.debug("********* liberando lista ***********");
				criteria.remove("codigoLider");
				criteria.put("codigoLider", "");
				flag = null;
				searchForm.setCodigoLider("");
			}
		
		List lista = service.getLibretaAhorroByCriteria(criteria);
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save - LibretaAhorroAction' method");
		}

		// Extraemos atributos y parámetros a usar

		MantenimientoCOMLibretaAhorroForm libretaAhorroForm = (MantenimientoCOMLibretaAhorroForm) this.formMantenimiento;
		boolean isNew = libretaAhorroForm.isNewRecord();

		// Extreamos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		ClienteUAGenerarService service = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		// LibretaAhorro libretaAhorro = (LibretaAhorro)
		// convert(libretaAhorroForm);
		LibretaAhorro libretaAhorro = new LibretaAhorro();
		BeanUtils.copyProperties(libretaAhorro, libretaAhorroForm);
		Map criteria = BeanUtils.describe(libretaAhorroForm);
		criteria.put("updatedBy", usuario.getLogin());
		criteria.put("lastUpdated", new Date());

		try {
			if (!isNew) {
				/* INI SA PER-SiCC-2012-0357 */
				if(libretaAhorroForm.getCuentaDetraccion().equals("")) {
					criteria.put("cuentaDetraccion", "");
				} else {
					criteria.put("cuentaDetraccion", libretaAhorroForm.getPrefijoCuentaDetraccion() + 
								libretaAhorroForm.getCuentaDetraccion());
				}
				/* FIN SA PER-SiCC-2012-0357 */
				
				service.updateLibretaAhorro(criteria, usuario);
			}
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			throw new Exception(this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));

		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			throw new Exception(this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
			
		}

		libretaAhorroForm.setCodigoLider("");
		this.flag = "s";
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit - LibretaAhorroAction' method");
		}
		MantenimientoCOMLibretaAhorroForm libretaAhorroForm = new MantenimientoCOMLibretaAhorroForm();
		LibretaAhorro libretaSeleccionado = (LibretaAhorro) this.beanRegistroSeleccionado;

		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			String codigoLider = libretaSeleccionado.getCodigoLider();
			String codigoPais = libretaSeleccionado.getCodigoPais();
			Map criteria = BeanUtils.describe(libretaAhorroForm);
			criteria.put("codigoLider", codigoLider);
			criteria.put("codigoPais", codigoPais);

			// Si el id ha sido enviado, buscamos la informacion
			// en caso contrario, no hacemos nada, se esta insertando
			// un nuevo registro a la aplicación
			if (codigoLider != null && codigoPais != null) 
			{
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigoLider +" - "+codigoPais);
				}

				ClienteUAGenerarService service = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

				List tiposRegimen = service.getTiposRegimen();

				this.siccTipoRegimenList = tiposRegimen;

				LibretaAhorro libretaAhorro = service.getLibretaAhorro(criteria);
				BeanUtils.copyProperties(libretaAhorroForm, libretaAhorro);

				/* INI SA PER-SiCC-2012-0357 */
				if (StringUtils.isNotEmpty(libretaAhorro.getCuentaDetraccion())) {
					libretaAhorroForm.setPrefijoCuentaDetraccion(libretaAhorro.getCuentaDetraccion().substring(0, 3));
					libretaAhorroForm.setCuentaDetraccion(libretaAhorro.getCuentaDetraccion().substring(3));
				} else {
					libretaAhorroForm.setPrefijoCuentaDetraccion("000");
					libretaAhorroForm.setCuentaDetraccion("");
				}
				/* FIN SA PER-SiCC-2012-0357 */

				// updateFormBean(mapping, request, libretaAhorroForm);
				libretaAhorroForm.setNewRecord(false);
			}
		}
		// request.getSession().setAttribute("flag", "n");
		flag = "n";

		return libretaAhorroForm;
	}

	@Override
	protected void setViewAtributes() throws Exception
	{
		log.debug("method view  view - LibretaAhorroSearchAction");
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;

//		request.getSession().setAttribute("flag", "n");
		flag = "n"; 		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoCOMLibretaAhorroForm f = (MantenimientoCOMLibretaAhorroForm) this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		String mensaje = null;
		if(!isNew){
			mensaje = "libretaAhorro.updated";
		}	
		
		return mensaje;
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoCOMLibretaAhorroForm f = (MantenimientoCOMLibretaAhorroForm) this.formMantenimiento;

		// Validamos que no tenga valores en blanco
		if (StringUtils.isBlank(f.getNumeroLibretaAhorro())) {
			mensaje = this.getResourceMessage("libretaAhorroForm.msg.NumeroCuentaInvalido");
		} else {
			// Validamos que la longitud sea la correcta
			if (f.getNumeroLibretaAhorro().length() != 14) {
				mensaje = this.getResourceMessage("libretaAhorroForm.msg.NumeroCuentaInvalido");
			} else {
				// Obtenemos los 3 primeros caracteres para determinar el
				// algoritmo
				String prefijo = f.getNumeroLibretaAhorro().substring(0, 3);
				if (prefijo.equals("191") || prefijo.equals("192") || prefijo.equals("193") || prefijo.equals("194")) {
					// Extraemos las cantidades a sumar
					String cantidades = f.getNumeroLibretaAhorro().substring(3,	11);
					int longitud = cantidades.length();
					// Sumamos las cantidades en grupos de 2
					int suma = 0;
					for (int i = 0; i < 4; i++) {
						String cantidad = cantidades.substring(longitud - i * 2	- 2, longitud - i * 2);
						suma = suma + Integer.parseInt(cantidad, 10);
					}
					// Tomamos los dos ultimos digitos de la suma
					int residuo = suma % 100;
					// Comparamos este valor con los 2 ultimos digitos del
					// numero
					if (Integer.parseInt(f.getNumeroLibretaAhorro().substring(12), 10) != residuo) {
						mensaje = this.getResourceMessage("libretaAhorroForm.msg.NumeroCuentaInvalido");
					}
				} else {
					String cuerpo = f.getNumeroLibretaAhorro().substring(3, 11);
					String cantidades = prefijo + "80" + cuerpo;
					int longitud = cantidades.length();
					// Sumamos las cantidades en grupos de 2
					int suma = 0;
					for (int i = 0; i < 6; i++) {
						String cantidad = cantidades.substring(longitud - i * 2
								- 2, longitud - i * 2);
						suma = suma + Integer.parseInt(cantidad, 10);
					}
					// Sumamos el primer digito faltante
					suma = suma
							+ Integer.parseInt(cantidades.substring(0, 1), 10);
					// Tomamos los dos ultimos digitos de la suma
					int residuo = suma % 100;
					// Comparamos este valor con los 2 ultimos digitos del
					// numero
					if (Integer.parseInt(
							f.getNumeroLibretaAhorro().substring(12), 10) != residuo) {
						mensaje = this.getResourceMessage("libretaAhorroForm.msg.NumeroCuentaInvalido");
					}
				}
			}
		}

		if(f.getCuentaDetraccion().length() > 0)
		{
			// Validamos solo si ha ingresado algun valor
			if (StringUtils.isBlank(f.getCuentaDetraccion().trim())) {
				mensaje = this.getResourceMessage("libretaAhorroForm.msg.tamanioCuentaDetraccion");
			}else
			{
				// Validamos que la longitud sea la correcta
				if (f.getCuentaDetraccion().length() != 9) {
					mensaje = this.getResourceMessage("libretaAhorroForm.msg.tamanioCuentaDetraccion");
				}
			}
			
		}		

		return mensaje;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List getSiccTipoRegimenList() {
		return siccTipoRegimenList;
	}

	public void setSiccTipoRegimenList(List siccTipoRegimenList) {
		this.siccTipoRegimenList = siccTipoRegimenList;
	}

}
