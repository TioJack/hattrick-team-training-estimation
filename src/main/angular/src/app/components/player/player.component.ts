import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PlayerFormGroup } from '../../../model/PlayerFormGroup';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html'
})
export class PlayerComponent {
  @Input() playerFormGroup: FormGroup<PlayerFormGroup>;

  onChangeEvent(event: any) {
    this.playerFormGroup.patchValue({ 'age': event.target.value });
  }
}
