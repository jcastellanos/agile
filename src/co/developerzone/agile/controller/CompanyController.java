package co.developerzone.agile.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import co.developerzone.agile.domain.Company;
import co.developerzone.agile.domain.User;
import co.developerzone.agile.util.MessageboxUtil;
import co.developerzone.service.CompanyService;
import co.developerzone.service.UserService;

public class CompanyController extends GenericForwardComposer {

	private static final long serialVersionUID = -3771172601349925943L;
	Company current;
	Window window;
	Listbox box;
	Paging paging;
	private static final int registrosPorPagina = 50;
	int paginaActual = 0;
	Intbox id;
	Textbox name;
	Button save;
	
	public void doAfterCompose(Component comp) {
		try {
			super.doAfterCompose(comp);
			// Cargamos la cantidad de campuseros en el paginador
			paging.setPageSize(registrosPorPagina);
			HttpServletRequest request = (HttpServletRequest) desktop.getExecution().getNativeRequest();
			paging.setTotalSize(CompanyService.countAll(request));
			paging.addEventListener("onPaging", new EventListener() {
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					PagingEvent pEvent = (PagingEvent) event;
					paginaActual = pEvent.getActivePage();
					reset();
					box.setSelectedIndex(0);
				}
			});
			box.addEventListener("onSelect", new EventListener() {
				public void onEvent(Event event) throws Exception {
					id.setValue(current.getId());
					name.setValue(current.getName());	
					name.setReadonly(false);
					save.setDisabled(false); 
				}				
			});	
		} catch (Exception e) {
			e.printStackTrace();
		}
		//reset();
		
	}
	
	public Company getCurrent() {
		return current;
	}
	
	public void setCurrent(Company current) {
		this.current = current;
	}
	
	public List<Company> getAllItems() {	
		HttpServletRequest request = (HttpServletRequest) desktop.getExecution().getNativeRequest();
		int inicio = paginaActual * registrosPorPagina;
		return CompanyService.findAll(request, inicio, registrosPorPagina);
	}
	
	public void onClick$nuevo() {	
		reset();	
		name.setReadonly(false);
		save.setDisabled(false);
		name.setFocus(true);		
	}

	public void onClick$save() {	
		HttpServletRequest request = (HttpServletRequest) desktop.getExecution().getNativeRequest();
		if(current == null || current.getId() == null) {
			current = new Company();			
		}	
		current.setName(name.getValue());
		
		if(current != null) {
			try {
				current = CompanyService.save(request, current);
				save.setDisabled(true);
			} catch(javax.persistence.RollbackException rEx) {
				if(rEx.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					MessageboxUtil.showAlert("La compa–ia no puede ser guardada", "Ya existe uns compa–ia con ese mismo c—digo");			
				}			
			} catch (Exception e) {
				MessageboxUtil.showGenericException(e);
			}
		}


		
	}

	/*
	public void onClick$eliminar() {	
		if(current != null && current.getId() != null) {
			try {
				String confirmacion = "Desea eliminar el tercero '" + current + "'?";
				if (Messagebox.show(confirmacion, "Eliminar tercero", Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES) {
					GenericDAO<Tercero> dao = new GenericJpaDAO<Tercero>(Tercero.class);
					try {
						dao.delete(current.getId());
						reset();
					} catch(javax.persistence.RollbackException rEx) {
						if(rEx.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
							MessageboxUtil.showAlert("El tercero no puede ser eliminado", "Existen registros que dependen de este tercero. Para poder eliminar este tercero, deben ser eliminados estos registros.");			
						}			
					}
					catch (Exception ex) {
						MessageboxUtil.showGenericException(ex);
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			alert("Debe seleccionar el tercero a ser eliminado");
		}
	}
	*/
	
	private void reset() {
		current = null;	
		resetUI();
	}
	
	private void resetUI() {	
		id.setRawValue(null);
		id.setReadonly(true);
		name.setRawValue(null);
		name.setReadonly(true);
		save.setDisabled(true);
	}
	
}
