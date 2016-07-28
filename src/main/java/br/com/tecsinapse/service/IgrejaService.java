package br.com.tecsinapse.service;

import br.com.tecsinapse.interceptor.Logging;
import br.com.tecsinapse.model.Igreja;
import br.com.tecsinapse.model.Post;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Logging
public class IgrejaService extends GenericService<Igreja, Long> {
}
