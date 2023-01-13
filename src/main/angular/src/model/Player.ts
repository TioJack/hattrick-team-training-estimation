import { IPlayer } from './IPlayer';

export class Player implements IPlayer {
  playerId: number;
  name: string;
  label: string;
  age: number;
  days: number;
  form: number;
  stamina: number;
  leadership: number;
  experience: number;
  loyalty: number;
  motherClubBonus: number;
  specialty: number;
  keeper: number;
  defender: number;
  playmaker: number;
  winger: number;
  passing: number;
  scorer: number;
  setPieces: number;
  inclusionWeek: number;
  daysForNextTraining: number;

  constructor(playerId: number) {
    this.playerId = playerId;
    this.name = '';
    this.label = '';
    this.age = 27;
    this.days = 0;
    this.form = 0;
    this.stamina = 0;
    this.leadership = 0;
    this.experience = 0;
    this.loyalty = 0;
    this.motherClubBonus = 0;
    this.specialty = 0;
    this.keeper = 0;
    this.defender = 0;
    this.playmaker = 0;
    this.winger = 0;
    this.passing = 0;
    this.scorer = 0;
    this.setPieces = 0;
    this.inclusionWeek = 0;
    this.daysForNextTraining = 0;
  }
}
