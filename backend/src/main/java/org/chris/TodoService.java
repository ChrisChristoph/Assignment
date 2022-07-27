package org.chris;

import org.chris.TodoEntity;
import org.hibernate.exception.ConstraintViolationException;

import io.quarkus.hibernate.orm.PersistenceUnit;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/todo")
public class TodoService extends AbstractService<TodoEntity> {
    public TodoService() {
        super(TodoEntity.class);
    }

    @Inject
    EntityManager em;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getTodoList() {
        List<TodoEntity> result = TodoEntity.listAll();

        return getResponse(result);
    }

    @Transactional
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response insert(TodoEntity data) {
        // data.id = null;
        data.persist();

        return addCORShdr(Response.status(Status.CREATED)).entity(data).build();
    }

    @OPTIONS
    public Response options() {
        return addCORShdr(Response.ok()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getTodoList(@PathParam("id") final Long id) {
        if (id != null) {
            List<TodoEntity> data = TodoEntity.findById(id);
            TodoEntity element = data.size() > 0 ? data.get(0) : null;
            return getResponse(element);
        }
        return addCORShdr(Response.status(Status.BAD_REQUEST)).entity("").build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}