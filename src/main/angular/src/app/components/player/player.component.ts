import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {PlayerFormGroup} from "../../../model/PlayerFormGroup";

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html'
})
export class PlayerComponent {
  @Input() playerFormGroup: FormGroup<PlayerFormGroup>;
}
