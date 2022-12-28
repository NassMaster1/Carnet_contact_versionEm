package org.miage.carnet_contact.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//si je veux intégrer une config XML venant de mon legacy, j'utilise l'@ImportResource
//@ImportResource(locations = {"classpath:spring/app-context-xml.xml"})

@ImportResource(locations = {"classpath:applicationContext.xml"})
@ComponentScan(basePackages = {"org.miage.carnet_contact.*"})
@Configuration
public class AppConfiguration {

    //plus rien dans la classe Java, juste une indication ou scanner les beans
}