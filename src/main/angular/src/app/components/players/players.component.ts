import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Player} from "../../../model/Player";
import {PlayerFormGroup} from "../../../model/PlayerFormGroup";

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html'
})
export class PlayersComponent {
  playersFormGroup: FormGroup;
  playersCount = 0;

  constructor(
      private readonly formBuilder: FormBuilder
  ) {
    this.playersFormGroup = this.formBuilder.group({
      players: [this.formBuilder.array([]), '']
    });
  }

  addPlayer() {
    this.playersCount++;
    this.playersFormGroup.controls['players'].value.push(
        this.formBuilder.group(new PlayerFormGroup(new Player(this.playersCount)))
    );
  }

  viewPlayers() {
    console.log(this.playersFormGroup);
  }

}


