package br.com.tecsinapse.agenda;

import br.com.tecsinapse.model.Evento;
import org.primefaces.model.ScheduleEvent;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.primefaces.model.ScheduleEvent;

public class EventoScheduleEvent implements ScheduleEvent, Serializable {

    private Evento evento;

    public EventoScheduleEvent(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String getId() {
        return evento.getId().toString();
    }

    @Override
    public void setId(String id) {}

    @Override
    public Object getData() {
        return evento;
    }

    public Evento getEvento() {
        return evento;
    }

    @Override
    public String getTitle() {
        return evento.getNome();
    }


    @Override
    public Date getStartDate() {
        return evento.getStartDate();
    }

    @Override
    public Date getEndDate() {
        return evento.getEndDate();
    }

    @Override
    public boolean isAllDay() {
        return false;
    }

    @Override
    public String getStyleClass() {
        return "ts-event";
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public String getDescription() {
        return this.evento.getDescricao();
    }

}