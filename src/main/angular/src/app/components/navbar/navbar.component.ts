import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {
  @Input() form: FormGroup;

  constructor() {
  }

  ngOnInit(): void {
  }

  save(): void {
    let config = {
      'version': 3,
      'players': [],
      'trainingStages': [],
      'playerTraining': [],
      'teamTrainingRS': {}
    };

    this.form.controls['players'].value.controls.forEach(function(player) {
      config.players.push(player.value);
    });

    const file = new Blob([JSON.stringify(config, null, 2)], { type: 'text/json' });

    const link = document.createElement('a');
    link.href = URL.createObjectURL(file);
    link.download = 'htte_' + this.getDate() + '.json';
    link.click();
    link.remove();
  }

  private getDate(): string {
    const now = new Date();
    return [now.getFullYear(), now.getMonth() + 1, now.getDate(), now.getHours(), now.getMinutes(), now.getSeconds()]
      .map(function(i) {
        return ('' + i).padStart(2, '0');
      })
      .join('_');
  }

  load(): void {

  }
}
