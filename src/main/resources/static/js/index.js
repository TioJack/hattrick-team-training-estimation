var playerIdSeek = 1;
var players = [];

$(() => {
    $('#save').click(function () {
        const save = {
            players: players
        }
        const blob = new Blob([JSON.stringify(save, null, 2)], {type: "text/plain;charset=utf-8"});
        saveAs(blob, "htte_config_" + getDate() + ".json");
    });

    $('#load').change(function () {
        const fr = new FileReader();
        fr.readAsText(this.files[0]);
        fr.onload = function () {
            players = [];
            const load = JSON.parse(fr.result);
            load.players.forEach(player => addPlayer(player));
        }
    });

    $('#add_player').click(function () {
        const player = {
            playerId: playerIdSeek++,
            form: $('#new_player_form').val(),
            stamina: $('#new_player_stamina').val(),
            keeper: $('#new_player_keeper').val(),
            defender: $('#new_player_defender').val(),
            playmaker: $('#new_player_playmaker').val(),
            winger: $('#new_player_winger').val(),
            passing: $('#new_player_passing').val(),
            scorer: $('#new_player_scorer').val(),
            setPieces: $('#new_player_setPieces').val(),
            experience: $('#new_player_experience').val(),
            loyalty: $('#new_player_loyalty').val(),
            motherClubBonus: $('#new_player_motherClubBonus').val(),
            specialty: $('#new_player_specialty').val(),
            age: $('#new_player_age').val(),
            days: $('#new_player_days').val(),
            inclusionWeek: $('#new_player_inclusionWeek').val(),
            daysForNextTraining: $('#new_player_daysForNextTraining').val()
        };
        addPlayer(player);
    });

});

function getDate() {
    const now = new Date();
    return [now.getFullYear(), now.getMonth() + 1, now.getDate(), now.getHours(), now.getMinutes(), now.getSeconds()]
        .map(function (i) {
            return ("" + i).padStart(2, "0");
        })
        .join('_');
}

function addPlayer(player) {
    players.push(player);
    $("<div class=\"player b1 p5\" id=\"player_" + player.playerId + "\">\n" +
        "    <div class=\"h tac\">Jugador " + player.playerId + "</div>\n" +
        "    <div class=\"h\">Años: " + player.age + "</div>\n" +
        "    <div class=\"h\">Días: " + player.days + "</div>\n" +
        "    <div class=\"h\">Forma: " + player.form + "</div>\n" +
        "    <div class=\"h\">Resistencia: " + player.stamina + "</div>\n" +
        "    <div class=\"h\">Experiencia: " + player.experience + "</div>\n" +
        "    <div class=\"h\">Fidelidad: " + player.loyalty + "</div>\n" +
        "    <div class=\"h\">Club de origen: " + player.motherClubBonus + "</div>\n" +
        "    <div class=\"h\">Especialidad: " + player.specialty + "</div>\n" +
        "    <div class=\"h\">Porteria: " + player.keeper + "</div>\n" +
        "    <div class=\"h\">Defensa: " + player.defender + "</div>\n" +
        "    <div class=\"h\">Jugadas: " + player.playmaker + "</div>\n" +
        "    <div class=\"h\">Lateral: " + player.winger + "</div>\n" +
        "    <div class=\"h\">Pases: " + player.passing + "</div>\n" +
        "    <div class=\"h\">Anotación: " + player.scorer + "</div>\n" +
        "    <div class=\"h\">Balón Parado: " + player.setPieces + "</div>\n" +
        "    <div class=\"h\">Semana de incorporación: " + player.inclusionWeek + "</div>\n" +
        "    <div class=\"h\">Días para el próximo entrenamiento: " + player.daysForNextTraining + "</div>\n" +
        "    <div class=\"p20 tac\"><label class=\"button\" onclick=\"delPlayer(" + player.playerId + ")\">Borrar Jugador</label></div>\n" +
        "</div>\n"
    ).appendTo($('#players'));
}

function delPlayer(playerId) {
    players.splice(players.findIndex(player => player.playerId === playerId), 1);
    $('#player_' + playerId).remove();
    console.log(players);
}
