import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Player } from '../../../model/Player';
import { PlayerFormGroup } from '../../../model/PlayerFormGroup';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html'
})
export class PlayersComponent {
  @Input() form: FormGroup;
  playersCount = 0;

  constructor(private readonly formBuilder: FormBuilder) {
  }

  addPlayer() {
    this.playersCount++;
    this.form.controls['players'].value.push(
      this.formBuilder.group(new PlayerFormGroup(new Player(this.playersCount)))
    );
  }

  importPlayers(): void {
  }

}


