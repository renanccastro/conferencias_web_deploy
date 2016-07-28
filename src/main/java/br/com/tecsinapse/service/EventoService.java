package br.com.tecsinapse.service;

import br.com.tecsinapse.interceptor.Logging;
import br.com.tecsinapse.model.*;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Stateless
@Logging
public class EventoService extends GenericService<Evento, Long> {
    public List<Evento> findPorDataEIgreja(Igreja igreja, Date start, Date end) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Evento.class);
        cq.where(cb.greaterThanOrEqualTo(e.get("startDate"), start));
        cq.where(cb.lessThanOrEqualTo(e.get("endDate"), end));
        cq.select(e);
        cq.where(cb.equal(e.get("igreja"),igreja));
        Query query = getEntityManager().createQuery(cq);
        List<Evento> result = query.getResultList();
        return result;
    }
    public void deleteEvento(Evento e){
        getEntityManager().remove(getEntityManager().contains(e) ? e : getEntityManager().merge(e));
    }
}
