package br.com.tecsinapse.controller;

import br.com.tecsinapse.cdi.annotation.UsuarioLogado;
import br.com.tecsinapse.model.Mensagem;
import br.com.tecsinapse.model.Ministerio;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.service.MensagemService;
import br.com.tecsinapse.service.MinisterioService;
import br.com.tecsinapse.service.UsuarioService;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.omnifaces.util.Messages;
import org.primefaces.model.UploadedFile;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "mensagens", pattern = "/mensagens/", viewId = "/jsf/pages/protegido/geral/mensagem/mensagens.xhtml"),
        @URLMapping(id = "mensagem", pattern = "/mensagem/id/#{mensagemController.mensagemId}/", viewId = "/jsf/pages/protegido/geral/mensagem/mensagem.xhtml"),
        @URLMapping(id = "mensagem-nova", pattern = "/mensagem/nova", viewId = "/jsf/pages/protegido/geral/mensagem/mensagem.xhtml")


})
public class MensagemController implements Serializable {
    private static final long serialVersionUID = 1L;


    @Inject
    private MensagemService mensagemService;

    @Inject
    @UsuarioLogado
    private Usuario usuarioLogado;



    private List<Usuario> usuarios;

    private UploadedFile file;

    private List<Mensagem> mensagens;
    private Long mensagemId;
    private Mensagem mensagem;

    @URLAction(mappingId = "mensagens", onPostback = false)
    public void idMensagens() {
        mensagens = mensagemService.findAll();
    }

    @URLAction(mappingId = "mensagem", onPostback = false)
    public void idMensagem() {
        mensagem = mensagemService.findById(mensagemId);
//        usuarios = usuarioService.findAllByChurch(this.usuarioLogado.getIgreja());
    }

    @URLAction(mappingId = "mensagem-nova", onPostback = false)
    public void idMensagemNova() {
        mensagem = new Mensagem();
        mensagem.setDataHoraEnvio(new Date());
//        usuarios = usuarioService.findAllByChurch(this.usuarioLogado.getIgreja());
    }
//
    public void salvar() {
        if (mensagem.getIgreja() == null)
            mensagem.setIgreja(usuarioLogado.getIgreja());
        if (mensagem.getCriador() == null)
            mensagem.setCriador(usuarioLogado);

        mensagemService.save(mensagem);
        Messages.addGlobalInfo("Mensagem salva com sucesso!");
    }

    public void enviar(Mensagem m) {
        mensagemService.enviarPush(m);
    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Long getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(Long mensagemId) {
        this.mensagemId = mensagemId;
    }

}
