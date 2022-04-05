package com.testedev.openApi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(
        description = "API Rest - Teste para Desenvolvedor Java.",
        title = "Teste Dev. Java.",
        version = "0.0.1", contact = @Contact (name = "Pedro Rodrigues", email = "phr-pedro@outlook.com")))
public class ApplicationOpenApi extends Application {
}
