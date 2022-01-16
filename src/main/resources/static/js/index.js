let playerIdSeed = 1;
let players = [];
let trainingStageIdSeed = 1;
let trainingStages = [];
let results = {};

let slots = {
    9: [{por: 100, max: 2, trg: 1}],
    3: [{por: 100, max: 10, trg: 2}],
    8: [{por: 100, max: 6, trg: 3}, {por: 50, max: 4, trg: 4}],
    5: [{por: 100, max: 4, trg: 5}, {por: 50, max: 4, trg: 6}],
    7: [{por: 100, max: 16, trg: 7}],
    4: [{por: 100, max: 6, trg: 8}],
    2: [{por: 125, max: 4, trg: 10}, {por: 100, max: 18, trg: 9}]
}

$(() => {
    $('#save').click(function () {
        const save = {
            version: 1,
            players: players,
            trainingStages: trainingStages,
            playerTraining: $("[id^='cb_']:checked").map(function (i, e) {
                return e.id
            }).toArray(),
            results: results
        }
        const blob = new Blob([JSON.stringify(save, null, 2)], {type: "text/plain;charset=utf-8"});
        saveAs(blob, "htte_config_" + getDate() + ".json");
    });

    $('#load').change(function () {
        try {
            const fr = new FileReader();
            fr.readAsText(this.files[0]);
            fr.onload = function () {
                resetPlayers();
                resetTrainingStages();
                const load = JSON.parse(fr.result);
                load.players.forEach(player => addPlayer(player));
                playerIdSeed = Math.max(...players.map(player => parseInt(player.playerId))) + 1;
                load.trainingStages.forEach(trainingStage => addTrainingStage(trainingStage));
                trainingStageIdSeed = Math.max(...trainingStages.map(trainingStage => parseInt(trainingStage.trainingStageId))) + 1;
                refreshPlayerTraining();
                load.playerTraining.forEach(pt => $("#" + pt).click());
                results = load.results;
                loadResults(results);
            }
        } catch (e) {
        }
    });

    $('#add_player').click(function () {
        const player = {
            playerId: playerIdSeed++,
            form: parseInt($('#new_player_form').val()),
            stamina: parseInt($('#new_player_stamina').val()),
            keeper: parseInt($('#new_player_keeper').val()),
            defender: parseInt($('#new_player_defender').val()),
            playmaker: parseInt($('#new_player_playmaker').val()),
            winger: parseInt($('#new_player_winger').val()),
            passing: parseInt($('#new_player_passing').val()),
            scorer: parseInt($('#new_player_scorer').val()),
            setPieces: parseInt($('#new_player_setPieces').val()),
            experience: parseInt($('#new_player_experience').val()),
            loyalty: parseInt($('#new_player_loyalty').val()),
            motherClubBonus: parseInt($('#new_player_motherClubBonus').val()),
            specialty: parseInt($('#new_player_specialty').val()),
            age: parseInt($('#new_player_age').val()),
            days: parseInt($('#new_player_days').val()),
            inclusionWeek: parseInt($('#new_player_inclusionWeek').val()),
            daysForNextTraining: parseInt($('#new_player_daysForNextTraining').val())
        };
        addPlayer(player);
        refreshPlayerTraining();
    });

    $('#add_trainingStage').click(function () {
        const trainingStage = {
            trainingStageId: trainingStageIdSeed++,
            duration: parseInt($('#new_stage_duration').val()),
            coach: parseInt($('#new_stage_coach').val()),
            assistants: parseInt($('#new_stage_assistants').val()),
            intensity: parseInt($('#new_stage_intensity').val()),
            stamina: parseInt($('#new_stage_stamina').val()),
            training: parseInt($('#new_stage_training').val())
        };
        addTrainingStage(trainingStage);
        refreshPlayerTraining();
    });

    $('#calculate').click(function () {
        const stagePlayerTraining = Object.fromEntries(trainingStages.map(trainingStage => [trainingStage.trainingStageId, $("[id^='cb_" + trainingStage.trainingStageId + "_']:checked").map(function (i, e) {
            const split = e.id.split('_');
            const trg = parseInt(split[4]);
            const playerId = parseInt(split[5]);
            let obj = {};
            obj[playerId] = trg;
            return obj;
        }).toArray().reduce(((r, c) => Object.assign(r, c)), {})
        ]));

        const teamTrainingRQ = {
            players: Object.fromEntries(new Map(players.map(player => [player.playerId, player]))),
            trainingStages: Object.fromEntries(new Map(trainingStages.map(trainingStage => [trainingStage.trainingStageId, trainingStage]))),
            stagePlayerTraining: stagePlayerTraining
        };

        $.ajax({
            url: '/teamTraining',
            type: 'POST',
            data: JSON.stringify(teamTrainingRQ),
            contentType: 'application/json',
            dataType: 'json',
            async: true,
            success: function (teamTrainingRS) {
                results = teamTrainingRS.weekPlayers;
                loadResults(results);
            }
        });
    });
});

