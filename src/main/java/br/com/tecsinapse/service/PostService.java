package br.com.tecsinapse.service;

import br.com.tecsinapse.interceptor.Logging;
import br.com.tecsinapse.model.Post;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.model.Video;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@Logging
public class PostService extends GenericService<Post, Long> {
    public List<Post> findPublished() {
        //cuidado, use o import javax.persistence.Query
        Query query = getEntityManager()
                .createQuery("select p from Post as p "+
                        "where p.publicado= :paramPublicado");
        query.setParameter("paramPublicado", true);

        return query.getResultList();
    }

}
