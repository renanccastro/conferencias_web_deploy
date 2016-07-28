package br.com.tecsinapse.service;

import br.com.tecsinapse.interceptor.Logging;
import br.com.tecsinapse.model.Ministerio;
import br.com.tecsinapse.model.Video;

import javax.ejb.Stateless;

@Stateless
@Logging
public class MinisterioService extends GenericService<Ministerio, Long> {

}