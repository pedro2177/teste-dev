package com.testedev.controller;

import com.testedev.models.Estado;
import com.testedev.service.EstadoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/estado")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadosResource {

    @Autowired
    @Inject
    EstadoService service;

    @GET
    @Path("/pag")
    @Operation(
	summary = "Busca de Estados (Com Paginação).",
	description = "Deve-se informar o Size (Tamanho) da lista que deseja receber" +
            ", assim como a página que irá receber. Obs: ao escolher um Size" +
            "menor ou igual a 0 o padrão (24) será aplicado.")
    public Response getEstados(@QueryParam("size") int size,
                               @QueryParam("page") int page) {
        return Response.ok(service.getEstados(size, page))
                .status(Response.Status.OK).build();
    }

    @GET
    @Path("/registros")
    @Operation(
	summary = "Busca quantidade de registros.",
	description = "Busca os registros na tabela Estado."
)
    public long findRegistros() {
        return service.getNumRegistros();
    }

    @GET
    @Path("/getAll")
    @Operation(
	summary = "Busca todos os Estados.",
	description = "Busca todos os Estados cadastrados no sistema."
)
    public Response getEstadoById() {
        return Response
                .ok(service.getEstados())
                .status(Response.Status.OK)
                .build();
    }

    @POST
    @Path("/create")
    @Operation(
	summary = "Criação de Estado",
	description = "Cria um Estado, obs: não deve enviar o ID, ele é gerado automaticamente."
)
    public Response createEstado(Estado estado) {
        return Response
                .ok(service.createEstado(estado))
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("/update")
    @Operation(
	summary = "Atualiza o Estado com base no ID.",
	description = "Ao solicitar a atualização, será verificado o ID enviado, caso seja encontrado, será feita a atualização dos dados."
)
    public Response updateEstado(Estado estado) {
        return Response
                .ok(service.updateEstado(estado))
                .status(Response.Status.OK)
                .build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Operation(
	summary = "Apaga o Estado",
	description = "Ao enviar o id pela URL, será apagado o Estado com o mesmo, caso seja encontrado."
)
    public Response deleteEstadoById(@PathParam("id") Integer id) {
        service.deleteById(id);
        return Response
                .ok()
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
