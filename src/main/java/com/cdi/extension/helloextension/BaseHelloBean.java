/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdi.extension.helloextension;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author rafael-pestano
 */
public abstract class BaseHelloBean {
    
     protected Hello hello;//this attribute will be injected by the child class via setter method
 
 
     
    public Hello getHello(){
        return hello;
    }
    
    public void setHello(Hello hello){
        this.hello = hello;
    }
 
}