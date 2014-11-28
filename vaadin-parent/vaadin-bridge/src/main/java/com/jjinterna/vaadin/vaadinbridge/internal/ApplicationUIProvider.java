package com.jjinterna.vaadin.vaadinbridge.internal;

import com.jjinterna.vaadin.vaadinbridge.ApplicationFactory;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

public class ApplicationUIProvider extends UIProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8133990779270081602L;

	private final ApplicationFactory factory;

    ApplicationUIProvider(ApplicationFactory factory) {
        this.factory = factory;
    }

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		return factory.getUIClass();
	}

    @Override
	public UI createInstance(UICreateEvent event) {
		return factory.getInstance();
	}

}
