import { Player } from './Player';
import { FormControl } from '@angular/forms';


export class PlayerFormGroup {
  playerId: FormControl;
  name: FormControl;
  label: FormControl;
  age: FormControl;
  days: FormControl;
  form: FormControl;
  stamina: FormControl;
  leadership: FormControl;
  experience: FormControl;
  loyalty: FormControl;
  motherClubBonus: FormControl;
  specialty: FormControl;
  keeper: FormControl;
  defender: FormControl;
  playmaker: FormControl;
  winger: FormControl;
  passing: FormControl;
  scorer: FormControl;
  setPieces: FormControl;
  inclusionWeek: FormControl;
  daysForNextTraining: FormControl;

  constructor(player: Player) {
    this.playerId = new FormControl(player.playerId);
    this.name = new FormControl(player.name);
    this.label = new FormControl(player.label);
    this.age = new FormControl(player.age);
    this.days = new FormControl(player.days);
    this.form = new FormControl(player.form);
    this.stamina = new FormControl(player.stamina);
    this.leadership = new FormControl(player.leadership);
    this.experience = new FormControl(player.experience);
    this.loyalty = new FormControl(player.loyalty);
    this.motherClubBonus = new FormControl(player.motherClubBonus);
    this.specialty = new FormControl(player.specialty);
    this.keeper = new FormControl(player.keeper);
    this.defender = new FormControl(player.defender);
    this.playmaker = new FormControl(player.playmaker);
    this.winger = new FormControl(player.winger);
    this.passing = new FormControl(player.passing);
    this.scorer = new FormControl(player.scorer);
    this.setPieces = new FormControl(player.setPieces);
    this.inclusionWeek = new FormControl(player.inclusionWeek);
    this.daysForNextTraining = new FormControl(player.daysForNextTraining);
  }
}
