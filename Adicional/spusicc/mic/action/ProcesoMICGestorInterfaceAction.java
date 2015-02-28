package biz.belcorp.ssicc.web.spusicc.mic.action;


/**
 * The Class ProcesoMICGestorInterfaceAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 23/12/2014
 */
/*
@ManagedBean
@SessionScoped
public class ProcesoMICGestorInterfaceAction extends BaseProcesoAbstractAction {
	
	*//**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
	
		// Hook method
		setViewAttributes(request, form);
		return mapping.findForward(getViewForward());
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMICGestorInterfaceForm form = new ProcesoMICGestorInterfaceForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        Usuario usuario = getUsuario(request);
        // Carga de los combos de Marca, Canal
        InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        request.getSession().setAttribute(Constants.SICC_MARCA_LIST,
                svc.getMarcas());        
        request.getSession().setAttribute(Constants.SICC_CANAL_LIST,
                svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
        //lista de tipo proceso 
        request.getSession().setAttribute(Constants.MIC_TIPO_PORCESO_LIST, getTipoproceso(request));
        
        ProcesoMICGestorInterfaceForm f = (ProcesoMICGestorInterfaceForm)form ;
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        f.setCodigoPais(getCodigoPais(request));
        f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
        					pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
        
        //cargo lista de paquete de entrada y salida
        setListaInterfaz(request, Constants.MIC_PAQUETE_INTERFAZ_SALIDA);
        setListaInterfaz(request, Constants.MIC_PAQUETE_INTERFAZ_ENTRADA);
        
        String codigoProcesoBatch = request.getParameter("codigoProcesoBatch");
		request.getSession().setAttribute("codigoProcesoBatch", codigoProcesoBatch);
		//seteamos uno x default
		f.setCodigoInterfaz(Constants.MIC_PAQUETE_INTERFAZ_ENTRADA);
		f.setCodigoSistema(f.getCodigoInterfaz().substring(0, f.getCodigoInterfaz().indexOf('-')).trim());
		
		f.setDescripcion(getDescripcionInterfaz(f));
        log.debug(getViewForward());
        //obteniendo numero d eejecuciones
		MantenimientoMICSegurosService serviceMicroseguros = (MantenimientoMICSegurosService) 
					getBean("spusicc.mantenimientoMICSegurosService");
		
		Map micPrams = serviceMicroseguros.getParametrosMicroSeguro();		
        f.setNumeroEjecucionEnvios((String)micPrams.get("numeroArchivos"));
        log.debug("numero de archivos xx " +f.getNumeroEjecucionEnvios());
    }
    
    *//**
     * Retorna la descripcion de la intefaz
     * @param f
     * @return
     *//*
    private String getDescripcionInterfaz(ProcesoMICGestorInterfaceForm f) {
    	InterfazService interfazService = (InterfazService)getBean("sisicc.interfazService");
  	  	String codigoPais =  f.getCodigoPais();
		String codigoSistema =  f.getCodigoSistema();
		String codigoInterfaz =  f.getCodigoInterfaz();
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = interfazService
				.getInterfaz(interfazEjecucionPK);
		return (interfazEjecucion!=null?interfazEjecucion.getDescripcion():"");
	}

    *//**
     * @param request
     * @param codigoInterfaz
     *//*
    private void setListaInterfaz(HttpServletRequest request, String codigoInterfaz) {
    	InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");    	
    	String codigoSistema=  codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
		InterfazPK interfazPK = new InterfazPK(getCodigoPais(request),
				codigoSistema, codigoInterfaz);

		List listPaquete = interfazService
								.getComponentesInterfazPaquete(interfazPK);
		log.info("Interfaces empaquetadas =" + listPaquete);
		if(Constants.MIC_PAQUETE_INTERFAZ_ENTRADA.equals(codigoInterfaz))
			request.getSession().setAttribute(
				   Constants.MIC_ALL_INTERFACES_ENTRADA, listPaquete);
		else{
		  request.getSession().setAttribute(
					Constants.MIC_ALL_INTERFACES_SALIDA_IPM, getListInterfaz(Constants.MIC_PAQUETE_INTERFAZ_SALIDA_IPM,listPaquete));
		  
		  request.getSession().setAttribute(
					Constants.MIC_ALL_INTERFACES_SALIDA_ASE, getListInterfaz(Constants.MIC_PAQUETE_INTERFAZ_SALIDA_ASE,listPaquete));
		  
		}
	}

	*//**
	 * Retorna codigo y descripcion de la interfza en un objeto List
	 * @param codigoInterfaz
	 * @param listPaquete
	 * @return
	 *//*
	private List getListInterfaz(String codigoInterfaz,
			List listPaquete) {
		List resultado = new ArrayList();
		Iterator it = listPaquete.iterator();
		while (it.hasNext()){
			Interfaz i=(Interfaz) it.next();
			if(codigoInterfaz.equals(i.getCodigo())){
				resultado.add(i);
				break;
			}
		}
		return resultado;
	}

	*//**
     * @param request
     * @return
     *//*
    private List getTipoproceso(HttpServletRequest request) {
    	List list = new ArrayList();
    	Base base = new Base();
    	
    	MessageResources messageResources = getResources(request);
		String mensajeTipoProcesoEntrada = messageResources.getMessage(
				"procesoMICGestorInterfaceForm.tipoProcesoEntrada");
		String mensajeTipoProcesoSalidaIPM = messageResources.getMessage(
				"procesoMICGestorInterfaceForm.tipoProcesoSalidaIPM");
		String mensajeTipoProcesoSalidaAseguradora = messageResources.getMessage(
		"procesoMICGestorInterfaceForm.tipoProcesoSalidaAseguradora");
		
    	base.setCodigo(Constants.NUMERO_CERO);//ENTRADA
    	base.setDescripcion(mensajeTipoProcesoEntrada);
    	list.add(base);
    	
    	base= new Base();
    	base.setCodigo(Constants.NUMERO_UNO);//SALIDA IPM
    	base.setDescripcion(mensajeTipoProcesoSalidaIPM);
    	list.add(base);
    	
    	base= new Base();
    	base.setCodigo(Constants.NUMERO_DOS);//SALIDA ASEGURADORA
    	base.setDescripcion(mensajeTipoProcesoSalidaAseguradora);
    	list.add(base);    	
		return list;
	}

	*//**
	 * Hook metodo. Utilizado para invocar proceso previos a la ejecucion de la interfaz.
	 * Asimismo dicho metodo actualiza la lista de Procesos Batch Activos de manera que coloca al 
	 * proceso en estado de Ejecucion  
	 *//*
	protected Map executeProcessBeforeInterfaz(ActionForm form, HttpServletRequest request, Map params) throws Exception {
        super.executeProcessBeforeInterfaz(form,request,params);
		return params;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
        ProcesoMICGestorInterfaceForm f = (ProcesoMICGestorInterfaceForm)form ;	
        
	   if(Constants.NUMERO_UNO.equals(f.getTipoProceso())){
		   f.setCodigoInterfaz(Constants.MIC_PAQUETE_INTERFAZ_SALIDA_IPM);
		   f.setDescripcion(getDescripcionInterfaz(f));
		   f.setNumeroEjecucionEnvios(null);
	   }
	   if(Constants.NUMERO_DOS.equals(f.getTipoProceso())){
		   f.setCodigoInterfaz(Constants.MIC_PAQUETE_INTERFAZ_SALIDA_ASE);
		   f.setDescripcion(getDescripcionInterfaz(f));
		   f.setNumeroEjecucionEnvios(null);
	     }	   
	   
	   if(Constants.NUMERO_CERO.equals(f.getTipoProceso())){
		   f.setCodigoInterfaz(Constants.MIC_PAQUETE_INTERFAZ_ENTRADA);
		   f.setDescripcion(getDescripcionInterfaz(f));
	       //se validara 
	       List listEntrada =  (List)request.getSession().getAttribute(
				   Constants.MIC_ALL_INTERFACES_ENTRADA);
	       if(listEntrada.size()==0){
	    	   MessageResources messageResources = getResources(request);
				String mensaje = messageResources.getMessage(
						"procesoMICGestorInterfaceForm.no.archivo.entrada");
				throw  new Exception(mensaje);
	       }
	   }  
	   return super.prepareParamsBeforeExecute(form, request);	
	}
}*/
