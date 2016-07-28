package br.com.tecsinapse.controller;

import br.com.tecsinapse.agenda.EventoScheduleEvent;
import br.com.tecsinapse.cdi.annotation.UsuarioLogado;
import br.com.tecsinapse.model.Evento;
import br.com.tecsinapse.model.Post;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.model.enums.Recorrencia;
import br.com.tecsinapse.service.EventoService;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "eventos", pattern = "/eventos/", viewId = "/jsf/pages/protegido/geral/evento/eventos.xhtml")
//        @URLMapping(id = "post", pattern = "/post/id/#{postController.postId}/", viewId = "/jsf/pages/protegido/geral/post/post.xhtml"),
//        @URLMapping(id = "post-novo", pattern = "/post/novo", viewId = "/jsf/pages/protegido/geral/post/post.xhtml")
})
public class EventoController extends LazyScheduleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    @UsuarioLogado
    private Usuario usuarioLogado;

    @Inject
    private EventoService eventoService;

    private Evento evento = new Evento();

    private boolean online = true;

    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

//    @URLAction(mappingId = "eventos", onPostback = false)
//    public void idPosts() {
//        posts = postService.findAll();
//    }
//
//    @URLAction(mappingId = "evento", onPostback = false)
//    public void idPost() {
//        post = postService.findById(postId);
//    }
//
//    @URLAction(mappingId = "evento-novo", onPostback = false)
//    public void idUsuarioNovo() {
//        post = new Post();
//    }

    @Override
    public void loadEvents(Date start, Date end) {

        List<Evento> compromissos = eventoService.findPorDataEIgreja(this.usuarioLogado.getIgreja(), start, end);

        for(Evento compromisso: compromissos) {
            addEvent(new EventoScheduleEvent(compromisso));
        }
    }

    public void selectOnline(){
        System.out.println("ae");

    }
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void removeEvent(ActionEvent actionEvent) {
        if (this.evento.getId() != null) {
            eventoService.deleteEvento(this.evento);
            updateEvent(new EventoScheduleEvent(this.evento));
        }
    }
    public void addEvent(ActionEvent actionEvent) {
        this.evento.setIgreja(this.usuarioLogado.getIgreja());
        eventoService.save(this.evento);
        addEvent(new EventoScheduleEvent(this.evento));
//        this.evento = new Evento();
//        if(evento.getId() == null)
//            eventModel.addEvent(event);
//        else
//            eventModel.updateEvent(event);
//
//        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        evento = ((EventoScheduleEvent) selectEvent.getObject()).getEvento();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        evento = new Evento();
        evento.setStartDate((Date)selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        //TODO: Tratar Move
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//
//        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        //TODO: Tratar Resize
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//
//        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Recorrencia> getRecurringValues(){
        return Arrays.asList(Recorrencia.values());
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
