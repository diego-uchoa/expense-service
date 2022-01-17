package org.acme.rest.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.rest.json.Expense.PaymentMethod;
import org.jboss.logging.Logger;

@Path("/expenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpenseResource {
    @Inject
    public ExpenseService expenseService;

    private final Logger logger = Logger.getLogger(ExpenseResource.class);

    @GET
    public Set<Expense> list() {
        Set<Expense> lista = expenseService.list();
        if(lista.isEmpty()){
            lista.add(new Expense("Groceries", PaymentMethod.CASH, "150.50"));
        }

        String logLista = "{";

        List<Expense> arrayLista = new ArrayList<Expense>();

        for (Expense expense : lista) {
            arrayLista.add(expense);    
        }

        logLista+="\"name\":\""+ arrayLista.get(0).getName() +"\"";
        logLista+=",\"amount\":\""+ arrayLista.get(0).getAmount() +"\"";
        logLista+=",\"paymentMethod\":\""+ arrayLista.get(0).getPaymentMethod() +"\"";
        logLista+=",\"uuid\":\""+ arrayLista.get(0).getUuid() +"\"";
        logLista+="}";

        System.out.println(logLista);

        return lista;
    }

    @POST
    public Expense create(Expense expense) {
        return expenseService.create(expense);
    }

    @DELETE
    @Path("{uuid}")
    public Set<Expense> delete(@PathParam("uuid") UUID uuid) {
        if (!expenseService.delete(uuid)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return expenseService.list();
    }

    @PUT
    public void update(Expense expense) {
        expenseService.update(expense);
    }
}