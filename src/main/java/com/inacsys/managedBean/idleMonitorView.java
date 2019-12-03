package com.inacsys.managedBean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

@ManagedBean
public class idleMonitorView {

	private static Logger logger = Logger.getLogger(idleMonitorView.class);

	public void onIdle() throws IOException {
		logger.info("[onIdle()] --------------- Inside onIdle() method() ------------------------");
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		//FacesContext.getCurrentInstance().getExternalContext()
				//.redirect("/inacsys/pages/xhtml/sessionTimeoutPage.xhtml");
		//RequestContext.getCurrentInstance().execute("PF('sessionDialog').show();");
		FacesContext.getCurrentInstance().getExternalContext().redirect("/inacsys/pages/xhtml/login.xhtml");
		logger.debug("inside onIdle");
	}
}
