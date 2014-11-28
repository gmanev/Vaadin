package com.jjinterna.vaadin.vaadinbridge;

import com.vaadin.ui.UI;

public interface ApplicationFactory {

    static final String ALIAS_NAME = "alias";

    Class<? extends UI> getUIClass();
    
    public UI getInstance();

}