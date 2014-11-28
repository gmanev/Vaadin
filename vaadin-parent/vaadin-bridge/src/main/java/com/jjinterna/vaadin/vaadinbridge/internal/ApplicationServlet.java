package com.jjinterna.vaadin.vaadinbridge.internal;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

public class ApplicationServlet extends VaadinServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 846707948381211662L;

	private UIProvider provider;
	
	public ApplicationServlet(UIProvider provider) {
		this.provider = provider;
	}
	
	@Override
	protected VaadinServletService createServletService(
			DeploymentConfiguration deploymentConfiguration)
			throws ServiceException {
		VaadinServletService servletService = 
				super.createServletService(deploymentConfiguration);

		servletService.addSessionInitListener(new SessionInitListener() {
			@Override
			public void sessionInit(SessionInitEvent sessionInitEvent) throws ServiceException {
				sessionInitEvent.getSession().addUIProvider(provider);
			}
		});

		return servletService;
	}

}
