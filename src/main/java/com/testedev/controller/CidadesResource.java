package com.testedev.controller;

import com.testedev.models.CidadeDTO;
import com.testedev.service.CidadeService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cidade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadesResource {

    @Inject
    CidadeService service;

    @GET
    @Path("getPag")
    @Operation(
            summary = "Busca de Cidades (Com Paginação)",
            description = "Deve-se informar o Size (Tamanho) da lista que deseja receber\" +\n" +
                    "\", assim como a página que irá receber. Obs: ao escolher um Size\" +\n" +
                    "\"menor ou igual a 0 o padrão (24) será aplicado."
    )
    public Response getCidade(@QueryParam("size") int size,
                              @QueryParam("page") int page) {
        return Response
                .ok(service.getCidade(page, size))
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/{id}")
    @Operation(
	summary = "Busca cidade pelo ID.",
	description = "ID sempre deve ser maior que 0."
)
    public Response getCidadeById(@PathParam("id") int id) {
        return Response
                .ok(service.getById(id))
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/byEstado/{estado}")
    @Operation(
	summary = "Busca a cidade pelo Estado",
	description = "Essa busca serve tanto para a sigla quanto para o nome em si do estado."
)
    public Response getByEstado(@PathParam("estado") String estado) {
        return Response
                .ok(service.getByEstado(estado))
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/byNome/{nome}")
    @Operation(
	summary = "Busca pelo nome da Cidade",
	description = "A partir de 3 caracteres é possível encontrar a cidade que deseja, retornando uma lista com as cidades encontradas."
)
    public Response getByNome(@PathParam("nome") String nome) {
        return Response
                .ok(service.getByNome(nome))
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/registros")
    @Operation(
            summary = "Busca quantidade de registros.",
            description = "Busca os registros na tabela Cidade."
    )
    public long getNumRegistros() {
        return service.getNumRegistros();
    }

    @PUT
    @Path("/update")
    @Operation(
            summary = "Atualiza o Cidade com base no ID.",
            description = "Ao solicitar a atualização, será verificado o ID enviado, caso seja encontrado, será feita a atualização dos dados."
    )
    public Response updateCidade(CidadeDTO cidadeDTO) {
        return Response
                .ok(service.updateCidade(cidadeDTO))
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @POST
    @Path("/create")
    @Operation(
            summary = "Criação de Cidade",
            description = "Cria uma Cidade, obs: não deve enviar o ID, ele é gerado automaticamente."
    )
    public Response createCidade(CidadeDTO cidade) {
        return Response
                .ok(service.createCidade(cidade))
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("/deleteById/{id}")
    @Operation(
            summary = "Apaga a Cidade",
            description = "Ao enviar o id pela URL, será apagado a Cidade com o mesmo id, caso seja encontrado."
    )
    public Response deleteById(@PathParam("id") Integer id) {
        service.deleteCidade(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
