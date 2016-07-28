package br.com.tecsinapse.service;

import br.com.tecsinapse.interceptor.Logging;
import br.com.tecsinapse.model.Email;
import br.com.tecsinapse.model.Post;
import br.com.tecsinapse.model.Tag;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TagService extends GenericService<Tag, Long> {
    public Tag findPorTitulo(String titulo) {
        TypedQuery<Tag> query = getEntityManager().createNamedQuery(
                "Tag.findPorTitulo", Tag.class);
        query.setParameter("titulo", titulo);
        List<Tag> tags = query.getResultList();
        if(tags.size() > 0)
            return tags.get(0);
        else
            return null;
    }

}