function loadResults(results) {
    let weeks = Object.keys(results);
    let first = weeks[0];
    let last = weeks[weeks.length - 1];
    let html = "<input id='result_range' type='range' min='" + first + "' max='" + last + "' oninput=\"week.value=result_range.value\">";
    html += "Semana: <output id=\"week\" for=\"result_range\"></output>";
    $('#div_results_range').html(html);
    $('#result_range').change(function (item) {
        $('#div_results').html("");
        const week = item.target.value;
        results[week].forEach(player => addPlayerResult(player));
    });
    $('#result_range').val(last);
    $('#week').val(last);
    $('#result_range').trigger('change');
}

function addPlayerResult(player) {
    const ini = players.filter(pl => pl.playerId === player.playerId)[0];
    $("<div class=\"res_player_top\">\n" +
        "  <div class=\"res_player b1 p5\">\n" +
        "    <div class=\"h tac\">Jugador " + ini.playerId + "</div>\n" +
        "    <div class=\"h\">Años: " + ini.age + "</div>\n" +
        "    <div class=\"h\">Días: " + ini.days + "</div>\n" +
        "    <div class=\"h\">Porteria: " + ini.keeper + "</div>\n" +
        "    <div class=\"h\">Defensa: " + ini.defender + "</div>\n" +
        "    <div class=\"h\">Jugadas: " + ini.playmaker + "</div>\n" +
        "    <div class=\"h\">Lateral: " + ini.winger + "</div>\n" +
        "    <div class=\"h\">Pases: " + ini.passing + "</div>\n" +
        "    <div class=\"h\">Anotación: " + ini.scorer + "</div>\n" +
        "    <div class=\"h\">Balón Parado: " + ini.setPieces + "</div>\n" +
        "  </div>\n" +
        "  <div class=\"res_player_sep\">==></div>\n" +
        "  <div class=\"res_player b1 p5\">\n" +
        "    <div class=\"h tac\">Jugador " + player.playerId + "</div>\n" +
        "    <div class=\"h\">Años: " + player.age + "</div>\n" +
        "    <div class=\"h\">Días: " + player.days + "</div>\n" +
        "    <div class=\"h\">Porteria: " + player.keeper + "</div>\n" +
        "    <div class=\"h\">Defensa: " + player.defender + "</div>\n" +
        "    <div class=\"h\">Jugadas: " + player.playmaker + "</div>\n" +
        "    <div class=\"h\">Lateral: " + player.winger + "</div>\n" +
        "    <div class=\"h\">Pases: " + player.passing + "</div>\n" +
        "    <div class=\"h\">Anotación: " + player.scorer + "</div>\n" +
        "    <div class=\"h\">Balón Parado: " + player.setPieces + "</div>\n" +
        "  </div>\n" +
        "</div>\n"
    ).appendTo($('#div_results'));
}

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
        "    <div class=\"p10 tac\"><label class=\"button\" onclick=\"delPlayer(" + player.playerId + ")\">Borrar Jugador</label></div>\n" +
        "</div>\n"
    ).appendTo($('#players'));
}

function delPlayer(playerId) {
    players.splice(players.findIndex(player => player.playerId === playerId), 1);
    $('#player_' + playerId).remove();
    refreshPlayerTraining();
}

function resetPlayers() {
    players = [];
    $('[id^="player_"]').remove();
}

function addTrainingStage(trainingStage) {
    trainingStages.push(trainingStage);
    $("<div class=\"stage b1 p5\" id=\"stage_" + trainingStage.trainingStageId + "\">\n" +
        "    <div class=\"h tac\">Etapa " + trainingStage.trainingStageId + "</div>\n" +
        "    <div class=\"h\">Entrenamiento: " + getTrainingName(trainingStage.training) + "</div>\n" +
        "    <div class=\"h\">Duración: " + trainingStage.duration + "</div>\n" +
        "    <div class=\"h\">Entrenador: " + trainingStage.coach + "</div>\n" +
        "    <div class=\"h\">Asistentes: " + trainingStage.assistants + "</div>\n" +
        "    <div class=\"h\">Intensidad: " + trainingStage.intensity + "</div>\n" +
        "    <div class=\"h\">Resistencia: " + trainingStage.stamina + "</div>\n" +
        "    <div class=\"p10 tac\"><label class=\"button\" onclick=\"delTrainingStage(" + trainingStage.trainingStageId + ")\">Borrar Etapa</label></div>\n" +
        "</div>\n"
    ).appendTo($('#stages'));
}

