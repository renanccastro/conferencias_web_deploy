/**
 * Created by renancastro on 20/07/16.
 */
function setImagePreview(){
    if(typeof PF != "undefined" && PF) {
        if (PF('upload')){
            PF('upload').jq.change(
                function () {
                    var reader = new FileReader();
                    reader.onload = function (event) {
                        $('#uploadImageChange').attr("src", event.target.result);
                    };
                    reader.readAsDataURL($(this).prop('files')[0]);
                });
        }
    }
}

function addMenuSearchFunction(){
    $(".search-menu-box").on('input', function() {
        console.log("teste2");
        var filter = $(this).val();
        $(".sidebar-menu > li").each(function(){
            console.log("teste");
            if ($(this).text().search(new RegExp(filter, "i")) < 0) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });
    });

}

function addLocaleToPrimefaces(){
    if (typeof PrimeFaces != 'undefined') {
        PrimeFaces.locales['pt'] = {
            closeText: 'Fechar',
            prevText: 'Anterior',
            nextText: 'Próximo',
            currentText: 'Começo',
            monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
            monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Des'],
            dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
            dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
            dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
            weekHeader: 'Semana',
            firstDay: 1,
            isRTL: false,
            showMonthAfterYear: false,
            yearSuffix: '',
            timeOnlyTitle: 'Só Horas',
            timeText: 'Tempo',
            hourText: 'Hora',
            minuteText: 'Minuto',
            secondText: 'Segundo',
            currentText: 'Data Atual',
            ampm: false,
            month: 'Mês',
            week: 'Semana',
            day: 'Dia',
            allDayText: 'Todo Dia'
        };
    }
}

function enableSelectPicker(){
    $(".selectpicker").selectpicker({});
}

function hackPrimefacesScheduler() {
    if (PrimeFaces.widget.Schedule != undefined) {

        PrimeFaces.widget.Schedule.prototype.init = function () {
            this.cfg.titleFormat = {
                month: 'MMMM yyyy',
                week: "De d MMM [ yyyy]{ 'à' d [ MMM] 'de' yyyy}",
                day: 'dddd, d MMM , yyyy'
            };
            this.cfg.columnFormat = {
                month: 'ddd',
                week: 'ddd d/M',
                day: 'dddd M/d'
            };
            if (this.jq.is(':visible')) {
                this.jqc.fullCalendar(this.cfg);
                return true;
            } else {
                return false;
            }
        };
    }
}

$(window).bind("load", function() {
    setImagePreview();
    addMenuSearchFunction();
    // enableSelectPicker();
    hackPrimefacesScheduler();
});
addLocaleToPrimefaces();

