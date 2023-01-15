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
  defending: FormControl;
  playmaking: FormControl;
  winger: FormControl;
  passing: FormControl;
  scoring: FormControl;
  setPieces: FormControl;
  daysForNextTraining: FormControl;
  inclusionWeek: FormControl;

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
    this.defending = new FormControl(player.defending);
    this.playmaking = new FormControl(player.playmaking);
    this.winger = new FormControl(player.winger);
    this.passing = new FormControl(player.passing);
    this.scoring = new FormControl(player.scoring);
    this.setPieces = new FormControl(player.setPieces);
    this.daysForNextTraining = new FormControl(player.daysForNextTraining);
    this.inclusionWeek = new FormControl(player.inclusionWeek);
  }
}