function delTrainingStage(trainingStageId) {
    trainingStages.splice(trainingStages.findIndex(trainingStage => trainingStages.trainingStageId === trainingStageId), 1);
    $('#stage_' + trainingStageId).remove();
    refreshPlayerTraining();
}

function resetTrainingStages() {
    trainingStages = [];
    $('[id^="stage_"]').remove();
}

function getTrainingName(training) {
    switch (training) {
        case 9:
            return 'Portería';
        case 3:
            return 'Defensa';
        case 8:
            return 'Jugadas';
        case 5:
            return 'Lateral';
        case 7:
            return 'Pases';
        case 4:
            return 'Anotación';
        case 2:
            return 'Balón Parado'
        default:
            return '';
    }
}

function refreshPlayerTraining() {
    let html = "";
    html += "<table class='pt_table'>";
    html += "<tr><th></th>";
    trainingStages.forEach(stage => {
        html += "<th>Etapa " + stage.trainingStageId + "<br>" + getTrainingName(stage.training) + "</th>";
    });
    html += "</tr>";
    players.forEach(player => {
        html += "<tr><th>Jugador " + player.playerId + "</th>";
        let accumDuration = 0;
        trainingStages.forEach(stage => {
            accumDuration += parseInt(stage.duration);
            html += "<td>";
            slots[stage.training].forEach(slot => {
                if (parseInt(player.inclusionWeek) <= accumDuration) {
                    html += "<input id='cb_" + stage.trainingStageId + "_" + slot.por + "_" + slot.max + "_" + slot.trg + "_" + player.playerId + "' type='checkbox'><label>" + slot.por + "%</label><br>";
                }
            });
            html += "</td>";
            //html += "<td>" + player.playerId + "_" + stage.trainingStageId + "</td>";
        });
        html += "</tr>";
    });
    html += "</table>";
    $('#div_player_training').html(html);
    $('#section_results').css('padding-top', '40px');
    $('[id^="cb_"]').change(function (item) {
        const split = item.target.id.split('_');
        const trainingStageId = parseInt(split[1]);
        const por = parseInt(split[2]);
        const max = parseInt(split[3]);
        const trg = parseInt(split[4]);
        const playerId = parseInt(split[5]);

        const same_player_slot_id = ["cb", trainingStageId].join('_') + "_";
        if (item.target.checked) {
            $("[id^=" + same_player_slot_id + "][id$=_" + playerId + "]:not(:checked)").prop("disabled", true);
        } else {
            //$("[id^=" + same_player_slot_id + "][id$=_" + playerId + "]:disabled").prop("disabled", false);
            $("[id^=" + same_player_slot_id + "][id$=_" + playerId + "]:disabled").each(function (item, element) {
                const split = element.id.split('_');
                const trainingStageId = parseInt(split[1]);
                const por = parseInt(split[2]);
                const max = parseInt(split[3]);
                const trg = parseInt(split[4]);
                const playerId = parseInt(split[5]);
                const same_slot_id = ["cb", trainingStageId, por, max, trg].join('_') + "_";
                const check = $("[id^=" + same_slot_id + "]:checked").length;
                if (check < max) {
                    $("#" + element.id).prop("disabled", false);
                }
            });
        }

        const same_slot_id = ["cb", trainingStageId, por, max, trg].join('_') + "_";
        const check = $("[id^=" + same_slot_id + "]:checked").length;
        if (check === max) {
            $("[id^=" + same_slot_id + "]:not(:checked)").prop("disabled", true);
        } else {
            $("[id^=" + same_slot_id + "]:disabled").each(function (item, element) {
                const split = element.id.split('_');
                const trainingStageId = parseInt(split[1]);
                const playerId = parseInt(split[5]);
                const same_player_slot_id = ["cb", trainingStageId].join('_') + "_";
                const exist_same_player_slot_checked = $("[id^=" + same_player_slot_id + "][id$=_" + playerId + "]:checked").length > 0;
                if (!exist_same_player_slot_checked) {
                    $("#" + element.id).prop("disabled", false);
                }
            });
        }
    });
}
