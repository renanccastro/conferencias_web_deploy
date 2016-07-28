package br.com.tecsinapse.controller;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;

import br.com.tecsinapse.cdi.annotation.UsuarioLogado;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.util.Constantes;

@Named
@SessionScoped
public class BaseController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private transient Logger logger;

    private Usuario usuarioLogado;

    /**
     * protected usar com @Inject
     */
    @Named("usuarioLogado")
    @Produces
    @UsuarioLogado
    protected Usuario getUsuarioLogado() {
        final Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            return null;
        }

        if (usuarioLogado == null) {
            usuarioLogado = subject.getPrincipals().oneByType(Usuario.class);
        }
        return usuarioLogado;
    }

    private void redirect(String page) {
        try {
            if (!getHttpServletResponse().isCommitted()) {
                logger.info("redirect to " + page);
                getHttpServletResponse().sendRedirect(getContextPath() + page);
            }
        } catch (IOException e) {
            logger.error("Erro ao redirecionar para " + page, e);
        }
    }

    public String getRealPath() {
        try {
            ExternalContext ec = getExternalContext();
            URI uri = new URI(ec.getRequestScheme(), null,
                    ec.getRequestServerName(), ec.getRequestServerPort(), null,
                    null, null);
            return uri.toASCIIString();
        } catch (URISyntaxException e) {
            throw new FacesException(e);
        }
    }

    public void redirectPageNotFound() {
        redirect("/pagina-nao-existe/");
    }

    public void redirectPageAccessNotAllowed() {
        redirect("/acesso-nao-autorizado/");
    }

    public boolean isAdminTS() {
        return getUsuarioLogado().getEmail().equalsIgnoreCase(Constantes.LIP_MAIL);
    }

    public String getUserName(){
        return getUsuarioLogado().getNome();
    }

    public String getUserPicture(){
        String image = getUsuarioLogado().getProfileImage();
        return "data:image/png;base64," + image;
    }
    public String getChurchImage(){
        String image = getUsuarioLogado().getIgreja().getProfileImage();
        return "data:image/png;base64," + image;
    }

    public void checkAdminTS() {
        if (!isAdminTS()) {
            redirectPageAccessNotAllowed();
        }
    }

    public String getCreatedDateLoggedUser() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(this.usuarioLogado.getDataHoraCriacao());
        return reportDate;

    }

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public HttpServletResponse getHttpServletResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public String getContextPath() {
        return getHttpServletRequest().getContextPath();
    }

    public String getRequestURL() {
        return getHttpServletRequest().getRequestURL().toString();
    }
}
