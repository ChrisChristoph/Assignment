package org.chris;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.persistence.criteria.Expression;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractService<T> {
    private Class<T> entityClass;

    public AbstractService(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public ResponseBuilder addCORShdr(ResponseBuilder rb) {
        return rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept");
    }

    public Response getResponse(Object o) {
        Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
        String jsonInString = gson.toJson(o);
        return addCORShdr(Response.ok()).entity(jsonInString).build();
    }
}
