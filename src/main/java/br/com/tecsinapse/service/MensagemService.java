package br.com.tecsinapse.service;

import br.com.tecsinapse.interceptor.Logging;
import br.com.tecsinapse.model.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Configuration;

import br.com.tecsinapse.util.EnvProperties;
import com.sun.tools.doclint.Env;
import org.slf4j.Logger;

import javax.ejb.AccessTimeout;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Stateless
@Logging
public class MensagemService extends GenericService<Mensagem, Long> {
    @Inject
    private transient Logger logger;

    @Inject
    private EnvProperties properties;

    private Client client = ClientBuilder.newClient();


    @Schedule(second = "0", minute = "*", hour = "*", persistent = false)
    @AccessTimeout(-1)
    public void enviarPushs() {
        logger.info("agendamento - inicio - enviarPushs");
//        List<Email> emails = this.
//                .findNaoEnviadosAindaComTentativasAFazer();

//        for (Email email : emails) {
//            enviarEmail(email);
//        }
        logger.info("agendamento - fim - enviarPushs");
    }

    public void enviarPush(Mensagem mensagem) {
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget target = cliente.target(properties.pushHost())
                    .path("/api/v1/notifications");

            logger.info("\n\nEnviando PUSH para o serviço: {}", target.getUri());

            MensagemPushModel model = new MensagemPushModel();
            model.setApp_id(properties.pushAppId());
            model.setContents(new HashMap<String, String>(){
                {
                    put("en", mensagem.getMensagem());
                }
            });
            model.setIncluded_segments(Arrays.asList("All"));

            Response response = target.request()
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Basic " + properties.pushToken())
                    .post(Entity.json(model));

            logger.info("\n\nResponse from post: {}", response);

            String resposta = response.readEntity(String.class);

            logger.info("\n\nResposta push: {}", resposta);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        } finally {
            cliente.close();
        }
    }


}

