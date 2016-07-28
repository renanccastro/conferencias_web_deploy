package br.com.tecsinapse.service;

import br.com.tecsinapse.interceptor.Logging;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.model.Video;
import br.com.tecsinapse.util.EnvProperties;
import br.com.tecsinapse.util.Token;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Logging
public class VideoService extends GenericService<Video, Long> {

}
