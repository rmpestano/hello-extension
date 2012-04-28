/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdi.extension.helloextension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;
import javax.enterprise.util.AnnotationLiteral;
import org.jboss.solder.literal.InjectLiteral;
import org.jboss.solder.reflection.annotated.AnnotatedTypeBuilder;

/**
 *
 * @author rafael-pestano
 */
public class HelloExtension implements Extension {
    private AnnotatedTypeBuilder<BaseHelloBean> builder = null;
    
    
    void processAnnotatedType(@Observes ProcessAnnotatedType<BaseHelloBean> pat) {
        final AnnotatedType<BaseHelloBean> at = pat.getAnnotatedType();
        if (pat.getAnnotatedType().isAnnotationPresent(Portuguese.class)) {
            injectLanguage(at, new PortugueseImpl());
        } else if(pat.getAnnotatedType().isAnnotationPresent(English.class)){
             injectLanguage(at, new EnglishImpl());
        }
        if(builder != null){
            pat.setAnnotatedType(builder.create());
        }



    }
    
    /**
     * metodo responsavel por injetar as anotações @Inject e @English/@Portuguese 
     * no campo Hello 
     */
    private void injectLanguage(AnnotatedType<BaseHelloBean> at, AnnotationLiteral language){
         for (AnnotatedField<? super BaseHelloBean> annotatedField : at.getFields()) {
                if (annotatedField.getBaseType().equals(Hello.class)) {
                    builder = new AnnotatedTypeBuilder().readFromType(at);
                    builder.addToField(annotatedField, language);
                    builder.addToField(annotatedField, new InjectLiteral());
                }
            }
    }
}