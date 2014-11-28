package com.jjinterna.vaadin.vaadinbridge.internal;

import javax.servlet.ServletException;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;

import com.vaadin.server.VaadinServlet;


class HttpServiceTracker extends ServiceTracker {
	
	HttpServiceTracker(BundleContext context) {
		super(context, HttpService.class.getName(), null);
	}
	
	@Override
	public Object addingService(ServiceReference reference) {
		HttpService httpService = (HttpService) context.getService(reference);
		try {
			BundleContentHttpContext httpContext = new BundleContentHttpContext(reference.getBundle());
			httpService.registerServlet("/VAADIN/*", new VaadinServlet(), null, httpContext);

			httpService.registerResources("/VAADIN/widgetsets", "/VAADIN/widgetsets", new TargetBundleHttpContext(context, "VAADIN.widgetsets"));
			httpService.registerResources("/VAADIN/themes", "/VAADIN/themes", new TargetBundleHttpContext(context, "VAADIN.themes"));			
		} catch (NamespaceException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ApplicationFactoryTracker bridge = new ApplicationFactoryTracker(httpService, context);
		bridge.open();
		return bridge;
	}
	@Override
	public void removedService(ServiceReference reference, Object service) {
		ApplicationFactoryTracker bridge = (ApplicationFactoryTracker) service;
		bridge.close();
		context.ungetService(reference);
	}
}
